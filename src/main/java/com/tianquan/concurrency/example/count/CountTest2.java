package com.tianquan.concurrency.example.count;

import com.tianquan.concurrency.annotations.NotThreadSafe;
import com.tianquan.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class CountTest2 {
    //        total request
    public static int clientTotal = 5000;
    //      total concurrent threads number
    public static int threadTotal = 200;

//    For AtomicInteger, need to change this
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute( () -> {
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch(Exception e){
                    log.error("Exception happened", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //    For AtomicInteger, need to change this
        log.info("Count:{}",count.get());
    }

    private static void add() {
        //    For AtomicInteger, need to change this
        count.incrementAndGet();
    }
}
