package com.eoi.springwebsecurity.websockets.messages;

public class PrivateMessage {

    private String text;

    private String to;

    public String getText() {
        return text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
