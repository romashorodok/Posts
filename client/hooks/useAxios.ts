import { AxiosInstance } from "axios";
import React from "react";
import * as Client from "~/api/client";

export const useAxios = () => {
  /**
   * @return axios instance to call internal next api
   */
  const axiosSSR = React.useMemo<AxiosInstance>(() => Client.axiosSSR, []);

  /**
   * @return axios instance to call spring backend
   */
  const axiosAPI = React.useMemo<AxiosInstance>(() => Client.axiosAPI, []);

  return {
    axiosAPI,
    axiosSSR,
  };
};
