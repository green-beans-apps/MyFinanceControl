package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.in;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ProcessFinancialMessageUseCase;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqFinanceMessageListener {

    ProcessFinancialMessageUseCase processFinancialMessageUseCase;

    public RabbitmqFinanceMessageListener(ProcessFinancialMessageUseCase processFinancialMessageUseCase) {
        this.processFinancialMessageUseCase = processFinancialMessageUseCase;
    }
    @RabbitListener(queues = RabbitMQConfig.MESSAGE_QUEUE)
    public void onMessage(Message message) {
        System.out.println("Recebendo mensagem do RabbitMQ: " + message.getText());
        processFinancialMessageUseCase.execute(message);
    }
 }
