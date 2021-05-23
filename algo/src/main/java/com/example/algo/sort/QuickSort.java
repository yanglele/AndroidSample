package com.example.algo.sort;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[] { 43, 6, 1, 3, -9, 77, 56, 3, 57 ,43};
//		QuickSort quickSort = new QuickSort();
//		quickSort.quickSort(arr, 0, arr.length - 1);
//		quickSortK(arr,0,arr.length-1,3);
		sort1(arr,0,arr.length-1);
		for (int tmp : arr)
			System.out.print(tmp + " ");
	}

	public static void sort1(int[] nums,int l,int r){
		if(l >= r){
			return;
		}
		int tl=l,tr=r;
		int door = nums[l];
		while (tl < tr){
			while (nums[tr] > door &&  tl < tr){
				tr--;
			}
			if(tl < tr){
				nums[tl++] = nums[tr];
			}
			while (nums[tl] < door && tl < tr){
				tl++;
			}
			if(tl < tr){
				nums[tr--]=nums[tl];
			}
		}
		nums[tl] = door;
		sort1(nums,l,tl-1);
		sort1(nums,tl+1,r);
	}


	public static void quickSort(int[] arr, int start, int end) {
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

}
