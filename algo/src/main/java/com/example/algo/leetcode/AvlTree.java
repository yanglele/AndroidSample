package com.example.algo.leetcode;

public class AvlTree<T extends Comparable<? super T>> {
	private static class AvlNode<T> {// avl���ڵ�

		AvlNode(T theElement) {
			this(theElement, null, null);
		}

		AvlNode(T theElement, AvlNode<T> lt, AvlNode<T> rt) {
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}

		T element; // �ڵ��е�����
		AvlNode<T> left; // �����
		AvlNode<T> right; // �Ҷ���
		int height; // �ڵ�ĸ߶�
	}

	private AvlNode<T> root;// avl����

	public AvlTree() {
		root = null;
	}

	// ��avl���в������ݣ��ظ����ݸ���
	public void insert(T x) {
		root = insert(x, root);
	}

	// ��avl��ɾ������,���ﲢδʵ��
	public void remove(T x) {
		System.out.println("Sorry, remove unimplemented");
	}

	// ��avl��������С������
	public T findMin() {
		if (isEmpty())
			System.out.println("����");
		;
		return findMin(root).element;
	}

	// ��avl��������������
	public T findMax() {
		if (isEmpty())
			System.out.println("����");
		return findMax(root).element;
	}

	// ����
	public boolean contains(T x) {
		return contains(x, root);
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	// �������avl��
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private AvlNode<T> insert(T x, AvlNode<T> t) {
		if (t == null)
			return new AvlNode<T>(x, null, null);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			t.left = insert(x, t.left);// ��x������������
			if (height(t.left) - height(t.right) == 2)// ����ƽ��
				if (x.compareTo(t.left.element) < 0)// LL�ͣ������ͣ�
					t = rotateWithLeftChild(t);
				else // LR�ͣ������ͣ�
					t = doubleWithLeftChild(t);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);// ��x������������
			if (height(t.right) - height(t.left) == 2)// ����ƽ��
				if (x.compareTo(t.right.element) > 0)// RR�ͣ������ͣ�
					t = rotateWithRightChild(t);
				else // RL��
					t = doubleWithRightChild(t);
		} else
			; // �ظ����ݣ�ʲôҲ����
		t.height = Math.max(height(t.left), height(t.right)) + 1;// ���¸߶�
		return t;
	}

	// ����С
	private AvlNode<T> findMin(AvlNode<T> t) {
		if (t == null)
			return t;
		while (t.left != null)
			t = t.left;
		return t;
	}

	// �����
	private AvlNode<T> findMax(AvlNode<T> t) {
		if (t == null)
			return t;
		while (t.right != null)
			t = t.right;
		return t;
	}

	// ���������ң�
	private boolean contains(T x, AvlNode t) {
		while (t != null) {
			int compareResult = x.compareTo((T) t.element);

			if (compareResult < 0)
				t = t.left;
			else if (compareResult > 0)
				t = t.right;
			else
				return true; // Match
		}
		return false; // No match
	}

	// �������avl��
	private void printTree(AvlNode<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	// ��߶�
	private int height(AvlNode<T> t) {
		return t == null ? -1 : t.height;
	}

	// ����������ת,������LL��
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	// ����������ת��������RR��
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
		AvlNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	// ˫��ת��������LR��
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	// ˫��ת,������RL��
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	// Test program
	public static void main(String[] args) {
		AvlTree<Integer> t = new AvlTree<Integer>();
		final int NUMS = 200;
		final int GAP = 17;
		System.out.println("Checking... (no more output means success)");
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			t.insert(i);
		t.printTree();
		System.out.println(t.height(t.root));

	}
}
