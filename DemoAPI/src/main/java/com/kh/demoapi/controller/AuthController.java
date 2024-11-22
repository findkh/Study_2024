package com.kh.demoapi.controller;

import com.kh.demoapi.model.Contents;
import com.kh.demoapi.model.Input;
import com.kh.demoapi.model.MessageResponse;
import com.kh.demoapi.model.Params;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/call")
@RestController
public class AuthController {

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestHeader("Authorization") String callId) {
        System.out.println("auth 호출됨");
        // 시스템에서 받은 콜아이디를 출력
        System.out.println("Received callId: " + callId);

        // MessageResponse 객체 생성
        MessageResponse response = new MessageResponse();
        response.setMsgKey("home");

        // Contents 객체 생성
        Contents contents = new Contents();
        Input input = new Input();
        input.setCallId(callId);  // 받은 콜아이디 설정
        input.setTenant("BMH");

        Params params = new Params();
        params.setPATNO("00361243");
        input.setParams(params);

        contents.setInput(input);
        response.setContents(contents);

        // ResponseEntity로 JSON 형태로 반환
//        return ResponseEntity.ok(response);
        return ResponseEntity.badRequest().build();
    }

}
