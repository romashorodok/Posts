import axios from "axios";
import React from "react";
import { useAuth } from "~/hooks/useAuth";

function Index() {
  const { profileId, logout } = useAuth();

  React.useEffect(() => {
    if (profileId)
      axios
        .get(`/user/profile/${profileId}`)
        .then(console.log)
        .catch(console.error);
  }, [profileId]);

  return (
    <div>
      <button onClick={() => logout.mutate({})}>Log out</button>
    </div>
  );
}

export default Index;
