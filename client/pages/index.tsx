import { GetServerSidePropsResult } from "next";
import LandingLayout from "~/layouts/LandingLayout";

interface Props {
  posts: Array<{ name: string }>;
}

export function Home({ posts }: Props) {
  return (
    <LandingLayout>
      {posts?.map((post: { name: string }, index) => (
        <div key={index}>
          <h1>{post.name}</h1>
        </div>
      ))}
    </LandingLayout>
  );
}

export function getServerSideProps(): GetServerSidePropsResult<Props> {
  const serverPostsResult = {
    data: [{ name: "How to craft..." }, { name: "How to build..." }],
  };

  return {
    props: {
      posts: serverPostsResult.data,
    },
  };
}

export default Home;