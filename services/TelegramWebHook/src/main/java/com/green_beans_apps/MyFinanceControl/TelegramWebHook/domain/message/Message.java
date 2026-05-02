package com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message;

import java.time.Instant;

public class Message {
    private String messageId;
    private String userId; // chat_id
    private String text;
    private Instant timestamp;
    private String firstName;
    private String lastName;

    public Message() {
    }

    public Message(String messageId, String userId, String text, Instant timestamp, String firstName, String lastName) {
        this.messageId = messageId;
        this.userId = userId;
        this.text = text;
        this.timestamp = timestamp;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

