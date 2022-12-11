import React from "react";
import Styles from "~/styles/layouts/editor.module.scss";
import Header from "~/components/base/Header";
import { links, logoName, socialLinks } from "./LandingLayout";
import { BaseProps } from "~/common/types";

interface EditorLayoutProps extends BaseProps {
  headerClassName?: string;

  children?: React.ReactNode;
}

export function EditorLayout({
  children,
  headerClassName,
}: EditorLayoutProps): React.ReactElement {
  return (
    <div className={`${Styles.editor_layout} flex flex-col`}>
      <Header
        className={headerClassName}
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
