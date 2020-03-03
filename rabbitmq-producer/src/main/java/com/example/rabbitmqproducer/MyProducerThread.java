package com.example.rabbitmqproducer;

import java.util.concurrent.CountDownLatch;

public class MyProducerThread extends Thread{

    public String threadName;
    public RabbitMQProducer rabbitMQProducer;
    public CountDownLatch countDownLatch;

    public MyProducerThread (String threadName,RabbitMQProducer rabbitMQProducer,CountDownLatch countDownLatch){
        this.threadName=threadName;
        this.rabbitMQProducer = rabbitMQProducer;
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        try {
            rabbitMQProducer.send();
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
