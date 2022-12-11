import axios from "axios";
import React from "react";

export interface AuthContextProps {
  accessToken?: string;
  profileId?: string;

  setAccessToken?: (token: string) => void;
  setProfileId?: (id: string) => void;
}

export const AuthContext = React.createContext<AuthContextProps>({});
export const accessTokenKey = "_access-token";
export const profileIdKey = "_profile-id";

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
