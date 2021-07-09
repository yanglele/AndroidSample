package com.example.java;

class ExceptionThread  implements Runnable
{
    @Override
    public void run()
    {
        Throwable thrown = null;
        System.out.println(3/2);
        System.out.println(3/0);
        System.out.println(3/1);
    }
}
