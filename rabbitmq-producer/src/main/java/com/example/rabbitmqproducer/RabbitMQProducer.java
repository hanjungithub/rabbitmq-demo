package com.example.rabbitmqproducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RabbitMQProducer{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() throws InterruptedException {
       // Thread.sleep(2000);
        String context = "hello " + new Date();
       System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("rabbitMQ-demo", context);
      /*  User user = new User();
        user.setName(context);
        System.out.println("User : " + context);
        this.rabbitTemplate.convertAndSend("rabbitMQ-demo-domain", context);*/

    }


    public void send1() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void send2() {
        String context = "hi, i am messages 2";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
    }

}
