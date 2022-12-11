import { TextField } from "@mui/material";
import React, { HTMLInputTypeAttribute } from "react";
import { BaseProps, OrNull } from "~/common/types";

interface FormFieldProps extends BaseProps {
  errors: { [keys: string]: Array<String> };
  label: string;

  type?: HTMLInputTypeAttribute;
  required?: boolean;
}

const FormField = React.forwardRef<
  HTMLInputElement,
  Pick<HTMLInputElement, "name" | "defaultValue"> & FormFieldProps
>(
  (
    { errors, name, required = true, ...props },
    ref: React.Ref<OrNull<HTMLInputElement>>
  ) => {
    return (
      <div className="m-5">
        <TextField
          fullWidth
          required={required}
          size={"small"}
          ref={ref}
          name={name}
          sx={{
            "& .MuiOutlinedInput-root": {
              "& fieldset": {
                borderColor: "white",
              },
              "&:hover fieldset": {
                borderColor: "white",
              },

              "&.Mui-focused fieldset": {
                borderColor: "white",
              },
            },
          }}
          InputLabelProps={{
            sx: {
              color: "white",

              "&.Mui-focused": {
                color: "white",
              },
            },
          }}
          InputProps={{
            sx: {
              color: "white",
            },
          }}
          {...props}
        />

        {errors && name in errors ? (
          <ul className="m-2">
            {errors[name].map((message: string, idx) => (
              <li className="list-inside list-disc text-red-500" key={idx}>
                {message}
              </li>
            ))}
          </ul>
        ) : null}
      </div>
    );
  }
);

export default FormField;
