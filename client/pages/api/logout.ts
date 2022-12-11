import { NextApiRequest, NextApiResponse } from "next";
import { REFRESH_TOKEN_HEADER } from "./login";
import Cookies from "cookies";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const cookies: CookiesType = new Cookies(req, res);

  cookies.set(REFRESH_TOKEN_HEADER, "");

  //TODO: backend black list or remove token

  res.status(200).send({});
};
