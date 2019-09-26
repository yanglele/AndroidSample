package com.example.testlib.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/20
 * version:
 * update:
 */

public class SynchronizedTest{

    public static void main(String[] args){
        SynchronizedNumber n = new SynchronizedNumber(0);
        Thread old=new Thread(new OldRunner(n),"old-thread");
        Thread even = new Thread(new EvenRunner(n),"even-thread");
        old.start();
        even.start();

    }
}

//打印奇数的线程
class OldRunner implements Runnable{
    private SynchronizedNumber n;

    public OldRunner(SynchronizedNumber n) {
        this.n = n;
    }

    public void run() {
        while (true){
            n.printOld();  //等待数据变成奇数
            System.out.println("old:" + n.getVal());
            n.increase();
            if (n.getVal()>12){
                break;
            }
        }
    }
}
//打印偶数的线程
class EvenRunner implements Runnable{
    private SynchronizedNumber n;

    public EvenRunner(SynchronizedNumber n) {
        this.n = n;
    }

    public void run() {
        while (true){
            n.printEven();            //等待数据变成偶数
            System.out.println("even:"+n.getVal());
            n.increase();
            if (n.getVal()>13){
                break;
            }
        }
    }
}

class SynchronizedNumber {
    private int val;

    public SynchronizedNumber(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
    public synchronized void increase(){
        val++;
        notify(); //数据变了，唤醒另外的线程
    }
    public synchronized void printOld(){
        while ((val % 2)==0){
            try {
                wait(); //只要是偶数，一直等待
                System.out.println("old waite come");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void printEven(){
        while ((val % 2)!=0){
            try {
                wait(); //只要是奇数，一直等待
                System.out.println("event waite come");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class LockNumber {
    private int val;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public LockNumber(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void increase(){
        lock.lock();
        try {
            val++;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void printOld(){
        lock.lock();
        try{
            while ((val % 2)==0){
                try {
                    condition.await();
                    System.out.println("old waite come");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }

    public void printEven(){
        lock.lock();
        try {
            while ((val % 2)!=0){
                try {
                    condition.await();
                    System.out.println("event waite come");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }

    }
}

