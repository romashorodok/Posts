import { GetServerSidePropsResult } from "next";
import Styles from "~/Styles/pages/index.module.scss";
import LandingLayout from "~/layouts/LandingLayout";

interface Props {
  posts: Array<{ name: string }>;
}

export function Home({ posts }: Props) {
  return (
    <LandingLayout>
      <div
        className={`${Styles.landing_background} flex flex-col justify-center`}
      >
        <div className={`${Styles.landing_text} text-4xl font-bold`}>
          <p>Runo - це все, що потрібно</p>
          <p>вам для комфортного</p>
          <p>проводження часу</p>
        </div>
      </div>

      <div className={`${Styles.landing_recent_posts}`}></div>
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
