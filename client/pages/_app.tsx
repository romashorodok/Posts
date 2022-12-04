import "~/styles/globals.css";
import "~/styles/common.scss";
import type { AppProps } from "next/app";
import Axios from "axios";
import React, { useState } from "react";
import {
  DehydratedState,
  Hydrate,
  QueryClient,
  QueryClientProvider,
} from "react-query";
import { AuthContextProvider } from "~/contexts/auth-context";

Axios.defaults.baseURL = "http://localhost:8080/api";
Axios.defaults.withCredentials = true;

type AppPropsExtended = AppProps & {
  pageProps: { dehydratedState: DehydratedState; [key: string]: unknown };
};

function MyApp({ Component, pageProps }: AppPropsExtended) {
  const [queryClient] = useState(() => new QueryClient());

  return (
    <QueryClientProvider client={queryClient}>
      <Hydrate state={pageProps.dehydratedState}>
        <AuthContextProvider>
          <Component {...pageProps} />
        </AuthContextProvider>
      </Hydrate>
    </QueryClientProvider>
  );
}

export default MyApp;
