package com.example.rabbitmqproducer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProducerApplicationTests {


    private static final int THREADNUM = 10;


    CountDownLatch latch=new CountDownLatch(RabbitmqProducerApplicationTests.THREADNUM);
    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Test
    public void contextLoads() {

    }

    @Test
    public void hello() throws Exception {
        ArrayBlockingQueue<Runnable> arrayWorkQueue = new ArrayBlockingQueue(RabbitmqProducerApplicationTests.THREADNUM);
        LinkedBlockingDeque<Runnable> linkedWorkQueue = new LinkedBlockingDeque<Runnable>();
        ExecutorService threadPool = new ThreadPoolExecutor(5, //corePoolSize线程池中核心线程数
                10, //maximumPoolSize 线程池中最大线程数
                60, //线程池中线程的最大空闲时间，超过这个时间空闲线程将被回收
                TimeUnit.SECONDS,//时间单位
                //下面是采用有界队列和无界队列的区别
                //arrayWorkQueue,
                linkedWorkQueue,

                //下面是jdk的四种执行策略
                //new ThreadPoolExecutor.AbortPolicy()  这种策略直接抛出异常，丢弃任务。
                //new ThreadPoolExecutor.DiscardPolicy() 这种策略和AbortPolicy几乎一样，也是丢弃任务，只不过他不抛出异常。
                //new ThreadPoolExecutor.CallerRunsPolicy() //线程调用运行该任务的 execute 本身。此策略提供简单的反馈控制机制，能够减缓新任务的提交速度。没看明白，当时是我的main线程执行的task5
                new ThreadPoolExecutor.DiscardOldestPolicy()//如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）
        );

        for (int i = 1; i <= RabbitmqProducerApplicationTests.THREADNUM ;i++){
            MyProducerThread task = new MyProducerThread("name"+i,rabbitMQProducer,latch);
            threadPool.execute(task);
        }
        latch.await();
        threadPool.shutdown();

    }




 /*       for (int i = 0; i < 1000; i++) {
            rabbitMQProducer.send();
        }
    }*/
     /*   rabbitMQProducer.send1();
        rabbitMQProducer.send2();*/
/*    Runnable a = new Runnable() {
        @Override
        public void run() {
            try {
                rabbitMQProducer.send();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    };

    Thread thread1 = new Thread(a);
    thread1.start();

    thread1.join();*/

}