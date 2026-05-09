package com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.command.FinancialCommand;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.domain.notification.Notification;

public interface FinancialNotificationPublisher {
    void publish(Notification notification);
}
