package com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out.RabbitmqFinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out.RabbitmqFinancialNotificationPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out.RegexFinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ProcessFinancialMessageService;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ProcessFinancialMessageUseCase;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialNotificationPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProcessFinancialMessageUseCase processFinancialMessageUseCase(FinancialMessageInterpreter financialMessageInterpreter, FinancialCommandPublisher financialCommandPublisher, FinancialNotificationPublisher  financialNotificationPublisher) {
        return new ProcessFinancialMessageService(financialMessageInterpreter, financialCommandPublisher, financialNotificationPublisher);
    }

    @Bean
    public FinancialMessageInterpreter financialMessageInterpreter() {
        return new RegexFinancialMessageInterpreter();
    }

    @Bean
    public FinancialCommandPublisher financialCommandPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitmqFinancialCommandPublisher(rabbitTemplate);
    }

    @Bean
    public FinancialNotificationPublisher financialNotificationPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitmqFinancialNotificationPublisher(rabbitTemplate);
    }
}
