package com.example.algo.com.sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 43, 6, 1, 3, -9, 77, 56, 3, 43 };
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

	public void quickSort1(int[] arr,int start,int end){
		int tmp = arr[start];
		int l = start,r=end;
		while (l < r){
			while (r > l && arr[r] >= tmp){
				r--;
			}
			arr[l] = arr[r];
			while (l < r && arr[l] <= tmp){
				l++;
			}
			arr[r] = arr[l];
		}
		arr[l] = tmp;
		if(start < l-1){
			quickSort1(arr,start,l-1);
		}
		if(l+1<end){
			quickSort1(arr,l+1,end);
		}
	}
}
