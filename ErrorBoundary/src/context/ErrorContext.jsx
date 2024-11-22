import React, { createContext, useState, useCallback, useContext } from "react";
import ErrorPage from "../page/ErrorPage";

export const ErrorContext = createContext();

export const ErrorProvider = ({ children }) => {
  console.log("ErrorProvider 호출");
  const [error, setError] = useState(null);

  // 에러 설정 함수
  const handleError = useCallback((message) => {
    console.error("Global Error:", message); // 콘솔에 에러 로그 출력
    setError(message); // 에러 상태 설정
  }, []);

  return (
    <ErrorContext.Provider value={{ error, handleError }}>
      {error ? <ErrorPage /> : children}
    </ErrorContext.Provider>
  );
};

// 훅
export const useError = () => useContext(ErrorContext);
