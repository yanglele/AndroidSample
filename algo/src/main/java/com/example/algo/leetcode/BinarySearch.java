package com.example.algo.leetcode;

public class BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] test={1,2,3,4,5,6,7};
		System.out.println(search(test, 0,test.length,6));
		System.out.println(binarySearch1(test, 6,0,test.length));
		System.out.println(binarySearch2(test,10));
		System.out.println(binarySearch2(test,7));
	}


	public static int search(int[] arr,int left,int right,int tar){
		if(left == right)
			return -1;

		int middle=(left+right) >> 1;
		if(arr[middle] == tar){
			return middle;
		}else if(arr[middle] > tar){
			return search(arr,left,middle-1,tar);
		}else {
			return search(arr,middle+1,right,tar);
		}
	}

	public static int binarySearch1(int[] nums,int tar,int l,int r){
		if(l > r){
			return -1;
		}
		int mid = (l+r)>>1;
		if(nums[mid] == tar){
			return mid;
		}else if(nums[mid] > tar){
			return binarySearch1(nums,tar,l,r-1);
		}else{
			return binarySearch1(nums,tar,mid+1,r);
		}
	}


	public static int binarySearch2(int[] nums,int tar){
		int l = 0,r = nums.length-1;
		while (l<=r){
			int mid = (l+r)>>1;
			if(nums[mid] == tar){
				return mid;
			}else if(nums[mid] > tar){
				r = mid - 1;
			}else {
				l = mid + 1;
			}
		}
		return -1;
	}
}
