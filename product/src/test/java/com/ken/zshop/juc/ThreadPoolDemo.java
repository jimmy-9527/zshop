package com.ken.zshop.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        // ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        // ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
//
//        // ExecutorService executorService = Executors.newCachedThreadPool();
//        try {
//            for (int i = 0; i < 10 ; i++) {
//                executorService.execute(()->{
//                    log.info("Executors创建线程池的方式实现多线程.......");
//                    // 业务代码执行
//                    int j = 100/3;
//                    log.info("业务代码执行结果：{}",j);
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            executorService.shutdown();
//        }

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                9,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 10 ; i++) {

                threadPool.execute(()->{
                    log.info("Executors创建线程池的方式实现多线程.......");
                    // 业务代码执行
                    int j = 100/3;
                    log.info("业务代码执行结果：{}",j);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
