package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.message.Message;

public interface ProcessFinancialMessageUseCase {
    public void execute(Message message);
}
