import { AxiosError } from "axios";
import { useRouter } from "next/router";
import * as Yup from "yup";
import FormField from "~/components/base/FormField";
import MuiButton from "~/components/base/MuiButton";
import { useAuth } from "~/hooks/useAuth";
import useForm from "~/hooks/useForm";
import FormLayout from "~/layouts/FormLayout";
import Styles from "~/styles/pages/login.module.scss";

const name = (val) => /[A-Z]/.test(val.charAt(0));

const Schema = Yup.object({
  firstName: Yup.string()
    .required()
    .min(2, "Name should contain at least 2 chars")
    .test("firstUpperCaseLatter", "First letter must be upper case", name),
  lastName: Yup.string()
    .required()
    .min(2, "Name should contain at least 2 chars")
    .test("firstUpperCaseLatter", "First letter must be upper case", name),
  email: Yup.string().required().email("Invalid email"),
  password: Yup.string()
    .required("Password is required")
    .matches(/[a-zA-Z]/, "Password can contain only Latin latters")
    .min(8, "Password required at least 8 chars"),
  passwordConfirmation: Yup.string()
    .required("Password confirmation is required")
    .oneOf([Yup.ref("password")], "Password must match"),
});

function Register() {
  const { register } = useAuth();
  const router = useRouter();

  const {
    formState: credentials,
    setFromState,
    error,
    validate,
    setError,
  } = useForm<Yup.InferType<typeof Schema>>(Schema, {
    firstName: undefined,
    lastName: undefined,
    email: undefined,
    password: undefined,
    passwordConfirmation: undefined,
  });

  async function onSubmit(event: React.FormEvent) {
    event.preventDefault();

    const valid = await validate();

    if (valid)
      register.mutate(credentials, {
        onSuccess: () => router.push("/login"),
        onError: ({
          response: { data },
        }: AxiosError<{ error: { [keys in keyof typeof error]: [any] } }>) => {
          setError(data.error);
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
            <h1 className="pb-8 pt-3 text-3xl fg-normal">Register</h1>

            <FormField
              label="First name"
              defaultValue={credentials.firstName}
              name={"firstName"}
              errors={error}
            />

            <FormField
              label="Last name"
              defaultValue={credentials.lastName}
              name={"lastName"}
              errors={error}
            />

            <FormField
              label="Email"
              defaultValue={credentials.email}
              name={"email"}
              errors={error}
            />

            <FormField
              label="Password"
              defaultValue={credentials.password}
              name={"password"}
              errors={error}
              type={"password"}
            />

            <FormField
              label="Confirm password"
              name={"passwordConfirmation"}
              errors={error}
              defaultValue={credentials.passwordConfirmation}
              type={"password"}
            />

            <div className="m-5">
              <MuiButton>Sign Up</MuiButton>
            </div>
          </form>
        </div>
      </div>
    </FormLayout>
  );
}

export default Register;
