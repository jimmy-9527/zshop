package com.ken.zshop.juc;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class ThreadDemo {

    public static class Thread01 extends Thread {
        @Override
        public void run() {
            log.info("继承Thread 实现方式.......");
            int i = 100 / 3;
            log.info("业务代码执行结果：{}, 线程名称:{}, 线程ID: {}", i, this.getName(), this.getId());
        }
    }

    public static class Thread02 implements Runnable {
        @Override
        public void run() {
            log.info("继承Thread 实现方式.......");
            int i = 100 / 3;
            log.info("业务代码执行结果：{}", i);
        }
    }

    public static class Thread03 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            log.info("继承Thread实现方式.......");
            // 业务代码执行
            int i = 100/3;
            log.info("业务代码执行结果：{}",i);
            return  i;
        }
    }

    public static void main(String[] args) {
        System.out.println("thread............started............");
//        Thread01 thread01 = new Thread01();
//        thread01.start();

//        Thread02 thread02 = new Thread02();
//        Thread thread = new Thread(thread02);
//        thread.start();

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                log.info("继承Thread 实现方式.......");
//                int i = 100 / 3;
//                log.info("业务代码执行结果：{}",i);
//            }
//        };
//        Thread thread = new Thread(runnable);
//        thread.start();

//        new Thread(()-> {
//            log.info("继承Thread 实现方式.......");
//            int i = 100 / 3;
//            log.info("业务代码执行结果：{}",i);
//        }).start();

//        Thread03 thread03 = new Thread03();
//        FutureTask<Integer> task = new FutureTask<Integer>(thread03);
//        Thread thread = new Thread(task);
//        thread.start();
//        try {
//            Integer integer = task.get();
//            System.out.println("子线程执行结果："+integer);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("继承Thread实现方式.......");
                // 业务代码执行
                int i = 100/3;
                log.info("业务代码执行结果：{}",i);
                return  i;
            }
        };
        FutureTask<Integer> task = new FutureTask<Integer>(callable);
        Thread thread = new Thread(task);
        thread.start();
        try {
            Integer integer = task.get();
            System.out.println("子线程执行结果："+integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("thread............ended............");
    }
}
