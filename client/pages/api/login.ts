import { NextApiRequest, NextApiResponse } from "next";
import Cookies from "cookies";
import { apiClient } from "~/api/serverClient";

export const REFRESH_TOKEN_HEADER = "_refresh-token";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { body } = req;
  const cookies: CookiesType = new Cookies(req, res);

  try {
    const req = await apiClient.post("/auth/signin", body);
    const { data } = req;
    const { refreshToken } = data;

    delete data["refreshToken"];

    cookies.set(REFRESH_TOKEN_HEADER, refreshToken, {
      httpOnly: true,
      sameSite: "lax",
      maxAge: 86400000,
    });

    res.send(data);
  } catch (e) {
    res.status(419).json(e.response.data);
  }
};
