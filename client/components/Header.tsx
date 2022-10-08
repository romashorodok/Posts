import React from "react";
import Image from "next/image";
import Styles from "~/Styles/components/Header.module.scss";

export interface Link {
  name?: string;
  path: string;
  icon?: string;
}

interface Props {
  logoName: string;
  links: Array<Link>;
  socialLinks: Array<Link>;
}

export function Header({
  links,
  socialLinks,
  logoName,
}: Props): React.ReactElement {
  return (
    <header
      className={`${Styles.header_container} flex flex-row justify-between`}
    >
      <h1>{logoName}</h1>
      <div className={`${Styles.navigation_container} flex flex-row`}>
        <section className="flex gap-4">
          {links?.map((link: Link, index) => (
            <a href={link.path} key={index}>
              {link?.name}
            </a>
          ))}
        </section>
        <section className="flex gap-4">
          {socialLinks?.map((link: Link, index) => (
            <a href={link.path} key={index}>
              <Image
                className={Styles.social_icon}
                src={link.icon}
                alt={link.name}
                width={25}
                height={25}
              />
            </a>
          ))}
        </section>
        <section>
          <button>Вхід</button>
        </section>
      </div>
    </header>
  );
}

export default Header;
