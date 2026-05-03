package com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ports.MessagePublisher;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;

public class ProcessIncomingMessageService implements ProcessIncomingMessageUseCase {

    private MessagePublisher publisher;

    public ProcessIncomingMessageService(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void execute(Message message) throws IllegalArgumentException {
        message.validate();
        publisher.publish(message);
    }
}
