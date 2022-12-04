import { NextApiRequest, NextApiResponse } from "next";
import Cookies from "cookies";
import { REFRESH_TOKEN_HEADER } from "./login";
import * as Client from "~/api/client";

/**
 * Refresh access token by refresh token from cookie
 *
 * @returns new access token
 */
export default async (req: NextApiRequest, res: NextApiResponse) => {
  const cookies: CookiesType = new Cookies(req, res);

  try {
    const payload = {
      refreshToken: cookies.get(REFRESH_TOKEN_HEADER),
    };

    const { data } = await Client.axiosAPI.post("/auth/refreshtoken", payload);

    delete data["refreshToken"];

    res.status(200).json(data);
  } catch (error) {
    const { message } = error;

    cookies.set(REFRESH_TOKEN_HEADER, "");

    res.status(403).send({ message });
  }
};
