import React, { useMemo, useState } from "react";
import Styles from "~/styles/pages/editor.module.scss";
import { withReact } from "slate-react";
import { createEditor, Descendant } from "slate";
import { withHistory } from "slate-history";
import { Post } from "~/common/post/types";
import EditorLayout from "~/layouts/EditorLayout";
import axios from "axios";
import { GetServerSideProps } from "next";
import { CustomElement, CustomText } from "~/common/editor/types.d";

import EditorRenderer from "~/components/editor/EditorRenderer";
import ToolBarButtons from "~/components/editor/ToolBarButtons";
import { parseText } from "~/common/editor/transforms";

function putText(post: Post, text: Descendant[], file) {
  const postJson = JSON.stringify({
    ...post,
    description: JSON.stringify(text),
  });


  const payload = new FormData();
  payload.append("post", new Blob([postJson], { type: "application/json" }));

  if (post.id) axios.put("/post/", payload).catch(console.error);
}

function Index({ post, file }: { post: Post; file }) {
  const [element, setElement] = useState<CustomElement>();
  const [leaf, setLeaf] = useState<CustomText>();

  const editor = useMemo(() => withHistory(withReact(createEditor())), []);
  const text = useMemo<Descendant[]>(() => parseText(post), [post]);

  const onChangeText = (text: Descendant[]) => putText(post, text, file);

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
      file: post?.image
    },
  };
};

export default Index;
