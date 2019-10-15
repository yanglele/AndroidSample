package com.example.algo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class ThreadPoolClass {

    public static void main(String[] args) throws InterruptedException {
        newCachedThreadPoolTest();
        int a=0;
    }


    public static void newCachedThreadPoolTest() throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i=0;i<20;i++){
            Thread.sleep(1000);

            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }


    public void synchronousQueueTest(){
        final SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();

        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<3;i++){
                    System.out.println("put thread start"+i);
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<3;i++){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println("take from putThread: " + queue.take());
                    } catch (InterruptedException e) {
                    }
                }
            }
        });

        putThread.start();
        takeThread.start();
    }
}
