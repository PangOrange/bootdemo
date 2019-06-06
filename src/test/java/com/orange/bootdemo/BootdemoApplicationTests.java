package com.orange.bootdemo;

import com.orange.bootdemo.redis.RedisSerializer;
import com.orange.bootdemo.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootdemoApplicationTests {

    @Autowired
    RedisService redisService;

    @Test
    public void contextLoads() {
        redisService.set("a", "b");
        // 测试多线程同时取redis值
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(countDownLatch)).start();
            countDownLatch.countDown();
        }
    }

    // 测试存储redis、序列化对象
    @Test
    public void redisSerializer() {
        redisService.set("serializer", new RedisSerializer());
        System.out.println(redisService.get("serializer"));
    }


    class Worker implements Runnable {

        CountDownLatch countDownLatch;

        Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await(); // 等待其它线程
                System.out.println( "redis:" + redisService.get("a")+ Thread.currentThread().getName() + "启动@" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
