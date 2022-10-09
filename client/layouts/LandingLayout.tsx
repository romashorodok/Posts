import React from "react";
import Footer from "~/components/Footer";
import { Header, Link } from "~/components/Header";

const logoName = "RUNO";

const links: Array<Link> = [
  { name: "Головна", path: "" },
  { name: "Статті", path: "" },
  { name: "Контакти", path: "" },
  { name: "Профіль", path: "" },
];

const socialLinks: Array<Link> = [
  { icon: "/assets/facebook.svg", path: "Facebook" },
  { icon: "/assets/twitter.svg", path: "Twitter" },
  { icon: "/assets/facebook.svg", path: "Youtube" },
  { icon: "/assets/twitter.svg", path: "Pinterest" },
];

export function LandingLayout({
  children,
}: React.PropsWithChildren): React.ReactElement {
  return (
    <>
      <Header logoName={logoName} links={links} socialLinks={socialLinks} />
      {children}
      <Footer socialLinks={socialLinks} />
    </>
  );
}

export default LandingLayout;
