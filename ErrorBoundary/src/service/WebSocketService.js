import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

const WEBSOCKET_CONSTANTS = {
  TOPICS: {
    STATUS_UPDATE: "/user/status/update",
    STATUS_INIT: "/user/status/init",
    STATUS_ERROR: "/user/status/error",
  },
  RECONNECT: {
    MAX_ATTEMPTS: 5,
    DELAY: 3000,
  },
  INACTIVITY_TIMEOUT: 5000,
};

/**
 * WebSocket 서비스 클래스
 * @class StompService
 */
class StompService {
  constructor() {
    this.client = null;
    this.onMessageHandler = null;
    this.onErrorHandler = null; // 에러 핸들러 추가
    this.reconnectAttempts = 0;
    this.inactivityTimer = null;
  }

  connect(
    endpoint,
    decodedCallId,
    { onConnect, onDisconnect, onMessage, onError }
  ) {
    if (this.client) {
      this.disconnect();
    }

    this.onMessageHandler = onMessage;
    this.onErrorHandler = onError; // 에러 핸들러 저장

    this.client = new Client({
      webSocketFactory: () => new SockJS(endpoint),
      connectHeaders: {
        callId: decodedCallId,
      },
      reconnectDelay: WEBSOCKET_CONSTANTS.RECONNECT.DELAY,
      onConnect: () => {
        console.log("[@WebSocket] 연결 성공, Call-ID:", decodedCallId);

        const subscriptionUrls = [
          `/user/status/init/${decodedCallId}`,
          `/user/status/update/${decodedCallId}`,
          `/user/status/${decodedCallId}/update`,
          `/user/status/error`,
        ];

        subscriptionUrls.forEach((url) => {
          this.client.subscribe(url, (message) => {
            if (this.onMessageHandler) {
              this.onMessageHandler(JSON.parse(message.body), url);
            }
            this.resetInactivityTimer();
          });
        });

        this.sendInitMessage(decodedCallId, { message: "Init" });
        if (onConnect) onConnect();
        this.startInactivityTimer();
      },
      onDisconnect: () => {
        console.log("[@WebSocket] 연결 해제");
        this.clearInactivityTimer();
        if (onDisconnect) onDisconnect();
      },
      onStompError: (frame) => {
        console.error("[@WebSocket] Broker Error:", frame.headers["message"]);
        console.error("Detail:", frame.body);
        this.handleError(new Error(frame.headers["message"]));
      },
      onWebSocketClose: (event) => {
        this.handleWebSocketClose(event);
      },
      onWebSocketError: (error) => {
        console.error("[@WebSocket] Error:", error);
        this.handleError(error);
      },
    });

    this.client.activate();
  }

  /**
   * WebSocket 연결 종료 처리
   * @private
   * @param {CloseEvent} event - WebSocket 종료 이벤트
   */
  handleWebSocketClose(event) {
    if (this.reconnectAttempts < WEBSOCKET_CONSTANTS.RECONNECT.MAX_ATTEMPTS) {
      console.log(
        `[@WebSocket] 연결 재시도 중... Attempt: ${this.reconnectAttempts + 1}`,
        event.reason
      );
      this.reconnectAttempts++;
    } else {
      this.handleError(new Error("Max reconnect attempts reached"));
      this.disconnect();
      console.error("[@Closed] 최대 재연결 횟수가 초과되었습니다.");
    }

    if (event.code === 1006) {
      this.handleError(new Error("WebSocket connection unexpectedly closed"));
      this.disconnect();
      console.error(
        "[@WebSocket] Check server availability and authorization."
      );
    }
  }

  /**
   * 에러를 상위로 전달
   * @private
   * @param {Error} error - 발생한 에러
   */
  handleError(error) {
    if (this.onErrorHandler) {
      this.onErrorHandler(error); // 상위에 에러 전달
    }
  }

  /**
   * 비활성 타이머 시작
   * @private
   */
  startInactivityTimer() {
    this.inactivityTimer = setTimeout(() => {
      this.onMessageHandler?.(
        this.createCallMessage("no_calls"),
        "Inactivity timeout"
      );
    }, WEBSOCKET_CONSTANTS.INACTIVITY_TIMEOUT);
  }

  /**
   * 비활성 타이머 초기화
   * @private
   */
  resetInactivityTimer() {
    this.clearInactivityTimer();
    this.startInactivityTimer();
  }

  /**
   * 비활성 타이머 제거
   * @private
   */
  clearInactivityTimer() {
    if (this.inactivityTimer) {
      clearTimeout(this.inactivityTimer);
      this.inactivityTimer = null;
    }
  }

  /**
   * WebSocket 연결 종료
   */
  disconnect() {
    console.log("[@WebSocket] 연결 해제");
    this.clearInactivityTimer();
    if (this.client) {
      this.client.deactivate();
      this.client = null;
    }
    this.reconnectAttempts = 0;
  }

  /**
   * 초기 메시지 전송
   * @param {string} callId - 콜 ID
   * @param {Object} data - 전송할 데이터
   * @private
   */
  sendInitMessage(callId, data) {
    this.sendToDestination(`/push/session/${callId}`, data);
  }

  /**
   * 지정된 destination 으로 메시지 전송
   * @param {string} destination - 메시지 전송 대상
   * @param {Object} data - 전송할 데이터
   * @private
   */
  sendToDestination(destination, data) {
    if (!this.client?.connected) {
      console.error(
        "[@WebSocket] 메시지를 전송할 수 없습니다. 소켓 연결을 확인해주세요."
      );
      return;
    }

    try {
      const body = JSON.stringify(data);
      this.client.publish({ destination, body });
    } catch (error) {
      console.error("[@WebSocket] Error sending message:", error);
    }
  }

  /**
   * 콜 메시지를 생성합니다.
   * @param {string} msgKey - 메시지 키
   * @returns {Object} 생성된 메시지
   * @private
   */
  createCallMessage(msgKey) {
    return {
      msgKey,
      tenant: "",
      contents: { output: { params: {} } },
    };
  }
}

// 싱글톤 인스턴스 생성 및 내보내기
export const webStompService = new StompService();
