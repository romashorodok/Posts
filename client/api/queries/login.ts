import { serverClient } from "../serverClient";

export default (credentials: Credentials) => {
  return serverClient.post("/api/login", credentials);
};
