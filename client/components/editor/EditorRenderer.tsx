import React from "react";
import { Descendant } from "slate";
import { Editable, RenderElementProps, Slate } from "slate-react";
import { CustomEditor } from "~/common/editor/types";
import Toolbar from "~/components/base/Toolbar";
import Styles from "~/styles/pages/editor.module.scss";
import Element from "~/components/editor/Element";
import Leaf from "~/components/editor/Leaf";

interface EditorRendererProps {
  editor: CustomEditor;
  value: Descendant[];
  toolbar?: React.ReactElement;
  onChange?: ((value: Descendant[]) => void) | undefined;
  onKeyDown?: ((event: React.KeyboardEvent) => void) | undefined;
  sideEffect?: (() => void) | undefined;
}

function EditorRenderer({
  editor,
  value,
  onChange,
  onKeyDown,
  toolbar,
  sideEffect,
}: EditorRendererProps): React.ReactElement {
  const renderElement = React.useCallback((props: RenderElementProps) => {
    if (sideEffect) sideEffect();

    return <Element {...props} />;
  }, []);

  const renderLeaf = React.useCallback((props) => {
    return <Leaf {...props} />;
  }, []);

  return (
    <Slate editor={editor} value={value} onChange={onChange}>
      <Toolbar className={`${Styles.editor_padding} ${Styles.editor_toolbar}`}>
        {toolbar}
      </Toolbar>
      <Editable
        className={Styles.editor_padding}
        renderElement={renderElement}
        renderLeaf={renderLeaf}
        onKeyDown={onKeyDown}
        placeholder="Enter some text…"
        spellCheck
        autoFocus
      />
    </Slate>
  );
}

export default EditorRenderer;
