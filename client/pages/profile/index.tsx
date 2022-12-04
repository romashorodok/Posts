import axios from "axios";
import React from "react";
import { useAuth } from "~/contexts/auth-context";

function Index() {
  const { accessToken, profileId } = useAuth();

  React.useEffect(() => {
    if (profileId != null && accessToken != null)
      (async () => {
        const profile = await axios.get(`/user/profile/${profileId}`).catch(console.log);

        console.log(profile)
      })();

  }, [accessToken, profileId]);

  return <div></div>;
}

export default Index;
