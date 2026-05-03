package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;

public interface FinancialMessageInterpreter {
    public FinancialCommand interpretMessage(Message message);
}
