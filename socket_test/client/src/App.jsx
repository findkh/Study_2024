import React, { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { COMPONENT_MAP } from "./component/COMPONENT_MAP"; // COMPONENT_MAP 임포트

function App() {
  const [stompClient, setStompClient] = useState(null);
  const [CurrentComponent, setCurrentComponent] = useState(null); // 대문자로 시작하는 변수명

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

        // 메시지 처리: 서버 응답에 따라 페이지 이동
        const { status, page } = JSON.parse(message.body); // 응답 메시지 파싱
        console.log("Parsed message:", { status, page }); // 파싱된 메시지 로깅

        // COMPONENT_MAP을 사용하여 해당 페이지 컴포넌트로 변경
        if (COMPONENT_MAP[page]) {
          setCurrentComponent(() => COMPONENT_MAP[page]); // 매핑된 컴포넌트를 함수형으로 설정
        } else {
          console.warn(`페이지 '${page}'에 해당하는 컴포넌트가 없습니다.`);
        }
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
    const message = { callId: "1234" };
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
      {/* 현재 컴포넌트가 있으면 렌더링 */}
      {CurrentComponent && <CurrentComponent />}
    </div>
  );
}

export default App;
