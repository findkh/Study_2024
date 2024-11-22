package com.kh.demoapi.model;

public class MessageResponse {

    private String msgKey;
    private Contents contents;

    // Getters and Setters

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
