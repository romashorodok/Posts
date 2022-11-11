import React from "react";

export interface BaseProps {
  className?: string;
  [key: string]: unknown;
}

export type OrNull<T> = T | null;

type BooleanKeys<T> = {
  [k in keyof T]: T[k] extends boolean ? k : never;
}[keyof T];

type OnlyBoolean<T> = { [k in BooleanKeys<T>]: boolean };

declare module "react" {
  function forwardRef<T, P = {}>(
    render: ForwardRefRenderFunction<T, P>
  ): ForwardRefExoticComponent<RefAttributes<T> & React.PropsWithRef<P>>;
}
