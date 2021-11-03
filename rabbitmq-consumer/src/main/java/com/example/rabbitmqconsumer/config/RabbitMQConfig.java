package com.example.rabbitmqconsumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class RabbitMQConfig {

    @Value("${server.port}")
    private int serverPort;

    final static Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

    final static String message = "topic.message";
    final static String messages = "topic.messages";
    public final static String FANOUT_EXCHANGE_NAME = "rabbitMQ-demo-fanoutExchange";


    @Bean
    public FanoutExchange fanoutExchange() {
        log.error("master commit1");
        log.error("【【【交换机实例创建成功123】】】");
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

     @Bean
    public Queue queue() throws UnknownHostException {
        return new Queue("rabbitMQ-demo" + InetAddress.getLocalHost().getHostAddress() + "-" + serverPort);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }



}
