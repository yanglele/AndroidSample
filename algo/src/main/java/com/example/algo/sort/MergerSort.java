package com.example.algo.sort;

public class MergerSort {

	//递归
//	public static int[] mergeSort(int[] nums, int left, int right) {
//		if (left == right)
//			return new int[] { nums[left] };
//
//		int mid = left + (right - left) / 2;
//		int[] leftArr = mergeSort(nums, left, mid); //左有序数组
//		int[] rightArr = mergeSort(nums, mid + 1, right); //右有序数组
//		int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组
//
//		int m = 0, i = 0, j = 0;
//		while (i < leftArr.length && j < rightArr.length) {
//			newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
//		}
//		while (i < leftArr.length)
//			newNum[m++] = leftArr[i++];
//		while (j < rightArr.length)
//			newNum[m++] = rightArr[j++];
//		return newNum;
//	}
//	public static void main(String[] args) {
//		int[] nums = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 10 };
//		int[] newNums = mergeSort(nums, 0, nums.length - 1);
//		for (int x : newNums) {
//			System.out.println(x);
//		}
//	}


//	public static void main(String[] args) {
//		int[] arr = new int[] { 43, 6, 1, 3, -9, 77, 56, 3, 57 };
//		int[] tmp = new int[arr.length];
//		MergerSort mergerSort = new MergerSort();
//		mergerSort.mergerSort(arr, tmp, 0, arr.length - 1);
//		for (int tmp1 : arr)
//			System.out.print(tmp1 + " ");
//	}

	public static void main(String[] args) {
		int[] nums = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 10 };
		int[] tmp = new int[nums.length];
		mergeSort(nums, tmp, 0,nums.length - 1);
		for (int x : nums) {
			System.out.println(x);
		}
	}
  //非递归

	public static void mergeSort(int[] arr,int[] tmp,int begin,int end){
		if(begin == end){
			return;
		}

		int mid = (begin + end)/2;
		mergeSort(arr,tmp,begin,mid);
		mergeSort(arr,tmp,mid+1,end);
		mergeArray(arr,tmp,begin,mid,mid+1,end);
	}

	public static void mergeArray(int[] arr,int[] tmp,int lb,int le,int rb,int re){
		int start = lb,k = lb;
		while(lb<=le && rb<=re){
			if(arr[lb] < arr[rb]){
				tmp[k++] = arr[lb++];
			}else{
				tmp[k++] = arr[rb++];
			}
		}
		while(lb<=le){
			tmp[k++] = arr[lb++];
		}
		while (rb<=re){
			tmp[k++] = arr[rb++];
		}
		System.arraycopy(tmp,start,arr,start,k-start);
	}

//	public static void mergeSort(int[] arr, int[] tmp, int start, int end) {
//		if (start < end) {
//			int middle = ((end - start) >> 1) + start;
//			mergeSort(arr, tmp, start, middle);
//			mergeSort(arr, tmp, middle + 1, end);
//			mergerArray(arr, tmp, start, middle, end);
//		}
//	}
//	public static void mergerArray(int[] arr, int[] tmp, int left, int middle, int end){
//		int begin = left;
//		int k = begin;
//
//		while (left <= middle && middle+1 <= end){
//			if(arr[left] < arr[])
//		}
//	}
//	public static void mergerArray(int[] arr, int[] tmp, int start, int middle, int end) {
//		int lStart = start;
//		int rStart = middle + 1;
//
//		int k = start;
//		while (lStart <= middle && rStart <= end) {
//			if (arr[lStart] < arr[rStart]) {
//				tmp[k++] = arr[lStart++];
//			} else {
//				tmp[k++] = arr[rStart++];
//			}
//		}
//		while (lStart <= middle) {
//			tmp[k++] = arr[lStart++];
//		}
//		while (rStart < end) {
//			tmp[k++] = arr[rStart++];
//		}
//
//		System.arraycopy(tmp,start,arr,start,k-start);
//	}


}
