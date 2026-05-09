package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitmqFinancialCommandPublisher implements FinancialCommandPublisher {

    private  RabbitTemplate rabbitTemplate;

    public RabbitmqFinancialCommandPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(FinancialCommand command) {
        System.out.println("Publicando comando no RabbitMQ: " + command.getDescription());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.COMMAND_ROUTING_KEY,
                command
        );
    }
}
