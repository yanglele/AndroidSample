package com.example.algo.leetcode;

public class TwoDimensional {

	/**
	 * �����ⷨ�����Ż�����ά����Ӷκ͡�
	 * 
	 * @author DaiSong
	 * @Date 2013��12��2��
	 */
	static int MAX = 501;
	static int[][] ps = new int[MAX][MAX];

	/**
	 * ����һ��������⣬ʱ�临�Ӷ�ΪO(N^3*M^3)
	 * 
	 * @param a
	 * @param m
	 * @param n
	 * @return
	 */
	public static int MaxSum1(int a[][], int m, int n) {

		int max = Integer.MIN_VALUE;
		int sum;
		for (int min_i = 0; min_i < n; min_i++) {
			for (int max_i = min_i; max_i < n; max_i++) {
				for (int min_j = 0; min_j < m; min_j++) {
					for (int max_j = min_j; max_j < m; max_j++) {
						// ���������ĺ�
						sum = 0;
						for (int i = min_i; i <= max_i; i++) {
							for (int j = min_j; j <= max_j; j++) {
								sum += a[i][j];
							}
						}
						if (sum > max) {
							max = sum;
						}
					}
				}
			}
		}
		return max;
	}

	/**
	 * ���������Ľ������ǵ�����ĺ���ҪƵ�����㣬����Ԥ������ps[n][m]���i=1..n,j=1..m����͡� ʱ�临�Ӷ�ΪO(N^2*M^2)
	 * 
	 * @param a
	 * @param n
	 * @param m
	 * @return
	 */
	public static int MaxSum2(int a[][], int n, int m) {

		int max = Integer.MIN_VALUE;
		int sum;
		PieceSum(a, n, m);
		for (int min_i = 1; min_i <= n; min_i++) {
			for (int max_i = min_i; max_i <= n; max_i++) {
				for (int min_j = 1; min_j <= m; min_j++) {
					for (int max_j = min_j; max_j <= m; max_j++) {
						// ���������ĺ�
						sum = ps[max_i][max_j] - ps[min_i - 1][max_j] - ps[max_i][min_j - 1] + ps[min_i - 1][min_j - 1];
						if (sum > max) {
							max = sum;
						}
					}
				}
			}
		}
		return max;
	}

	/**
	 * Ԥ�����������ͣ�ʱ�临�Ӷ�ΪO(N*M)
	 * 
	 * @param a
	 * @param n
	 * @param m
	 */
	public static void PieceSum(int[][] a, int n, int m) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + a[i][j];
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[][] = { { -1, -4, 3 }, { 3, 4, -1 }, { -5, -2, 8 } };
		System.out.println(MaxSum1(a, 3, 3));
		int b[][] = { { 0, 0, 0, 0 }, { 0, -1, -4, 3 }, { 0, 3, 4, -1 }, { 0, -5, -2, 8 } };
		System.out.println(MaxSum2(b, 3, 3));
	}
}
