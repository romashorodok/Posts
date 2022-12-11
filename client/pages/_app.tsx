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
} from "@tanstack/react-query";
import { AuthContextProvider } from "~/contexts/auth-context";
import { InterceptorContextProvider } from "~/contexts/interceptor-context";

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
          <InterceptorContextProvider>
            <Component {...pageProps} />
          </InterceptorContextProvider>
        </AuthContextProvider>
      </Hydrate>
    </QueryClientProvider>
  );
}

export default MyApp;
