package com.example.algo.leetcode;

public class HeapSort {
	private static int[] sort = new int[] { 1, 34, 10, 43, 55, 42, 6, 23, 9, 8, 12, 17, 34, 11, 12, 13 ,5};

	public static void main(String[] args) {
		buildMaxHeapify(sort);// ??????????
		sort(sort);// ?????????
//		buildMaxHeapify1(sort);
//		heapSort(sort);
		print(sort);
	}


	public static void buildMaxHeapify(int[] array){
		int beginIndex = getParentIndex(array.length-1);
		for(int i = beginIndex;i>= 0;i--){
			build(array,array.length,i);
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

	public static void sort(int[] array){
		for(int i=array.length - 1;i>=0;i--){
			array[0] ^= array[i];
			array[i] ^= array[0];
			array[0] ^= array[i];
			build(array,i,0);
		}
	}


	private static void buildMaxHeapify1(int[] arry) {
		int startIndex = getParentIndex(arry.length - 1);
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(arry, arry.length, i);
		}
	}

	private static void maxHeapify(int[] arry, int length, int index) {
		int leftIndex = getLeftChildIndex(index);
		int rightIndex = getRightChildIndex(index);
		int largest = index;
		if (leftIndex < length && arry[index] < arry[leftIndex])
			largest = leftIndex;
		if (rightIndex < length && arry[largest] < arry[rightIndex])
			largest = rightIndex;
		if (largest != index) {
			arry[index] ^= arry[largest];
			arry[largest] ^= arry[index];
			arry[index] ^= arry[largest];
			maxHeapify(arry, length, largest);
		}
	}

	private static void heapSort(int[] arry) {
		int length = arry.length;
		for (int i = 0; i < length; i++) {
			arry[0] ^= arry[length - 1 - i];
			arry[length - 1 - i] ^= arry[0];
			arry[0] ^= arry[length - 1 - i];
			maxHeapify(arry, length - 1 - i, 0);
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

	// private static void buildMaxHeapify(int[] data) {
	// // ????????????????????????????
	// int startIndex = getParentIndex(data.length - 1);
	// // ????????????????????????????
	// for (int i = startIndex; i >= 0; i--) {
	// maxHeapify(data, data.length, i);
	// }
	// }
	//
	// /**
	// * ????????
	// *
	// * @param data
	// * @param heapSize?????????????????????sort?????????????????????????????????????????
	// * @param index??index???????????
	// */
	// private static void maxHeapify(int[] data, int heapSize, int index) {
	// // ?????????????????
	// int left = getChildLeftIndex(index);
	// int right = getChildRightIndex(index);
	//
	// int largest = index;
	// if (left < heapSize && data[index] < data[left]) {
	// largest = left;
	// }
	// if (right < heapSize && data[largest] < data[right]) {
	// largest = right;
	// }
	// // ????????????????????????????????????????????????????????????????????????????????
	// if (largest != index) {
	// int temp = data[index];
	// data[index] = data[largest];
	// data[largest] = temp;
	// maxHeapify(data, heapSize,
	// largest);//??????index?????????????????????????????????????????????????
	// }
	// }
	//
	// /**
	// * ????????????????data??????????????????????????
	// *
	// * @paramdata
	// */
	// private static void heapSort(int[] data) {
	// // ????????????????????????
	// for (int i = data.length - 1; i > 0; i--) {
	// int temp = data[0];
	// data[0] = data[i];
	// data[i] = temp;
	// maxHeapify(data, i, 0);
	// }
	// }
	//
	// private static int getParentIndex(int current) {
	// return (current - 1) >> 1;
	// }
	//
	//
	// private static int getChildLeftIndex(int current) {
	// return (current << 1) + 1;
	// }
	//
	//
	// private static int getChildRightIndex(int current) {
	// return (current << 1) + 2;
	// }
	//
	private static void print(int[] data) {
		int pre = -2;
		for (int i = 0; i < data.length; i++) {
			if (pre < (int) getLog(i + 1)) {
				pre = (int) getLog(i + 1);
				System.out.println();
			}
			System.out.print(data[i] + "|");
		}
	}

	//
	// /**
	// * ??2???????
	// */
	private static double getLog(double param) {
		return Math.log(param) / Math.log(2);
	}
}