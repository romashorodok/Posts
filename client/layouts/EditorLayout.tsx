import React from "react";
import Styles from "~/styles/layouts/editor.module.scss";
import Header from "~/components/base/Header";
import { links, logoName, socialLinks } from "./LandingLayout";

export function EditorLayout({
  children,
}: React.PropsWithChildren): React.ReactElement {
  return (
    <div className={`${Styles.editor_layout} flex flex-col`}>
      <Header
        float={false}
        logoName={logoName}
        links={links}
        socialLinks={socialLinks}
      />
      {children}
    </div>
  );
}

export default EditorLayout;
