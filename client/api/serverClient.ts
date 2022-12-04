import axios, {
  Axios,
  AxiosError,
  AxiosResponse,
  RawAxiosRequestHeaders,
} from "axios";
import { QueryClient } from "react-query";

// TODO: make it as env var
const api = "http://localhost:3000";

export const serverQueryClient = new QueryClient({
  defaultOptions: { queries: { staleTime: 2000 } },
});

/**
 * Next js api server client
 */
export const serverClient = axios.create({
  baseURL: api,
  withCredentials: true,
});

export const apiClient = axios.create({
  baseURL: "http://localhost:8080/api",
});

axios.interceptors.response.use(
  (resp: AxiosResponse) => resp,
  async (error: AxiosError) => {
    const status = error.response?.status || 500;

    switch (status) {
      case 401: {
        const { data: auth } = await serverClient.post("/api/refresh-token");

        if (!auth?.accessToken) return Promise.reject(error);

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
  }
);
