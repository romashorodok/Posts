import axios from "axios";

export const axiosAPI = axios.create({
  baseURL: "http://localhost:8080/api",
});

export const axiosSSR = axios.create({
  baseURL: "http://localhost:3000/",
});
