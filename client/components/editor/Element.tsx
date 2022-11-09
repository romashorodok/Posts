import { CSSProperties } from "react";
import { RenderElementProps } from "slate-react";
import { CustomElement } from "~/common/editor/types.d";

type Modify<T, R extends Partial<Record<keyof T, any>>> = Omit<T, keyof R> & R;

type ExtendedRenderElementProps = Modify<
  RenderElementProps,
  {
    element: Partial<CustomElement & { align: string }>;
  }
>;

function Element({
  attributes,
  children,
  element,
}: ExtendedRenderElementProps): React.ReactElement {
  const style = { textAlign: element.align } as CSSProperties;

  switch (element.type) {
    case "block-quote":
      return (
        <blockquote style={style} {...attributes}>
          {children}
        </blockquote>
      );
    case "bulleted-list":
      return (
        <ul className="list-disc list-inside" style={style} {...attributes}>
          {children}
        </ul>
      );
    case "heading-one":
      return (
        <h1 className="text-2xl" style={style} {...attributes}>
          {children}
        </h1>
      );
    case "heading-two":
      return (
        <h2 className="text-xl" style={style} {...attributes}>
          {children}
        </h2>
      );
    case "list-item":
      return (
        <li style={style} {...attributes}>
          {children}
        </li>
      );
    case "numbered-list":
      return (
        <ol className="list-decimal list-inside" style={style} {...attributes}>
          {children}
        </ol>
      );
    default:
      return (
        <p style={style} {...attributes}>
          {children}
        </p>
      );
  }
}

export default Element;
