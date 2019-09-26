package com.example.testlib.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2019/9/20
 * version:
 * update:
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        LockModule buff = new LockModule();

        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);

        writer.start();
        Thread.sleep(1000);
        reader.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (;;) {
                    if (System.currentTimeMillis()
                            - start > 10000) {
                        System.out.println("������,�����ж�");
                        reader.interrupt();
                        break;
                    }

                }

            }
        }).start();

    }

    public static class LockModule {

        private ReentrantLock lock = new ReentrantLock();

        public void write() {
            lock.lock();
            try {
                long startTime = System.currentTimeMillis();
                System.out.println("��ʼ�����buffд�����ݡ�");
                for (;;)// ģ��Ҫ����ܳ�ʱ��
                {
                    if (System.currentTimeMillis()
                            - startTime > 30000)
                        break;
                }
                System.out.println("����д����");
            } finally {
                lock.unlock();
            }
        }
        public void read() throws InterruptedException {
            lock.lockInterruptibly();//ע�����������Ӧ�жϣ��׳��ж��쳣��
            try {
                System.out.println("�����buff������");
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Writer extends Thread {

        private LockModule buff;

        public Writer(LockModule buff) {
            this.buff = buff;
        }

        @Override
        public void run() {
            buff.write();
            System.out.println("д����");
        }

    }
    public static class Reader extends Thread {

        private LockModule buff;

        public Reader(LockModule buff) {
            this.buff = buff;
        }

        @Override
        public void run() {

            try {
                buff.read();//�����յ��жϵ��쳣���Ӷ���Ч�˳�
            } catch (InterruptedException e) {
                System.out.println("�Ҳ�����");
            }

            System.out.println("��������ȥ����������");

        }

    }
}
