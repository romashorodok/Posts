import React from "react";
import * as Yup from "yup";

const useForm = <T extends Object>(schema: Yup.AnySchema, data: T) => {
  type ErrorMessages = Partial<{ [key in keyof T]: Array<String> }>;

  const [state, setFromState] = React.useReducer<React.Reducer<T, Partial<T>>>(
    (state, next) => ({ ...state, ...next }),
    data
  );

  const [error, setError] = React.useState<ErrorMessages>();

  const validate = async () => {
    try {
      await schema.validate(state, { abortEarly: false });

      setError({} as never);

      return true;
    } catch (err) {
      if (err instanceof Yup.ValidationError) {
        const messageBag: ErrorMessages = {} as never;

        err.inner.forEach((error: Yup.ValidationError) => {
          const isUndefined = !messageBag[error.path];
          if (isUndefined) messageBag[error.path] = [];

          messageBag[error.path].push(error.message);
        });

        setError(messageBag);
      }

      return false;
    }
  };

  return { formState: state, setFromState, error, setError, validate };
};

export default useForm;
