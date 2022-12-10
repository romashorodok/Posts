import React from "react";
import Styles from "~/styles/pages/login.module.scss";
import * as Yup from "yup";
import FormField from "~/components/base/FormField";
import FormLayout from "~/layouts/FormLayout";
import MuiButton from "~/components/base/MuiButton";
import useForm from "~/hooks/useForm";
import { useAuth } from "~/hooks/useAuth";
import { AxiosError } from "axios";
import { useRouter } from "next/router";

const Schema = Yup.object({
  email: Yup.string().required().email("Invalid email"),
  password: Yup.string()
    .required("Password is required")
    .matches(/[a-zA-Z]/, "Password can contain only Latin latters")
    .min(8, "Password required at least 8 chars"),
});

function Login() {
  const { login } = useAuth();
  const router = useRouter();

  const {
    formState: credentials,
    setFromState,
    error,
    setError,
    validate,
  } = useForm<Yup.InferType<typeof Schema>>(Schema, {
    email: undefined,
    password: undefined,
  });

  async function onSubmit(event: React.FormEvent) {
    event.preventDefault();

    const valid = await validate();

    if (valid)
      login.mutate(credentials, {
        onSuccess: () => router.push("/"),
        onError: ({ response: { data } }: AxiosError<{ message: string }>) => {
          setError({ password: [data.message] });
        },
      });
  }

  function onChange(event: React.FormEvent) {
    if (event.target instanceof HTMLInputElement) {
      const { name, value } = event.target;

      setFromState({ [name]: value });
    }
  }

  return (
    <FormLayout>
      <div className={`${Styles.login_background} flex-1 flex justify-center`}>
        <div className={`${Styles.login_container} self-center`}>
          <form autoComplete="off" onChange={onChange} onSubmit={onSubmit}>
            <h1 className="pb-8 pt-3 text-3xl fg-normal">Login</h1>

            <FormField
              label="Email"
              name={"email"}
              errors={error}
              defaultValue={credentials.email}
            />

            <FormField
              label="Password"
              defaultValue={credentials.password}
              name={"password"}
              errors={error}
              type={"password"}
            />

            <div className="m-5">
              <MuiButton>Log In</MuiButton>
            </div>
          </form>
        </div>
      </div>
    </FormLayout>
  );
}

export default Login;
