import React from "react";
import { GetServerSidePropsResult } from "next";
import Styles from "~/Styles/pages/index.module.scss";
import LandingLayout from "~/layouts/LandingLayout";
import Card from "~/components/Card";

type Category = {
  name: string;
};

type Post = {
  title: string;
  created_at: string;
  description: string;
};

interface Props {
  posts: Array<Post>;
  categories: Array<Category>;
}

export function Home({ posts, categories }: Props) {
  const [selectedCategory, setSelectedCategory] = React.useState(0);

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

      <div className={`${Styles.landing_recent_posts}`}>
        <h1 className="text-4xl font-bold mb-10">Нещодавні пости</h1>

        <div className="flex flex-row justify-between flex-wrap mb-10 bg-support">
          <ul className="flex flex-row flex-wrap gap-4">
            {categories.map((category: Category, index) => (
              <li
                key={index}
                className={`${
                  index === selectedCategory
                    ? Styles.landing_category__selected
                    : ""
                } ${Styles.landing_category}`}
                onClick={() => setSelectedCategory(index)}
              >
                {category.name}
              </li>
            ))}
          </ul>

          <a className={Styles.landing_category}>Переглянути все</a>
        </div>

        <div
          className={`${Styles.landing_post_cards} flex flex-row flex-wrap gap-5`}
        >
          {posts.map((post: Post) => (
            <Card>
              <p className="text-xs bg-minor">{post.created_at}</p>
              <p className="text-lg font-bold">{post.title}</p>
              <p className="text-xs bg-minor">{post.description}</p>
            </Card>
          ))}
        </div>
      </div>
    </LandingLayout>
  );
}

export function getServerSideProps(): GetServerSidePropsResult<Props> {
  const serverPostsResult = {
    data: [
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
      {
        title: "Моя мрія відвідати Париж цього року",
        description:
          "Lorem ipsum dolor sit amet, consectetur adipiscing  elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        created_at: "04.10.2022",
      },
    ],
  };
  const serverCategoriesResult = {
    data: [
      { name: "Всі" },
      { name: "Пригоди" },
      { name: "Подорож" },
      { name: "Мода" },
      { name: "Технології" },
      { name: "Наука" },
    ],
  };

  return {
    props: {
      posts: serverPostsResult.data,
      categories: serverCategoriesResult.data,
    },
  };
}

export default Home;
