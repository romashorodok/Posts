import React from "react";
import { BaseProps, OrNull } from "~/common/types.d";

interface FontSizeInputProps extends BaseProps {
  value: number;
  onChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

const FontSizeInput = React.forwardRef<HTMLInputElement, FontSizeInputProps>(
  (
    { value, onChange, ...props }: React.PropsWithChildren<FontSizeInputProps>,
    ref: React.Ref<OrNull<HTMLInputElement>>
  ) => (
    <input
      {...props}
      ref={ref}
      className="w-16"
      inputMode={"numeric"}
      maxLength={2}
      value={value}
      onChange={onChange}
    />
  )
);

export default FontSizeInput;
