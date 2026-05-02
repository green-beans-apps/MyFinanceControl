package com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.in.telegram;

public class TelegramUpdate {

    private Long update_id;
    private TelegramMessage message;

    public Long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Long update_id) {
        this.update_id = update_id;
    }

    public TelegramMessage getMessage() {
        return message;
    }

    public void setMessage(TelegramMessage message) {
        this.message = message;
    }
}
