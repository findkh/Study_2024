import axios from "axios";
import Cookies from "universal-cookie";
import jwtAxios from "./customAxios";

const url = "http://localhost:8080/api/v1/";

export const getSample = async (pageNum) => {
  const path = url + "samples/list";
  const res = await jwtAxios.get(path);
  return res.data;
};

export const testAPI = () => {
  console.log("Test API!...");
};

export const makeToken = async (mid, mpw) => {
  const path = url + "token/make";
  const data = { mid, mpw };

  const res = await axios.post(path, data);
  console.log("js/data", res.data);
  return res.data;
};

const cookies = new Cookies(null, { path: "/", maxAge: 2592000 });

export const saveToken = (tokenName, tokenValue) => {
  cookies.set(tokenName, tokenValue);
};

export async function requestRefreshToken() {
  const refreshToken = cookies.get("refreshToken");
  const mid = cookies.get("mid");
  const accessToken = cookies.get("accessToken");

  if (!mid || !refreshToken || !accessToken) {
    throw Error("Cannot request refresh.");
  }

  const path = url + "token/refresh";

  const header = {
    "Content-Type": "application/x-www-form-urlencoded",
    Authorization: "Bearer " + accessToken,
  };

  const data = { refreshToken, mid };

  const res = await axios.post(path, data, { headers: header });

  return res.data;
}
