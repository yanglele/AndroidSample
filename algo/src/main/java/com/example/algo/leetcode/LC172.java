package com.example.algo.leetcode;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

//�ֽ�����, ���ҽ��� �����г��� һ�� (2,5)ʱ, �����������һ�� trailing zero.
//1.  2�ĸ�����Զ����5������.
//2.  ����5�ĸ���ʱ, ��򵥵ķ����� SUM(N/5^1,  N/5^2, N/5^3...)
public class LC172 {

	 public synchronized int trailingZeroes(int n) {
	        int count=0;
	        while(n/5 != 0){
	        	n=n/5;
	        	count=count+n;
	        }
		 return count;
	    }
}
