package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;

public class ProcessFinancialMessageService implements ProcessFinancialMessageUseCase {

    private FinancialMessageInterpreter financialMessageInterpreter;
    private FinancialCommandPublisher financialCommandPublisher;

    public ProcessFinancialMessageService(FinancialMessageInterpreter financialMessageInterpreter, FinancialCommandPublisher financialCommandPublisher) {
        this.financialMessageInterpreter = financialMessageInterpreter;
        this.financialCommandPublisher = financialCommandPublisher;
    }

    @Override
    public void execute(Message message) {
        FinancialCommand financialCommand = financialMessageInterpreter.interpretMessage(message);
        System.out.println("Processing financial message: " + financialCommand.getAmount());
        financialCommandPublisher.publish(financialCommand);
    }
}
