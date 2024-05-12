package com.example.bloodlink;

public class Message {
    private String text;
    private String sender;
    private String time;

    public Message() {

    }

    public Message(String text, String sender, String time) {
        this.text = text;
        this.sender = sender;
        this.time = time;
    }

    // Getters and setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

