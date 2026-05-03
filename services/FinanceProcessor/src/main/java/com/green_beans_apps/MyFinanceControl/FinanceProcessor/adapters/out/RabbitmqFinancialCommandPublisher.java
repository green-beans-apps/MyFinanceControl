package com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;

public class RabbitmqFinancialCommandPublisher implements FinancialCommandPublisher {

    @Override
    public void publish(FinancialCommand command) {
        System.out.println("Publicando comando no RabbitMQ: " + command.getDescription());
    }
}
