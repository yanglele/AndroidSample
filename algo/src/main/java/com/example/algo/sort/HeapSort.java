package com.example.algo.sort;

import static jdk.nashorn.internal.objects.Global.print;

public class HeapSort {

	private static int[] sort = new int[] { 1, 34, 10, 43, 55, 42, 6, 23, 9, 8, 12, 17, 34, 11, 12, 13 ,5};

	public static void main(String[] args) {
		buildMaxHeapify(sort);// ??????????
		print(sort);
	}


	public static void buildMaxHeapify(int[] array){
		//创建大顶推
		int beginIndex = getParentIndex(array.length-1);
		for(int i = beginIndex;i>= 0;i--){
			build(array,array.length,i);
		}

		//交换顶部数据，排序
		for(int i=array.length - 1;i>=0;i--){
			array[0] ^= array[i];
			array[i] ^= array[0];
			array[0] ^= array[i];
			build(array,i,0);
		}
	}

	public static void build(int[] array,int length,int index){
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);

		int largest=index;
		if(left < length && array[left] > array[index])
			largest = left;
		if(right < length && array[right] > array[largest])
			largest = right;

		if(largest != index){
			array[largest] ^= array[index];
			array[index] ^= array[largest];
			array[largest] ^= array[index];
			build(array,length,largest);
		}
	}

	private static int getParentIndex(int index) {
		return (index - 1) >> 1;
	}

	private static int getLeftChildIndex(int index) {
		return (index << 1) + 1;
	}

	private static int getRightChildIndex(int index) {
		return (index << 1) + 2;
	}
}
