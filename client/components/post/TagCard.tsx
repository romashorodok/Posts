import { capitalize } from "~/common/helpers";
import { Tag } from "~/common/post/types";
import Styles from "~/Styles/components/post/TagCard.module.scss";

interface Props {
  tag: Tag;
}

function TagCard({ tag }: Props) {
  return (
    <div className={`${Styles.tagCard_container} rounded`}>
      <h1>{capitalize(tag.name)}</h1>
    </div>
  );
}

export default TagCard;
