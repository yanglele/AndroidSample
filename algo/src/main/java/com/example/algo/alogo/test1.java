package com.example.algo.alogo;

public class test1 {

	static int a;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getCatch());

//		TreeNode r1 = new TreeNode(6);
//		TreeNode r2 = new TreeNode(4);
//		TreeNode r3 = new TreeNode(7);
//		TreeNode r4 = new TreeNode(1);
//		TreeNode r5 = new TreeNode(5);
//		TreeNode r6 = new TreeNode(8);
//
//		r1.left = r2;
//		r1.right = r3;
//		r2.left = r4;
//		r2.right = r5;
//		r3.right = r6;
//
//		// testArrVal();
//
//		System.out.println(a);
		// Integer aInteger = new Integer(968);
		// getNum(aInteger);
		// System.out.println("aInteger=" + aInteger);
		// List<String> list1 = new ArrayList<>();
		// list1.add("adc");
		// list1.add("qwe");
		// Iterator<String> iterator = list1.iterator();
		// while (iterator.hasNext()) {
		// System.out.println(iterator.next());
		// }

		// int[] a = { 1, 2 };
		// getNum(a);
		// System.out.println(a[0]);
	}

	private static int getCatch(){
		try{
			int a= 1/0;
		}catch (Exception e){
			System.out.println("catch");
			return 1;
		}finally {
			System.out.println("finally");
			return 2;
		}
	}

	public static void getNum(Integer a) {
		a = new Integer(998);
		System.out.println("a=" + a);
	}

	public static void getNum(int[] a) {
		a[0] = 100;
	}

	public static void testArrVal() {
		int[] a = new int[5];
		int b;
		for (int tmp : a)
			System.out.println(tmp);
		// System.out.println(b);
	}

}
