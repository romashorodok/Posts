import React from "react";
import Header from "~/components/base/Header";
import { links, logoName, socialLinks } from "./LandingLayout";

function FormLayout({ children }: React.PropsWithChildren) {
  return (
    <>
      <Header logoName={logoName} links={links} socialLinks={socialLinks} />
      {children}
    </>
  );
}

export default FormLayout;
