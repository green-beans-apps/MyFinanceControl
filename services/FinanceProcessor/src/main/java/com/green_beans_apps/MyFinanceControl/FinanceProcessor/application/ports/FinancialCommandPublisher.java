package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;

public interface FinancialCommandPublisher {
    void publish(FinancialCommand command);
}
