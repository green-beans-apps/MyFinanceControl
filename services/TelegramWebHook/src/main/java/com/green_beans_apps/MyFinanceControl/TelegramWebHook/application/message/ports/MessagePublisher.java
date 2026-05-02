package com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ports;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;

public interface MessagePublisher {
    public void publish(Message message);
}
