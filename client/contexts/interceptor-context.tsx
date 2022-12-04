import axios, {
  AxiosError,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
import React from "react";
import { useAxios } from "~/hooks/useAxios";
import { useAuth } from "./auth-context";

export function InterceptorContextProvider({
  children,
}: React.PropsWithChildren) {
  const { setAccessToken } = useAuth();
  const { axiosSSR, axiosAPI } = useAxios();

  React.useMemo(() => {
    const errorInterceptor = async (error: AxiosError) => {
      const status = error.response?.status || 500;

      switch (status) {
        case 401: {
          const { data: auth } = await axiosSSR.post("/api/refresh-token");

          if (!auth?.accessToken) return Promise.reject(error);

          setAccessToken(auth?.accessToken);

          const bearer = `Bearer ${auth?.accessToken}`;
          axios.defaults.headers.common.Authorization = bearer;
          error.config.headers.Authorization = bearer;

          const config = error.config;
          config.headers = JSON.parse(
            JSON.stringify(config.headers || {})
          ) as RawAxiosRequestHeaders;

          return axios(config);
        }
      }
    };

    const fulfilledInterceptor = (resp: AxiosResponse) => resp;

    axios.interceptors.response.use(fulfilledInterceptor, errorInterceptor);
    axiosAPI.interceptors.response.use(fulfilledInterceptor, errorInterceptor);
  }, []);

  return <>{children}</>;
}
