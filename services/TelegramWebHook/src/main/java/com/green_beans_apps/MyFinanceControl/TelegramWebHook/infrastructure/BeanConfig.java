package com.green_beans_apps.MyFinanceControl.TelegramWebHook.infrastructure;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageService;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageUseCase;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ports.MessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProcessIncomingMessageUseCase processIncomingMessageUseCase(
            MessagePublisher publisher) {
        return new ProcessIncomingMessageService(publisher);
    }

}
