import axios, { AxiosResponse } from "axios";
import React from "react";
import { useMutation, UseMutationResult } from "react-query";
import { useAxios } from "~/hooks/useAxios";

interface AuthContextProps {
  accessToken?: string;
  profileId?: string;

  setAccessToken?: (token: string) => void;
  setProfileId?: (id: string) => void;
}

export const AuthContext = React.createContext<AuthContextProps>({});
const accessTokenKey = "_access-token";
const profileIdKey = "_profile-id";

export function AuthContextProvider({ children }: React.PropsWithChildren) {
  const [accessToken, setAccessToken] = React.useState<string>();
  const [profileId, setProfileId] = React.useState<string>();

  const authContextData = React.useMemo(
    () => ({
      accessToken,
      profileId,

      setAccessToken,
      setProfileId,
    }),
    [accessToken]
  );

  React.useEffect(() => {
    const localAccessToken = localStorage.getItem(accessTokenKey);
    const localProfileId = localStorage.getItem(profileIdKey);

    if (localAccessToken != null && localAccessToken != accessToken)
      setAccessToken(localAccessToken);

    if (localProfileId != null && localProfileId != profileId)
      setProfileId(localProfileId);

    const bearer = `Bearer ${localAccessToken}`;
    axios.defaults.headers.common.Authorization = bearer;
  });

  return (
    <AuthContext.Provider value={authContextData}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = (): {
  accessToken?: string;
  setAccessToken: (accessToken: string) => void;
  profileId?: string;
  login: UseMutationResult<AxiosResponse<any, any>, any, Credentials, any>;
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

  return { accessToken, setAccessToken, profileId, login, logout };
};
