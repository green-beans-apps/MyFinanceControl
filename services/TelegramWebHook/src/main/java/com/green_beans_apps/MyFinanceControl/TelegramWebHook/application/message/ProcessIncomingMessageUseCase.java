package com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;

public interface ProcessIncomingMessageUseCase {
    public void execute(Message message);
}
