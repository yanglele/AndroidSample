package com.example.algo.sort;

public class MergerSort {

	public static int[] mergeSort(int[] nums, int left, int right) {
		if (left == right)
			return new int[] { nums[left] };

		int mid = left + (right - left) / 2;
		int[] leftArr = mergeSort(nums, left, mid); //左有序数组
		int[] rightArr = mergeSort(nums, mid + 1, right); //右有序数组
		int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

		int m = 0, i = 0, j = 0;
		while (i < leftArr.length && j < rightArr.length) {
			newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
		}
		while (i < leftArr.length)
			newNum[m++] = leftArr[i++];
		while (j < rightArr.length)
			newNum[m++] = rightArr[j++];
		return newNum;
	}
	public static void main(String[] args) {
		int[] nums = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 10 };
		int[] newNums = mergeSort(nums, 0, nums.length - 1);
		for (int x : newNums) {
			System.out.println(x);
		}
	}

//	public static void main(String[] args) {
//		int[] arr = new int[] { 43, 6, 1, 3, -9, 77, 56, 3, 57 };
//		int[] tmp = new int[arr.length];
//		MergerSort mergerSort = new MergerSort();
//		mergerSort.mergerSort(arr, tmp, 0, arr.length - 1);
//		for (int tmp1 : arr)
//			System.out.print(tmp1 + " ");
//	}
//
//	public void mergerArray(int[] arr, int[] tmp, int start, int middle, int end) {
//		int i1 = start;
//		int i2 = middle;
//
//		int j1 = middle + 1;
//
//		int k = start;
//		while (i1 <= i2 && j1 <= end) {
//			if (arr[i1] < arr[j1]) {
//				tmp[k] = arr[i1];
//				i1++;
//			} else {
//				tmp[k] = arr[i2];
//				i2++;
//			}
//			k++;
//		}
//		while (i1 <= i2) {
//			tmp[k] = arr[i1];
//			k++;
//			i1++;
//		}
//		while (j1 < end) {
//			tmp[k] = arr[j1];
//			k++;
//			j1++;
//		}
//
//		for (int i = start; i < k; i++)
//			arr[i] = tmp[i];
//	}
//
//	public void mergerSort(int[] arr, int[] tmp, int start, int end) {
//		if (start < end) {
//			int middle = ((end - start) >> 1) + start;
//			mergerSort(arr, tmp, start, middle);
//			mergerSort(arr, tmp, middle + 1, end);
//			mergerArray(arr, tmp, start, middle, end);
//		}
//	}
}
