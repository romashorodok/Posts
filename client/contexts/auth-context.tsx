import axios, { AxiosResponse } from "axios";
import React from "react";
import { useMutation, UseMutationResult } from "react-query";
import LoginQuery from "~/api/queries/login";

export const AuthContext = React.createContext({});

export function AuthContextProvider({ children }: React.PropsWithChildren) {
  const [accessToken, setAccessToken] = React.useState<string>();
  const [profileId, setProfileId] = React.useState<string>();

  const login = useMutation((credentials: Credentials) => LoginQuery(credentials), {
    onSuccess: ({ data: user }) => {
      setAccessToken(user?.accessToken);
      setProfileId(user?.id);
      localStorage.setItem("_access-token", user?.accessToken);
      localStorage.setItem("_profile-id", user?.id);
    }
  });

  const authContextData = React.useMemo(
    () => ({
      accessToken,
      profileId,
      login,
    }),
    [accessToken]
  );

  React.useEffect(() => {
    const localAccessToken = localStorage.getItem("_access-token");
    const localProfileId = localStorage.getItem("_profile-id");

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
  profileId?: string;
  login?: UseMutationResult<AxiosResponse<any, any>, any, Credentials, any>;
} => React.useContext(AuthContext);
