import {
  Editor,
  Node,
  Transforms,
  Element as SlateElement,
  Descendant,
} from "slate";
import { Post } from "~/common/post/types";
import { CustomEditor, CustomText } from "./types.d";

export function parseText(post: Post): Descendant[] {
  try {
    return JSON.parse(post.description);
  } catch {
    console.error("Cannot parse post description");
    return [
      {
        type: "paragraph",
        children: [{ text: "Cannot parse description, enter content again." }],
      },
    ];
  }
}

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
