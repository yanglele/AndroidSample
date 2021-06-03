package com.example.algo.sort;

import static jdk.nashorn.internal.objects.Global.print;

public class HeapSort {

    private static int[] sort = new int[]{1, 34, 10, 43, 55, 42, 6, 23, 9, 8, 12, 17, 34, 11, 12, 13, 5};

    public static void main(String[] args) {
        buildMaxHeapify(sort);
        for(int tmp:sort){
            System.out.print(tmp + "  ");
        }
    }

    public static void buildMaxHeapify(int[] args){

        //构建大顶堆
        for(int i = getParentIndex(args.length - 1);i>=0;i--){
            build(args,i,args.length);
        }

        // 将堆顶元素放在数组后面
        for(int i=args.length-1;i>0;i--){
            swap(args,i,0);
            build(args,0,i);
        }

    }

    public static void build(int[] arr,int index,int length){
        int left = getLeftChild(index);
        int right = getRightChild(index);
        int maxIndex = index;
        if(left < length && arr[maxIndex] < arr[left]){
            maxIndex = left;
        }
        if(right < length && arr[maxIndex] < arr[right]){
            maxIndex = right;
        }
        if(maxIndex != index){
            swap(arr,maxIndex,index);
            build(arr,maxIndex,length);
        }
    }

    public static int getParentIndex(int index){
        return (index-1)>>1;
    }
    public static int getLeftChild(int index){
        return (index<<1) + 1;
    }
    public static int getRightChild(int index){
        return (index<<1)+2;
    }
    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

//
//    public static void buildMaxHeapify(int[] array) {
//        //创建大顶推
//        int beginIndex = getParentIndex(array.length - 1);
//        for (int i = beginIndex; i >= 0; i--) {
//            build(array, array.length, i);
//        }
//
//        //交换顶部数据，排序
//        for (int i = array.length - 1; i >= 0; i--) {
//            array[0] ^= array[i];
//            array[i] ^= array[0];
//            array[0] ^= array[i];
//            build(array, i, 0);
//        }
//    }
//
//    public static void build(int[] array, int length, int index) {
//        int left = getLeftChildIndex(index);
//        int right = getRightChildIndex(index);
//
//        int largest = index;
//        if (left < length && array[left] > array[index]) largest = left;
//        if (right < length && array[right] > array[largest]) largest = right;
//
//        if (largest != index) {
//            array[largest] ^= array[index];
//            array[index] ^= array[largest];
//            array[largest] ^= array[index];
//            build(array, length, largest);
//        }
//    }
//
//    private static int getParentIndex(int index) {
//        return (index - 1) >> 1;
//    }
//
//    private static int getLeftChildIndex(int index) {
//        return (index << 1) + 1;
//    }
//
//    private static int getRightChildIndex(int index) {
//        return (index << 1) + 2;
//    }
}
