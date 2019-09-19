package com.example.algo.sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 43, 6, 1, 3, -9, 77, 56, 3, 57 ,43};
		QuickSort quickSort = new QuickSort();
		quickSort.quickSort1(arr, 0, arr.length - 1);
		for (int tmp : arr)
			System.out.print(tmp + " ");
	}

	public void quickSort(int[] arr, int start, int end) {
		int tmp = arr[start];
		int i = start;
		int j = end;
		while (i < j) {
			while (arr[j] >= tmp && i < j)
				j--;
			arr[i] = arr[j];
			while (arr[i] <= tmp && i < j)
				i++;
			arr[j] = arr[i];
		}
		arr[i] = tmp;
		if (start < i - 1)
			quickSort(arr, start, i - 1);
		if (i + 1 < end)
			quickSort(arr, i + 1, end);
	}

	public void quickSort1(int[] nums,int start,int end){
		int tmp = nums[start];
		int i=start,j=end;
		while (i<j){
			while (nums[j] >= tmp && i<j)
				j--;
			nums[i] = nums[j];
			while (nums[i] <= tmp && i<j)
				i++;
			nums[j]=nums[i];
		}
		nums[i] = tmp;

		if(start < i-1){
			quickSort1(nums,start,i-1);
		}
		if(i+1 < end){
			quickSort1(nums,i+1,end);
		}
	}
}
