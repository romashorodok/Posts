import React from "react";
import { isMarkActive, toggleMark } from "~/common/editor/helpers";
import { CustomEditor, CustomText } from "~/common/editor/types";
import { BaseProps, OrNull, OnlyBoolean } from "~/common/types.d";
import Button from "~/components/base/Button";

interface LeafButtonProps extends BaseProps {
  icon: React.ReactElement;
  editor: CustomEditor;
  leaf: keyof OnlyBoolean<CustomText>;
}

const LeafButton = React.forwardRef(
  (
    { icon, leaf, editor, ...props }: LeafButtonProps,
    ref: React.Ref<OrNull<HTMLButtonElement>>
  ) => {
    const active = isMarkActive(editor, leaf);

    const toggle = (event: React.ChangeEvent) => {
      event.preventDefault();
      toggleMark(editor, leaf);
    };

    return (
      <Button {...props} ref={ref} active={active} onMouseDown={toggle}>
        {icon}
      </Button>
    );
  }
);

export default LeafButton;
