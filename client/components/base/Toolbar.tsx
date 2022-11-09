import React, { Ref, PropsWithChildren } from "react";
import { cx, css } from "@emotion/css";
import { BaseProps, OrNull } from "~/common/types.d";
import Menu from "~/components/base/Menu";

const Toolbar = React.forwardRef(
  (
    { className, ...props }: PropsWithChildren<BaseProps>,
    ref: Ref<OrNull<HTMLDivElement>>
  ) => (
    <Menu
      {...props}
      ref={ref}
      className={cx(
        className,
        css`
          border-bottom: 2px solid #eee;
        `
      )}
    />
  )
);

export default Toolbar;
