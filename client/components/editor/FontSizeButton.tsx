import React from "react";
import { CustomEditor } from "~/common/editor/types";
import { BaseProps, OrNull } from "~/common/types.d";
import Button from "~/components/base/Button";

interface FontSizeButtonProps extends BaseProps {
  value: number;
  icon: React.ReactElement;
  editor: CustomEditor;
}

const FontSizeButton = React.forwardRef<HTMLButtonElement, FontSizeButtonProps>(
  (
    { value, icon, editor, ...props }: FontSizeButtonProps,
    ref: React.Ref<OrNull<HTMLButtonElement>>
  ) => {
    const onChange = (event: React.ChangeEvent) => {
      event.preventDefault();
      editor.addMark("size", value);
    };

    return (
      <Button {...props} ref={ref} onMouseDown={onChange}>
        {icon}
      </Button>
    );
  }
);

export default FontSizeButton;
