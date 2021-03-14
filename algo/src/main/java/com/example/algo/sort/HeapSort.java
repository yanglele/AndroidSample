package com.example.algo.sort;

import static jdk.nashorn.internal.objects.Global.print;

public class HeapSort {

    private static int[] sort = new int[]{1, 34, 10, 43, 55, 42, 6, 23, 9, 8, 12, 17, 34, 11, 12, 13, 5};

    public static void main(String[] args) {

        int k=3;//求第3大的数
        for (int i = getParentIndex1(sort.length - 1); i >= 0; i--) {
            heapsort1(sort, sort.length, i);
        }

        for (int i = sort.length - 1; i >= sort.length-k; i--) {
            sort[0] ^= sort[i];
            sort[i] ^= sort[0];
            sort[0] ^= sort[i];
            heapsort1(sort,i,0);
        }

        System.out.println(sort[sort.length-k]);
    }

    public static void heapsort1(int[] arr, int length, int index) {

        int left = getLeftChild1(index);
        int right = getRightChild1(index);

        int maxIndex = index;
        if (left < length && arr[left] > arr[maxIndex]) {
            maxIndex = left;
        }
        if (right < length && arr[right] > arr[maxIndex]) {
            maxIndex = right;
        }

        if (maxIndex != index) {
            arr[index] ^= arr[maxIndex];
            arr[maxIndex] ^= arr[index];
            arr[index] ^= arr[maxIndex];
            heapsort1(arr, length, maxIndex);
        }
    }

    public static int getParentIndex1(int index) {
        return (index - 1) >> 1;
    }

    public static int getLeftChild1(int index) {
        return (index << 1) + 1;
    }

    public static int getRightChild1(int index) {
        return (index << 1) + 2;
    }


    public static void buildMaxHeapify(int[] array) {
        //创建大顶推
        int beginIndex = getParentIndex(array.length - 1);
        for (int i = beginIndex; i >= 0; i--) {
            build(array, array.length, i);
        }

        //交换顶部数据，排序
        for (int i = array.length - 1; i >= 0; i--) {
            array[0] ^= array[i];
            array[i] ^= array[0];
            array[0] ^= array[i];
            build(array, i, 0);
        }
    }

    public static void build(int[] array, int length, int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);

        int largest = index;
        if (left < length && array[left] > array[index]) largest = left;
        if (right < length && array[right] > array[largest]) largest = right;

        if (largest != index) {
            array[largest] ^= array[index];
            array[index] ^= array[largest];
            array[largest] ^= array[index];
            build(array, length, largest);
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
