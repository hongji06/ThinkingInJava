package com.datastruct;

public class TreeDemo {
	public static void main(String[] args) {
		int[] arrayNode = new int[]{1,2,3,4,5,6,7,8,9};
        BinaryTree bt = new BinaryTree(arrayNode);
        System.out.println("inOrder:");
        bt.inOrder(bt.root);
        System.out.println("\npreOrder:");
        bt.preOrder(bt.root);
        System.out.println("\npostOrder:");
        bt.postOrder(bt.root);
	}
}
