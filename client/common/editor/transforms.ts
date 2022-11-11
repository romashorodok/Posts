import { Editor, Node, Transforms, Element as SlateElement } from "slate";
import { CustomEditor, CustomText } from "./types.d";

const hasLeafProperty = (n: CustomText, field: string): boolean => {
  return !Editor.isEditor(n) && Node.isNode(n) && field in n;
};

export function setLeafProperty(
  editor: CustomEditor,
  prop: keyof CustomText,
  value: any
) {
  Transforms.setNodes<SlateElement>(
    editor,
    { [prop]: value },
    {
      match: (n) => hasLeafProperty(n, prop),
      split: true,
    }
  );
}
