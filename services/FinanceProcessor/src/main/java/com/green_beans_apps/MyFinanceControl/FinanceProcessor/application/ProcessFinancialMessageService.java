package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialNotificationPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.MessageInterpretationException;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.notification.Notification;

public class ProcessFinancialMessageService implements ProcessFinancialMessageUseCase {

    private FinancialMessageInterpreter financialMessageInterpreter;
    private FinancialCommandPublisher financialCommandPublisher;
    private FinancialNotificationPublisher financialNotificationPublisher;

    public ProcessFinancialMessageService(FinancialMessageInterpreter financialMessageInterpreter, FinancialCommandPublisher financialCommandPublisher, FinancialNotificationPublisher financialNotificationPublisher) {
        this.financialMessageInterpreter = financialMessageInterpreter;
        this.financialCommandPublisher = financialCommandPublisher;
        this.financialNotificationPublisher = financialNotificationPublisher;
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
        } catch (MessageInterpretationException e) {
            System.err.println("Erro ao interpretar mensagem: " + e.getMessage());
            Notification notification = new Notification(Integer.valueOf(message.getUserId()), e.getMessage());
            financialNotificationPublisher.publish(notification);
        }
    }
}
