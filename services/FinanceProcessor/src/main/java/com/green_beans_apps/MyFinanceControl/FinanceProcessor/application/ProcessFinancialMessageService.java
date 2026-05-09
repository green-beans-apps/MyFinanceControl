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
        try {
            FinancialCommand financialCommand = financialMessageInterpreter.interpretMessage(message);

            System.out.println("Valor: " + financialCommand.getAmount());
            System.out.println("User id: " + financialCommand.getUserId());
            System.out.println("Descricao: " + financialCommand.getDescription());
            System.out.println("Operacao: " + financialCommand.getType().name());
            financialCommandPublisher.publish(financialCommand);
        } catch (IllegalArgumentException e) {
            // TODO: politica de envio de mensagem avisando o usuario que a mensagem não foi entendida
            System.err.println("Erro ao interpretar mensagem: " + e.getMessage());
        }
    }
}
