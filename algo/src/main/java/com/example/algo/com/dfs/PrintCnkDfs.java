package com.example.algo.com.dfs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PrintCnkDfs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 5;
		int k = 3;
		int[] a = new int[k];
		boolean[] book = new boolean[n];
		dfs(n, k, a, 0, 0);
	}

	public static void dfs(int n, int k, int[] a, int cur, int step) {
		if (step == k) {
			for (int tmp : a)
				System.out.print(tmp + " ");
			System.out.println();
			return;
		}
		for (int i = cur; i < n; i++) {
			a[step] = i + 1;
			dfs(n, k, a, i + 1, step + 1);
			a[step] = -1;
		}
	}

}
