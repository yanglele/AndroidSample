package com.example.testlib;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyClass {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.setA(2);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());

        executor.execute(getRunable("1"));
        executor.execute(getRunable("2"));
        executor.execute(getRunable("3"));
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
        Thread.sleep(1000);
        System.out.println(executor.getActiveCount() + " "+executor.getCorePoolSize());
    }

    public static Runnable getRunable(final String name){

        return new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(name);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}

final class Test{
    int a=1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
