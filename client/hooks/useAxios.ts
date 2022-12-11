import { AxiosInstance } from "axios";
import React from "react";
import * as Client from "~/api/client";

export const useAxios = () => {
  /**
   * @return axios instance to call internal next api
   */
  const axiosSSR = React.useMemo<AxiosInstance>(() => Client.axiosSSR, []);

  // FIX: When try refresh access token, not refresh token in localstorage. It may be re-rendering problem
  /**
   * @return axios instance to call spring backend
   */
  const axiosAPI = React.useMemo<AxiosInstance>(() => Client.axiosAPI, []);

  return {
    axiosAPI,
    axiosSSR,
  };
};
