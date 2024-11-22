import { createContext, useEffect, useContext } from "react";
import { useAuth } from "./AuthProvider";
import { webStompService } from "../service/WebSocketService";
import { useError } from "./ErrorContext";

const WebSocketContext = createContext();

export const WebSocketProvider = ({ children }) => {
  const { decodedCallId } = useAuth();
  const { handleError } = useError(); // ErrorContext에서 handleError 가져오기

  useEffect(() => {
    if (decodedCallId != null) {
      console.log("WebSocket!");

      const handlers = {
        onConnect: () => {
          console.log("연결됨");
        },
        onDisconnect: () => {
          console.log("연결 해제");
        },
        onMessage: (message, destination) => {
          console.log("메시지 수신:", message, destination);
        },
        onError: (error) => {
          console.error("WebSocket 에러 발생:", error);
          handleError("WebSocket 연결 중 에러가 발생했습니다."); // ErrorContext 상태 업데이트
        },
      };

      try {
        webStompService.connect("/stomp", decodedCallId, handlers);
      } catch (error) {
        console.error("WebSocket 연결 시도 중 예외 발생:", error);
        handleError("WebSocket 초기화 실패: " + error.message); // 초기화 실패 처리
      }

      return () => {
        webStompService.disconnect();
        console.log("WebSocket 연결 해제");
      };
    }
  }, [decodedCallId, handleError]);

  const data = "테스트";
  return (
    <WebSocketContext.Provider value={data}>
      {children}
    </WebSocketContext.Provider>
  );
};

export const useWebSocket = () => useContext(WebSocketContext);
