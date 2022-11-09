import {
  Text,
  createEditor,
  Node,
  Element,
  Editor,
  Descendant,
  BaseEditor,
  BaseText,
  ExtendedType,
} from "slate";
import { ReactEditor, RenderLeafProps } from "slate-react";
import { HistoryEditor } from "slate-history";
import { type } from "os";

export const LIST_TYPES = ["numbered-list", "bulleted-list"];
export const TEXT_ALIGN_TYPES = ["left", "center", "right"];
export const TEXT_SIZES = {
  "block-quote": "14",
  "bulleted-list": "14",
  "numbered-list": "14",
  "heading-two": "14",
  "heading-one": "14",
  "list-item": "14",
  paragraph: "14",
  default: "14",
};

export type BlockQuoteElement = BaseElement & {
  type: "block-quote";
  align?: string;
  children: Descendant[];
};

export type BulletedListElement = BaseElement & {
  type: "bulleted-list";
  align?: string;
  children: Descendant[];
};

export type NumberedListElement = BaseElement & {
  type: "numbered-list";
  align?: string;
  children: Descendant[];
};

export type HeadingTwoElement = BaseElement & {
  type: "heading-two";
  align?: string;
  children: Descendant[];
};

export type HeadingOneElement = BaseElement & {
  type: "heading-one";
  align?: string;
  children: Descendant[];
};

export type ListItemElement = BaseElement & {
  type: "list-item";
  children: Descendant[];
};

export type ParagraphElement = BaseElement & {
  type: "paragraph";
  align?: string;
  children: Descendant[];
};

export type BaseElement = {
  type: string;
  size?: string;
  children?: [];
};

export type CustomElement =
  | BaseElement
  | BlockQuoteElement
  | BulletedListElement
  | NumberedListElement
  | HeadingOneElement
  | HeadingTwoElement
  | ListItemElement
  | ParagraphElement;

export type LeafText = {
  bold?: boolean;
  italic?: boolean;
  code?: boolean;
  underline?: boolean;
  text: string;
  size?: number;
};

export type EmptyText = {
  text: string;
};

export type CustomText = LeafText & EmptyText;

export type CustomEditor = BaseEditor & ReactEditor & HistoryEditor;

export type CustomRenderLeafProps = RenderLeafProps & { leaf: CustomText };

declare module "slate" {
  interface CustomTypes {
    Editor: CustomEditor;
    Element: CustomElement;
    Text: CustomText;
  }
}
