import FontSizeInput from "~/components/editor/FontSizeInput";
import FontSizeButton from "~/components/editor/FontSizeButton";
import ElementFormatButton from "~/components/editor/ElementFormatButton";
import LeafButton from "~/components/editor/LeafButton";
import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import { useSlate } from "slate-react";
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
import { TEXT_SIZES } from "~/common/editor/types.d";

function ToolBarButtons({ element, leaf }) {
  const editor = useSlate();

  const getFontSize = (value): number => {
    if (value === "px") return 0;

    return isNaN(parseInt(value)) || value === undefined
      ? parseInt(TEXT_SIZES[element?.type] ?? TEXT_SIZES["default"])
      : parseInt(value);
  };

  const increaseFontSize = (value) => getFontSize(value) + 1;

  const decreaseFontSize = (value) => getFontSize(value) - 1;

  return (
    <>
      <LeafButton leaf={"bold"} icon={<FormatBoldIcon />} editor={editor} />
      <LeafButton leaf={"italic"} icon={<FormatItalicIcon />} editor={editor} />
      <LeafButton
        leaf={"underline"}
        icon={<FormatUnderlinedIcon />}
        editor={editor}
      />
      <LeafButton leaf={"code"} icon={<CodeIcon />} editor={editor} />

      <ElementFormatButton
        format="heading-one"
        icon={<LooksOneIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="heading-two"
        icon={<LooksTwoIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="block-quote"
        icon={<FormatQuoteIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="numbered-list"
        icon={<FormatListNumberedIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="bulleted-list"
        icon={<FormatListBulletedIcon />}
        editor={editor}
      />

      <FontSizeButton
        value={increaseFontSize(leaf?.size)}
        icon={<AddIcon />}
        editor={editor}
      />

      <FontSizeInput
        value={getFontSize(leaf?.size)}
        onChange={(event) => {
          event.preventDefault();
          editor.addMark("size", event.target.value + "px");
        }}
      />
      <FontSizeButton
        value={decreaseFontSize(leaf?.size)}
        icon={<RemoveIcon />}
        editor={editor}
      />

      <ElementFormatButton
        format="left"
        icon={<AlignHorizontalLeftIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="center"
        icon={<AlignHorizontalCenterIcon />}
        editor={editor}
      />
      <ElementFormatButton
        format="right"
        icon={<AlignHorizontalRightIcon />}
        editor={editor}
      />
    </>
  );
}

export default ToolBarButtons;
