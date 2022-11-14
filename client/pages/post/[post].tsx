import axios from "axios";
import { GetServerSideProps } from "next";
import { Post } from "~/common/post/types";
import React, { useMemo } from "react";
import { withReact } from "slate-react";
import { createEditor } from "slate";
import { parseText } from "~/common/editor/transforms";
import EditorRenderer from "~/components/editor/EditorRenderer";
import LandingLayout from "~/layouts/LandingLayout";
import LandingStyles from "~/Styles/pages/index.module.scss";
import Styles from "~/Styles/pages/post.module.scss";
import { prefacePost } from "..";
import { capitalize } from "~/common/helpers";
import TagCard from "~/components/post/TagCard";

interface Props {
  post: Post;
}

function Index({ post }: Props) {
  const editor = React.useMemo(() => withReact(createEditor()), []);
  const text = useMemo(() => parseText(post), [post]);

  const prefaceBackground = React.useMemo(
    () =>
      post.image
        ? { backgroundImage: `url(data:image/*;base64,${post.image})` }
        : { background: "grey" },
    [post]
  );

  const authorName = React.useMemo(
    () =>
      `${capitalize(post?.user.firstName)} ${capitalize(post.user.lastName)}`,
    [post]
  );

  return (
    <LandingLayout>
      <section
        className={`${LandingStyles.landing_featured_post} relative`}
        style={prefaceBackground}
      >
        <div
          className={`${LandingStyles.landing_featured_post_text} flex flex-col items-center justify-center absolute fg-normal`}
        >
          <p className="text-4xl font-bold">{post.title}</p>
          <p
            className={`${LandingStyles.landing_featured_post_description__separator} text-lg`}
          >
            {prefacePost(post)}
          </p>
        </div>
      </section>

      <section className={`${Styles.post_content}`}>
        <EditorRenderer editor={editor} value={text} readonly={true} />

        <div className="flex column gap-3 flex-wrap">
          {post.tags.map((tag) => (
            <TagCard tag={tag} />
          ))}
        </div>

        <div className={`${Styles.post_content_separator}`}>
          <h1>
            By <b>{authorName}</b>
          </h1>
        </div>
      </section>
    </LandingLayout>
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
