import axios from "axios";
import Cookies from "universal-cookie";
import { requestRefreshToken, saveToken } from "./api";

const jwtAxios = axios.create();

const cookies = new Cookies(null, { path: "/", maxAge: 2592000 });

const beforeRequest = (config) => {
  console.log("beforeRequest");

  const accessToken = cookies.get("accessToken");

  if (!accessToken) {
    throw Error("No Token");
  }

  config.headers["Authorization"] = "Bearer " + accessToken;

  return config;
};

const beforeResponse = (response) => {
  console.log("beforeResponse");
  return response;
};

const errorResponse = async (error) => {
  const status = error.response.status;
  const res = error.response.data;
  const errorMsg = res.error;

  console.log(status, res, errorMsg);

  if (errorMsg.indexOf("expired") > -1) {
    console.log("Refresh Token");

    try {
      const data = await requestRefreshToken();
      console.log(data);

      // 토큰 저장
      saveToken("accessToken", data.accessToken);
      saveToken("refreshToken", data.refreshToken);

      // 기존 요청 헤더에 새로운 토큰 설정
      error.config.headers["Authorization"] = "Bearer " + data.accessToken;

      // 원래 요청 재시도
      return await axios(error.config);
    } catch (refreshError) {
      return Promise.reject(refreshError);
    }
  } else {
    return Promise.reject(error);
  }
};

// 요청 인터셉터
jwtAxios.interceptors.request.use(beforeRequest);

// 응답 인터셉터
jwtAxios.interceptors.response.use(beforeResponse, errorResponse);

export default jwtAxios;
