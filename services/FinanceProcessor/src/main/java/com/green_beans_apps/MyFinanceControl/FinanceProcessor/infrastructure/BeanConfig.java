package com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure;

import com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out.RabbitmqFinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.adapters.out.RegexFinancialMessageInterpreter;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ProcessFinancialMessageService;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ProcessFinancialMessageUseCase;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialCommandPublisher;
import com.green_beans_apps.MyFinanceControl.FinanceProcessor.application.ports.FinancialMessageInterpreter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProcessFinancialMessageUseCase processFinancialMessageUseCase(FinancialMessageInterpreter financialMessageInterpreter, FinancialCommandPublisher financialCommandPublisher) {
        return new ProcessFinancialMessageService(financialMessageInterpreter, financialCommandPublisher);
    }

    @Bean
    public FinancialMessageInterpreter financialMessageInterpreter() {
        return new RegexFinancialMessageInterpreter();
    }

     @Bean
    public FinancialCommandPublisher financialCommandPublisher(RabbitTemplate rabbitTemplate) {
        return new RabbitmqFinancialCommandPublisher(rabbitTemplate);
     }
}
