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
  const { setAccessToken, logout } = useAuth();
  const { axiosSSR, axiosAPI } = useAxios();

  React.useMemo(() => {
    const errorInterceptor = async (error: AxiosError) => {
      const status = error.response?.status || 500;

      const onErrorHandler = async (e) => {
        await logout.mutateAsync({});
        return e;
      };

      switch (status) {
        case 401: {
          const { data: auth } = await axiosSSR
            .post("/api/refresh-token")
            .catch(onErrorHandler);

          if (!auth?.accessToken)
            return Promise.reject(() => onErrorHandler(error));

          setAccessToken(auth?.accessToken);

          const bearer = `Bearer ${auth?.accessToken}`;
          axios.defaults.headers.common.Authorization = bearer;
          error.config.headers.Authorization = bearer;

          const config = error.config;
          config.headers = JSON.parse(
            JSON.stringify(config.headers || {})
          ) as RawAxiosRequestHeaders;

          return axios(config).catch(onErrorHandler);
        }
      }
    };

    const fulfilledInterceptor = (resp: AxiosResponse) => resp;

    axios.interceptors.response.use(fulfilledInterceptor, errorInterceptor);
    axiosAPI.interceptors.response.use(fulfilledInterceptor, errorInterceptor);
  }, []);

  return <>{children}</>;
}
