package com.example.java;

class TestExceptionHandler {
    public static void main(String[] args){
        Thread thread = new Thread(new ExceptionThread());
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }

    static class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            System.out.println("-----"+throwable.getMessage());
        }
    }
}
