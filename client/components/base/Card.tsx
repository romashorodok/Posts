import React from "react";
import Image from "next/image";
import Styles from "~/styles/components/Card.module.scss";
import { BaseProps } from "~/common/types";

interface Props extends BaseProps {
  image?: string;
  onClick?: ((event: React.MouseEvent) => void) | undefined;
}

const defaultImage = "/assets/post_mock.jpg";

export function Card({
  image,
  children,
  className,
  onClick,
}: React.PropsWithChildren<Props>) {
  return (
    <div className={className} onClick={onClick}>
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
