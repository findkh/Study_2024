import { createContext, useEffect, useState, useContext } from "react";
import { useParams } from "react-router-dom";
import { validateCallId as apiValidateCallId } from "../api/auth";
import { useError } from "../context/ErrorContext"; // useError import

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

// 사용자 인증을 요청함
const AuthProvider = ({ children }) => {
  const { callId } = useParams();
  const { handleError } = useError(); // useError로 handleError 가져오기
  const [isLoading, setIsLoading] = useState(true);
  const [decodedCallId, setDecodedCallId] = useState();

  useEffect(() => {
    const validateCallId = async () => {
      try {
        const decoded = await apiValidateCallId(callId);
        setDecodedCallId(decoded);
      } catch (error) {
        handleError(error.message); // 에러 발생 시 ErrorContext로 에러 전달
      } finally {
        setIsLoading(false); // 로딩 완료
      }
    };

    if (callId) {
      validateCallId(); // API 호출
      console.log("인증 완료");
    }
  }, [callId, handleError]);

  return (
    <AuthContext.Provider value={{ decodedCallId, isLoading }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
