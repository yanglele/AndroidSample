package com.example.algo.leetcode;

public class MaxSumDp3 {

	/*
	 * �ⷨһ��ʱ�临�Ӷ�ΪO(n^3) ��һ����������������ͣ�������ֱ����Ұ���İ취���ǣ�����forѭ��������������������ÿһ��������ĺͣ�
	 * ���������Щ�����������һ��ֵ�� ��currSum[i, ��, j]Ϊ����A�е�i��Ԫ�ص���j��Ԫ�صĺͣ�����0 <= i <= j < n����
	 * maxSumΪ�����󵽵��������������ĺ͡�
	 * �ҵ�ȫ�Ǹ��������ʱ�����ǿ����ó��򷵻�0��Ҳ�����ó��򷵻������Ǹ���������������ó��򷵻������Ǹ�������
	 */
	int MaxSubArray(int[] A, int n) {
		int maxSum = A[0]; // ȫ����������������
		int currSum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				for (int k = i; k <= j; k++) {
					currSum += A[k];
				}
				if (currSum > maxSum)
					maxSum = currSum;

				currSum = 0; // ����Ҫ�ǵ����㣬����Ļ�sum���մ�ŵ�������������ĺ͡�
			}
		}
		return maxSum;
	}

	/**
	 * ��ʵ�ϣ���������currSumΪ��ǰ���������ĺͣ�maxSumΪ���Ҫ���ص����������ĺͣ�����������ɨ��ʱ��
	 * �Ե�j+1��Ԫ��������ѡ��Ҫô����ǰ���ҵ��������飬Ҫô��Ϊ��������ĵ�һ��Ԫ�أ�
	 * ���currSum���ϵ�ǰԪ��a[j]��С��a[j]������currSum����a[j]������currSum���¸�ֵ����Ϊ��һ��Ԫ�أ�
	 * ��currSum = a[j]�� ͬʱ����currSum > maxSum�������maxSum = currSum�����򱣳�ԭֵ�������¡�
	 */
	int MaxSubArray2(int[] a, int n) {
		int currSum = 0;
		int maxSum = a[0]; // ȫ����������������

		for (int j = 0; j < n; j++) {
			currSum = (a[j] > currSum + a[j]) ? a[j] : currSum + a[j];
			maxSum = (maxSum > currSum) ? maxSum : currSum;

		}
		return maxSum;
	}
}
