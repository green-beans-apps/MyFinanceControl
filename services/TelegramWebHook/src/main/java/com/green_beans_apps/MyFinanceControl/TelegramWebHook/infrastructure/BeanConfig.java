package com.green_beans_apps.MyFinanceControl.TelegramWebHook.infrastructure;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.out.messagePublisher.RabbitMQMessagePublisher;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageService;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ProcessIncomingMessageUseCase;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ports.MessagePublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProcessIncomingMessageUseCase processIncomingMessageUseCase(
            MessagePublisher publisher) {
        return new ProcessIncomingMessageService(publisher);
    }

    @Bean
    public MessagePublisher messagePublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitMQMessagePublisher(rabbitTemplate);
    }
}
