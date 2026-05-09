package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialNotificationPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.notification.Notification;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitmqFinancialNotificationPublisher implements FinancialNotificationPublisher {

    private RabbitTemplate rabbitTemplate;

    public RabbitmqFinancialNotificationPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(Notification notification) {
        System.out.println("Publicando notificação no RabbitMQ: " + notification);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                notification
        );
    }
}
