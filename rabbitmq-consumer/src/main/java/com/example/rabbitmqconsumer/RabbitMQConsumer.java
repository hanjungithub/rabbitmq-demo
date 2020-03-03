package com.example.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "rabbitMQ-demo")
//@RabbitListener(queues = "rabbitMQ-demo-domain")
public class RabbitMQConsumer {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello + ",receiverDate："+new Date());
    }
  /*  @RabbitHandler
    public void process1(Object user) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Receiver-User  : " + user);
    }*/
}
