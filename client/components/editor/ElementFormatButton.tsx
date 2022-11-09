import React from "react";
import { isBlockActive } from "~/common/editor/helpers";
import { CustomEditor } from "~/common/editor/types";
import { BaseProps, OrNull } from "~/common/types.d";
import Button from "~/components/base/Button";
import { TEXT_ALIGN_TYPES } from "~/common/editor/types.d";
import { toggleBlock } from "~/common/editor/helpers";
import { setLeafProperty } from "~/common/editor/transforms";

interface ElementFormatButtonProps extends BaseProps {
  icon: React.ReactElement;
  editor: CustomEditor;
  format: string;
}

// Need for set up format for elements
const ElementFormatButton = React.forwardRef(
  (
    { icon, editor, format, ...props }: ElementFormatButtonProps,
    ref: React.Ref<OrNull<HTMLButtonElement>>
  ) => {
    const active = isBlockActive(
      editor,
      format,
      TEXT_ALIGN_TYPES.includes(format) ? "align" : "type"
    );

    const toggle = (event: React.ChangeEvent) => {
      event.preventDefault();
      toggleBlock(editor, format);
      setLeafProperty(editor, "size", null);
    };

    return (
      <Button {...props} ref={ref} active={active} onMouseDown={toggle}>
        {icon}
      </Button>
    );
  }
);

export default ElementFormatButton;
