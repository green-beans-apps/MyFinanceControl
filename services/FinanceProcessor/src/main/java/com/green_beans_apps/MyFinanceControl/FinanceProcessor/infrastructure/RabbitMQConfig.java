package com.green_beans_apps.MyFinanceControl.FinanceProcessor.infrastructure;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "finance.exchange";

    public static final String MESSAGE_QUEUE = "finance.message";
    public static final String MESSAGE_ROUTING_KEY = "finance.message";

    public static final String COMMAND_QUEUE = "finance.command";
    public static final String COMMAND_ROUTING_KEY = "finance.command";

    public static final String NOTIFICATION_QUEUE = "finance.notification";
    public static final String NOTIFICATION_ROUTING_KEY = "finance.notification";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // Configurando fila de mensagens
    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE, true); // durable
    }

    @Bean
    public Binding messageBinding() {
        return BindingBuilder
                .bind(messageQueue())
                .to(exchange())
                .with(MESSAGE_ROUTING_KEY);
    }

    // Configurando fila de comandos
    @Bean
    public Queue commandQueue() {
        return new Queue(COMMAND_QUEUE, true); // durable
    }

    @Bean
    public Binding commandBinding() {
        return BindingBuilder
                .bind(commandQueue())
                .to(exchange())
                .with(COMMAND_ROUTING_KEY);
    }

    // Configurando fila de notificacao
    @Bean
    public Queue notificationQueue(){
        return new Queue(NOTIFICATION_QUEUE, true);
    }

    @Bean
    public Binding notificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(exchange())
                .with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory,
            MessageConverter messageConverter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
