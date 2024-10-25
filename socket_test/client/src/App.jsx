import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

function App() {
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    // SockJS 및 STOMP 클라이언트 초기화
    const socket = new SockJS("http://localhost:8080/stomp");
    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000, // 재연결 지연 시간 설정 (5초)
      debug: (msg) => console.log(new Date(), msg),
    });

    client.onConnect = (frame) => {
      console.log("WebSocket 연결 성공:", frame);
      client.subscribe("/topic/messages", (message) => {
        console.log("서버에서 수신한 메시지:", message.body);
      });
    };

    client.onStompError = (frame) => {
      console.error("STOMP 오류:", frame.headers["message"], frame.body);
    };

    client.activate(); // STOMP 클라이언트 활성화
    setStompClient(client);

    return () => {
      if (client) {
        client.deactivate().then(() => console.log("WebSocket 연결 해제 완료"));
      }
    };
  }, []);

  // 메시지 전송 함수
  const sendMessage = () => {
    const message = { callId: "" };
    if (stompClient && stompClient.connected) {
      stompClient.publish({
        destination: "/app/send",
        body: JSON.stringify(message),
      });
      console.log("메시지 전송:", message);
    } else {
      console.error("WebSocket 연결 실패. 메시지 전송 불가");
    }
  };

  return (
    <div className="App">
      <h1>WebSocket 연결 테스트</h1>
      <button onClick={sendMessage}>서버로 메시지 전송</button>
    </div>
  );
}

export default App;
