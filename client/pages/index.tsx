import React from "react";
import { GetServerSideProps } from "next";
import Styles from "~/Styles/pages/index.module.scss";
import LandingLayout from "~/layouts/LandingLayout";
import axios from "axios";
import { Node } from "slate";
import { parseText } from "~/common/editor/transforms";
import { Post, Tag } from "~/common/post/types";
import { capitalize } from "~/common/helpers";
import PostCard from "~/components/post/PostCard";

interface Props {
  posts: Array<Post>;
  tags: Array<Tag>;
  featuredPost: Post;
}

export function prefacePost(post: Post): string {
  return parseText(post)
    .slice(0, 3)
    .map((item) => Node.string(item))
    .join(" ");
}

export function Home({ posts, tags, featuredPost }: Props) {
  const [selectedTag, setSelectedTag] = React.useState<number>(0);
  const [_posts, setPosts] = React.useState(posts);

  const featuredPostBackground = React.useMemo(
    () =>
      featuredPost.image
        ? { backgroundImage: `url(data:image/*;base64,${featuredPost.image})` }
        : { background: "grey" },

    [featuredPost]
  );

  React.useEffect(() => {
    (async () => {
      const tag = tags[selectedTag];
      const { data: posts } = await axios.get(
        `/post/recent?tag=${tag.name}&size=8`
      );
      setPosts(posts);
    })();
  }, [selectedTag]);

  return (
    <LandingLayout>
      <section
        className={`${Styles.landing_background} flex flex-col justify-center`}
      >
        <div className={`${Styles.landing_text} text-4xl font-bold`}>
          <p>Runo - це все, що потрібно</p>
          <p>вам для комфортного</p>
          <p>проводження часу</p>
        </div>
      </section>

      <section className={`${Styles.landing_recent_posts}`}>
        <h1 className="text-4xl font-bold mb-10">Нещодавні пости</h1>

        <div className="flex flex-row justify-between flex-wrap mb-10 bg-support">
          <ul className="flex flex-row flex-wrap gap-4">
            {tags.map((category: Tag, index) => (
              <li
                key={index}
                className={`${
                  index === selectedTag ? Styles.landing_category__selected : ""
                } ${Styles.landing_category}`}
                onClick={() => setSelectedTag(index)}
              >
                {capitalize(category.name)}
              </li>
            ))}
          </ul>

          <a className={Styles.landing_category}>Переглянути все</a>
        </div>

        <div className={`${Styles.landing_post_cards} grid`}>
          {_posts.map((post: Post, index) => (
            <PostCard key={index} post={post} />
          ))}
        </div>
      </section>

      <section
        className={`${Styles.landing_featured_post} relative`}
        style={featuredPostBackground}
      >
        <div
          className={`${Styles.landing_featured_post_text} flex flex-col items-center justify-center absolute fg-normal`}
        >
          <p className="text-4xl font-bold">{featuredPost.title}</p>
          <p
            className={`${Styles.landing_featured_post_description__separator} text-lg`}
          >
            {prefacePost(featuredPost)}
          </p>
          <p>{new Date(featuredPost.createdAt).toDateString()}</p>
        </div>
      </section>
    </LandingLayout>
  );
}

export const getServerSideProps: GetServerSideProps<Props> = async () => {
  const { data: posts } = await axios.get(`/post/recent?tag=all&size=8`);
  const { data: tags }: { data: Array<Tag> } = await axios.get("/tag/");
  const { data: featuredPost } = await axios.get("/post/most-liked/");
  tags.unshift({ name: "all" });

  return {
    props: {
      posts,
      tags,
      featuredPost,
    },
  };
};

export default Home;
