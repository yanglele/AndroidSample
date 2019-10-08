package com.example.algo.new_aha;

//二分查找法
public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] test={1,2,3,4,5,6,7};
		System.out.println(binarySearch(test, 0,test.length,6));
	}

	public static int binarySearch(int[] args,int begin,int end,int des){
		if(begin == end){
			return begin;
		}
		int middle =(end + begin)>>1;
		if(des == args[middle]){
			return middle;
		}else if(des > args[middle]){
			return binarySearch(args,middle+1,end,des);
		}else {
			return binarySearch(args,0,middle-1,des);
		}
	}



	public static int binarySearch(int[] array,int des){
		int low=0;
		int high=array.length-1;
		while(low<=high && high<=array.length-1){
			int middle=(low+high)>>1;
			if(des == array[middle])
				return middle;
			else if(des>array[middle])
				low=middle+1;
			else if(des<array[middle])
				high=middle-1;
		}
		return -1;
	}
}
