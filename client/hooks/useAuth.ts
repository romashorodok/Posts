import axios, { AxiosResponse } from "axios";
import React from "react";
import { useMutation, UseMutationResult } from "@tanstack/react-query";
import {
  AuthContextProps,
  AuthContext,
  accessTokenKey,
  profileIdKey,
} from "~/contexts/auth-context";
import { useAxios } from "~/hooks/useAxios";

export const useAuth = (): {
  accessToken?: string;
  setAccessToken: (accessToken: string) => void;
  profileId?: string;
  login: UseMutationResult<AxiosResponse<any, any>, any, Credentials, any>;
  register: UseMutationResult<
    AxiosResponse<any, any>,
    any,
    RegisterCredentials,
    any
  >;
  logout: UseMutationResult<AxiosResponse<any, any>>;
} => {
  const {
    accessToken,
    profileId,
    setAccessToken: setAccessTokenInternal,
    setProfileId: setProfileIdInternal,
  } = React.useContext<AuthContextProps>(AuthContext);
  const { axiosSSR } = useAxios();

  const login = useMutation(
    (credentials: Credentials) => axiosSSR.post("/api/login", credentials),
    {
      onSuccess: ({ data: user }) => {
        setAccessToken(user?.accessToken);
        setProfileId(user?.id);
      },
    }
  );

  const register = useMutation((registerCredentials: RegisterCredentials) => {
    const payload = new FormData();
    payload.append(
      "user",
      new Blob([JSON.stringify(registerCredentials)], {
        type: "application/json",
      })
    );

    return axios.create().post("/user/", payload);
  });

  const logout = useMutation(() => axiosSSR.post("/api/logout"), {
    onMutate: () => {
      setAccessToken(undefined);
      setProfileId(undefined);

      localStorage.removeItem("_profile-id");
      localStorage.removeItem("_access-token");
    },
  });

  const setAccessToken = (accessToken: string) => {
    setAccessTokenInternal(accessToken);
    localStorage.setItem(accessTokenKey, accessToken);
  };

  const setProfileId = (profileId: string) => {
    setProfileIdInternal(profileId);
    localStorage.setItem(profileIdKey, profileId);
  };

  return { accessToken, setAccessToken, profileId, login, register, logout };
};
