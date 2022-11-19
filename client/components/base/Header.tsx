import React from "react";
import Image from "next/image";
import Styles from "~/Styles/components/Header.module.scss";
import NextLink from "next/link";

export interface Link {
  name?: string;
  path: string;
  icon?: string;
}

interface Props {
  logoName: string;
  links: Array<Link>;
  socialLinks: Array<Link>;
  float?: boolean;
}

export function Header({
  links,
  socialLinks,
  logoName,
  float = true,
}: Props): React.ReactElement {
  return (
    <header
      className={`${Styles.header_container} ${
        float ? "fixed" : "static"
      } flex flex-row justify-between`}
    >
      <NextLink href={"/"} className="text-base">
        {logoName}
      </NextLink>
      <div className={`${Styles.navigation_container} flex flex-row`}>
        <section className="flex gap-4">
          {links?.map((link: Link, index) => (
            <NextLink href={link.path} key={index}>
              {link.name}
            </NextLink>
          ))}
        </section>
        <section className="flex gap-4">
          {socialLinks?.map((link: Link, index) => (
            <NextLink href={link.path} key={index}>
              <Image
                className="social_icon"
                src={link.icon}
                alt={link.name}
                width={25}
                height={25}
              />
            </NextLink>
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
