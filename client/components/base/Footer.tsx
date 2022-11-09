import Image from "next/image";
import Styles from "~/styles/components/Footer.module.scss";
import { Link } from "./Header";

interface Props {
  socialLinks: Array<Link>;
}

export function Footer({ socialLinks }: Props) {
  return (
    <footer>
      <div className={`${Styles.contact_container}`}>
        <section className={`${Styles.section_container} flex flex-col`}>
          <p className={`${Styles.contact_heading_title} text-lg font-bold`}>
            Контакти
          </p>
          <p>maks@gmail.com</p>
          <p>+380 960 904 505</p>
        </section>
        <section className={`${Styles.section_container} flex flex-col`}>
          <p className={`${Styles.contact_heading_title} text-lg font-bold`}>
            Досліджуйте
          </p>
          <a className={`${Styles.contact_selected}`}>Статі</a>
          <a className={`${Styles.contact_selected}`}>Профіль</a>
          <a className={`${Styles.contact_selected}`}>Контакти</a>
        </section>
        <section className={`${Styles.section_container} flex flex-col`}>
          <p className={`${Styles.contact_heading_title} text-lg font-bold`}>
            Наш офіс
          </p>
          <p>вул. Українська, буд.2,</p>
          <p>54220, Чернівці,</p>
          <p>Україна</p>
        </section>
        <section className={`${Styles.section_container} flex flex-col`}>
          <p className={`${Styles.contact_heading_title} text-lg font-bold`}>
            Соціальні мережі
          </p>
          <div
            className={`${Styles.social_icons_container} flex flex-wrap gap-2`}
          >
            {socialLinks?.map((link: Link, index) => (
              <a href={link.path} key={index}>
                <Image src={link.icon} alt={link.name} width={25} height={25} />
              </a>
            ))}
          </div>
        </section>
      </div>
      <div className={`${Styles.subfooter_container} fg-normal`}>
        <p>2022 | RUNO</p>
      </div>
    </footer>
  );
}

export default Footer;
