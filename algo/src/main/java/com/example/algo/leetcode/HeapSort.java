package com.example.algo.leetcode;

public class HeapSort {
	private static int[] sort = new int[] { 1, 34, 10, 43, 55, 42, 6, 23, 9, 8, 12, 17, 34, 11, 12, 13 ,5};

	public static void main(String[] args) {
		buildMaxHeapify(sort);// ���ȴ����󶥶�
		heapSort(sort);// ��ʼ������
		print(sort);
	}

	private static void buildMaxHeapify(int[] arry) {
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
	// // �����һ�����ڵ㿪ʼ������ʼ�����󶥶�
	// int startIndex = getParentIndex(data.length - 1);
	// // ��β�˿�ʼ�������ѣ�ÿ�ζ�����ȷ�Ķ�
	// for (int i = startIndex; i >= 0; i--) {
	// maxHeapify(data, data.length, i);
	// }
	// }
	//
	// /**
	// * ��������
	// *
	// * @param data
	// * @param heapSize��Ҫ�������ѵĴ�С��һ����sort��ʱ���õ�����Ϊ���ֵ����ĩβ��ĩβ�Ͳ��ٹ���������
	// * @param index��index�ڵ��´����󶥶�
	// */
	// private static void maxHeapify(int[] data, int heapSize, int index) {
	// // ��ǰ���������ӽڵ�Ƚ�
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
	// // �õ����ֵ�������Ҫ�������������ڵ��������ӽڵ㡣��������ˣ����ӽڵ���ܾͲ��������ˣ���Ҫ���µ���
	// if (largest != index) {
	// int temp = data[index];
	// data[index] = data[largest];
	// data[largest] = temp;
	// maxHeapify(data, heapSize,
	// largest);//������index�Ӷ�˳����ܱ仯���������������Ӷѣ�ֻ��������֧����һ��֧û��
	// }
	// }
	//
	// /**
	// * �������ֵ����ĩβ��data��Ȼ�����ѣ��������ͳ��˵�����
	// *
	// * @paramdata
	// */
	// private static void heapSort(int[] data) {
	// // ĩβ��ͷ�������������������
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
	// * ��2Ϊ�׵Ķ���
	// */
	private static double getLog(double param) {
		return Math.log(param) / Math.log(2);
	}
}