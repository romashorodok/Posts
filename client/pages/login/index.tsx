import React from "react";
import { useAuth } from "~/contexts/auth-context";

function Login() {
  const [email] = React.useState<string>("roman@gmail.com");
  const [password] = React.useState<string>("q");

  const { login, accessToken } = useAuth();

  function onSubmit(event: React.FormEvent, credentials: Credentials) {
    event.preventDefault();

    login.mutate(credentials, {
      onSuccess: () => {
        console.log("from submit form");
        console.log(accessToken)
      },
    });
  }

  return (
    <div>
      <form onSubmit={(e) => onSubmit(e, { email, password })}>
        <button>Submit</button>
      </form>
    </div>
  );
}

export default Login;
