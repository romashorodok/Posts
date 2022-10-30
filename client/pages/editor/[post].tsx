import React, { useCallback, useMemo } from "react";
import Styles from "~/styles/pages/editor.module.scss";
import {
  Editable,
  withReact,
  useSlate,
  Slate,
  RenderElementProps,
} from "slate-react";
import { createEditor, Descendant } from "slate";
import { withHistory } from "slate-history";
import { Post } from "~/pages";
import FormatBoldIcon from "@mui/icons-material/FormatBold";
import FormatItalicIcon from "@mui/icons-material/FormatItalic";
import FormatUnderlinedIcon from "@mui/icons-material/FormatUnderlined";
import CodeIcon from "@mui/icons-material/Code";
import LooksTwoIcon from "@mui/icons-material/LooksTwo";
import LooksOneIcon from "@mui/icons-material/LooksOne";
import FormatQuoteIcon from "@mui/icons-material/FormatQuote";
import FormatListNumberedIcon from "@mui/icons-material/FormatListNumbered";
import FormatListBulletedIcon from "@mui/icons-material/FormatListBulleted";
import AlignHorizontalLeftIcon from "@mui/icons-material/AlignHorizontalLeft";
import AlignHorizontalCenterIcon from "@mui/icons-material/AlignHorizontalCenter";
import AlignHorizontalRightIcon from "@mui/icons-material/AlignHorizontalRight";
import { Button, Toolbar } from "./components";
import EditorLayout from "~/layouts/EditorLayout";
import axios from "axios";
import { GetServerSideProps } from "next";
import Element from "~/components/Element";
import {
  toggleBlock,
  toggleMark,
  isBlockActive,
  isMarkActive,
} from "~/common/editor/helpers";
import { TEXT_ALIGN_TYPES } from "~/common/editor/types.d";

const Index = ({ post }: { post: Post }) => {
  const renderElement = useCallback(
    (props: RenderElementProps) => <Element {...props} />,
    []
  );
  const renderLeaf = useCallback((props) => <Leaf {...props} />, []);
  const editor = useMemo(() => withHistory(withReact(createEditor())), []);

  const text = useMemo<Descendant[]>(() => {
    try {
      return JSON.parse(post.description);
    } catch {
      console.error("Cannot parse post description");
      return [
        {
          type: "paragraph",
          children: [
            { text: "Cannot parse description, enter content again." },
          ],
        },
      ];
    }
  }, [post]);

  const onChangeText = (text: Descendant[]) => {
    const plainText = JSON.stringify(text);
    if (post.id) {
      axios.put("/post/", { ...post, description: plainText });
    }
  };

  return (
    <EditorLayout>
      <h1 className="fg-normal m-2">{post.title}</h1>
      <div className={Styles.editor_container}>
        <Slate editor={editor} value={text} onChange={onChangeText}>
          <Toolbar
            className={`${Styles.editor_padding} ${Styles.editor_toolbar}`}
          >
            <MarkButton format="bold" icon={<FormatBoldIcon />} />
            <MarkButton format="italic" icon={<FormatItalicIcon />} />
            <MarkButton format="underline" icon={<FormatUnderlinedIcon />} />
            <MarkButton format="code" icon={<CodeIcon />} />
            <BlockButton format="heading-one" icon={<LooksOneIcon />} />
            <BlockButton format="heading-two" icon={<LooksTwoIcon />} />
            <BlockButton format="block-quote" icon={<FormatQuoteIcon />} />
            <BlockButton
              format="numbered-list"
              icon={<FormatListNumberedIcon />}
            />
            <BlockButton
              format="bulleted-list"
              icon={<FormatListBulletedIcon />}
            />
            <BlockButton format="left" icon={<AlignHorizontalLeftIcon />} />
            <BlockButton format="center" icon={<AlignHorizontalCenterIcon />} />
            <BlockButton format="right" icon={<AlignHorizontalRightIcon />} />
          </Toolbar>
          <Editable
            className={Styles.editor_padding}
            renderElement={renderElement}
            renderLeaf={renderLeaf}
            placeholder="Enter some rich text…"
            spellCheck
            autoFocus
          />
        </Slate>
      </div>
    </EditorLayout>
  );
};

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

const Leaf = ({ attributes, children, leaf }) => {
  if (leaf.bold) {
    children = <strong>{children}</strong>;
  }

  if (leaf.code) {
    children = <code>{children}</code>;
  }

  if (leaf.italic) {
    children = <em>{children}</em>;
  }

  if (leaf.underline) {
    children = <u>{children}</u>;
  }

  return <span {...attributes}>{children}</span>;
};

const BlockButton = ({ format, icon }) => {
  const editor = useSlate();
  return (
    <Button
      active={isBlockActive(
        editor,
        format,
        TEXT_ALIGN_TYPES.includes(format) ? "align" : "type"
      )}
      onMouseDown={(event) => {
        event.preventDefault();
        toggleBlock(editor, format);
      }}
    >
      {icon}
    </Button>
  );
};

const MarkButton = ({ format, icon }) => {
  const editor = useSlate();
  return (
    <Button
      active={isMarkActive(editor, format)}
      onMouseDown={(event) => {
        event.preventDefault();
        toggleMark(editor, format);
      }}
    >
      {icon}
    </Button>
  );
};

export default Index;
