import React, { useMemo, useState } from "react";
import Styles from "~/styles/pages/editor.module.scss";
import { withReact } from "slate-react";
import { createEditor, Descendant } from "slate";
import { withHistory } from "slate-history";
import { Post } from "~/pages";
import EditorLayout from "~/layouts/EditorLayout";
import axios from "axios";
import { GetServerSideProps } from "next";
import { CustomElement, CustomText } from "~/common/editor/types.d";

import EditorRenderer from "~/components/editor/EditorRenderer";
import ToolBarButtons from "~/components/editor/ToolBarButtons";

function parseText({ post }: { post: Post }): Descendant[] {
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

function putText(post: Post, text: Descendant[]) {
  const plainText = JSON.stringify(text);

  if (post.id)
    axios
      .put("/post/", { ...post, description: plainText })
      .catch(console.error);
}

function Index({ post }: { post: Post }) {
  const [element, setElement] = useState<CustomElement>();
  const [leaf, setLeaf] = useState<CustomText>();

  const editor = useMemo(() => withHistory(withReact(createEditor())), []);
  const text = useMemo<Descendant[]>(() => parseText({ post }), [post]);

  const onChangeText = (text: Descendant[]) => putText(post, text);

  const onCursorChange = () => {
    React.useEffect(() => {
      const elements: CustomElement[] = editor.children;

      if (editor?.selection) {
        const element: CustomElement = elements[editor.selection.focus.path[0]];
        const leaf = element.children[editor.selection.focus.path[1]];
        setElement(element);
        setLeaf(leaf);
      }
    });
  };

  const handleKeyDown = (event: React.KeyboardEvent) => {
    if (event.key === "Enter") editor.removeMark("size");
  };

  return (
    <EditorLayout>
      <h1 className="fg-normal m-2">{post.title}</h1>
      <div className={Styles.editor_container}>
        <EditorRenderer
          editor={editor}
          value={text}
          onChange={onChangeText}
          onKeyDown={handleKeyDown}
          sideEffect={onCursorChange}
          toolbar={<ToolBarButtons element={element} leaf={leaf} />}
        />
      </div>
    </EditorLayout>
  );
}

export const getServerSideProps: GetServerSideProps<{ post: Post }> = async ({
  params,
}) => {
  const { post: id } = params;
  const { data: post } = await axios.get(`/post/${id}`);
  return {
    props: {
      post,
    },
  };
};

export default Index;
