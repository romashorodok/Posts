import React from "react";
import Image from "next/image";
import Styles from "~/styles/components/Card.module.scss";

interface Props {
  image?: string;
}

export function Card({
  image = "/assets/post_mock.jpg",
  children,
}: React.PropsWithChildren<Props>) {
  return (
    <div className={`${Styles.card_container}`}>
      <Image className="rounded" src={image} width={310} height={280} />
      <div className={Styles.card_content}>{children}</div>
    </div>
  );
}

export default Card;
