import { useQuery } from "@tanstack/react-query";
import axios from "axios";
import React from "react";
import { Profile } from "~/common/post/types";
import { useAuth } from "~/hooks/useAuth";

function Index() {
  const { profileId, logout } = useAuth();

  const {data: profile, ...profileQuery} = useQuery<Profile>(['profile'], () => axios.get(`/user/profile/${profileId}`).then(resp => resp.data), {
    enabled: false
  });

  React.useEffect(() => {
    if (profileId)
      profileQuery.refetch();

  }, [profileId]);

  if (profileQuery.isLoading)
    return null;

  return (
    <div className="flex flex-col m-4">
      <h1>email: {profile.email}</h1>
      <h1>first name: {profile.firstName}</h1>
      <h1>email: {profile.lastName}</h1>

      <h1>total post: {profile.posts.totalElements}</h1>

      <div>
      <button onClick={() => logout.mutate({})}>Log out</button>
      </div>
    </div>
  );
}

export default Index;
