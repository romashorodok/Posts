import React from "react";
import { BaseProps, OrNull } from "~/common/types.d";
import { cx, css } from "@emotion/css";

interface ButtonProps extends BaseProps {
  active: boolean;
  reversed: boolean;
}

const Button = React.forwardRef<
  HTMLButtonElement,
  React.PropsWithChildren<Partial<ButtonProps>>
>(
  (
    {
      className,
      active,
      reversed,
      ...props
    }: React.PropsWithChildren<ButtonProps>,
    ref: React.Ref<OrNull<HTMLSpanElement>>
  ) => (
    <span
      {...props}
      ref={ref}
      className={cx(
        className,
        css`
          cursor: pointer;
          color: ${reversed
            ? active
              ? "white"
              : "#aaa"
            : active
            ? "black"
            : "#ccc"};
        `
      )}
    />
  )
);

export default Button;
