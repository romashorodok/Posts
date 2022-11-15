import { useMemo } from "react";
import { Post } from "~/common/post/types";
import Styles from "~/Styles/components/post/PostCard.module.scss";
import Card from "~/components/base/Card";
import { prefacePost } from "~/pages";
import { useRouter } from "next/router";

interface Props {
  post: Post;
}

function PostCard({ post }: Props) {
  const router = useRouter();

  const image = useMemo(
    () => (post.image ? `data:image/*;base64,${post.image}` : null),
    [post]
  );

  const onClick = () => router.push(`/post/${post.id}`);

  return (
    <Card
      image={image}
      className={`${Styles.post_card_containere}`}
      onClick={onClick}
    >
      <p className="text-xs bg-minor">
        {new Date(post.createdAt).toDateString()}
      </p>
      <span
        className={`${Styles.post_card_title__animation} text-lg font-bold`}
      >
        {post.title}
      </span>
      <p className={`${Styles.post_card_description} text-xs bg-minor`}>
        {prefacePost(post)}
      </p>
    </Card>
  );
}

export default PostCard;
