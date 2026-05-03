package com.green_beans_apps.MyFinanceControl.TelegramWebHook.adapters.out.messagePublisher;

import com.green_beans_apps.MyFinanceControl.TelegramWebHook.application.message.ports.MessagePublisher;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.domain.message.Message;
import com.green_beans_apps.MyFinanceControl.TelegramWebHook.infrastructure.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQMessagePublisher implements MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQMessagePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(Message message) {
        System.out.println("Publicando mensagem no RabbitMQ: " + message);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                message
        );
    }
}
