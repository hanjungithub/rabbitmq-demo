package com.example.rabbitmqproducer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    final static Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

    final static String message = "topic.message";
    final static String messages = "topic.messages";
    public final static String FANOUT_EXCHANGE_NAME = "rabbitMQ-demo-fanoutExchange";

    @Bean
    public FanoutExchange fanoutExchange() {
        log.error("【【【交换机实例创建成功】】】");
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

     @Bean
    public Queue Queue() {
        return new Queue("rabbitMQ-demo");
    }
   @Bean
    public Queue Queue1() {
        return new Queue("rabbitMQ-demo-domain");
    }

    @Bean
    public Queue queueMessage() {
        return new Queue(RabbitMQConfig.message);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(RabbitMQConfig.messages);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


}
