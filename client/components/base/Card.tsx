import React from "react";
import Image from "next/image";
import Styles from "~/styles/components/Card.module.scss";

interface Props {
  image?: string;
}

const defaultImage = "/assets/post_mock.jpg";

export function Card({ image, children }: React.PropsWithChildren<Props>) {
  return (
    <div>
      <Image
        className="rounded"
        src={image ? image : defaultImage}
        width="100%"
        height="100%"
        layout="responsive"
        objectFit="contain"
      />
      <div className={Styles.card_content}>{children}</div>
    </div>
  );
}

export default Card;
