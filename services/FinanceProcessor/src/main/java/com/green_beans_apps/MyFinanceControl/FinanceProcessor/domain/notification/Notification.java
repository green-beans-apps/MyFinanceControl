package com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.notification;

public class Notification {
    private Integer userId;
    private String message;

    public Notification() {
    }

    public Notification(Integer userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
