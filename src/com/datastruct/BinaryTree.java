package com.datastruct;

public class BinaryTree {
	BinaryTreeNode[] btn;
	BinaryTreeNode root;
	int nodeSize;

	public BinaryTree(int[] arrayNode) {
		nodeSize = arrayNode.length;
		btn = new BinaryTreeNode[nodeSize];

		// 把arrayNode元素转化为节点
		for (int i = 0; i < nodeSize; i++) {
			btn[i] = new BinaryTreeNode();
			btn[i].setData(arrayNode[i]);
			if (i == 0) {
				root = btn[i];
			}
		}
		// 把二叉树的左右子树节点补全
		for (int j = 0; j <= (nodeSize - 2) / 2; j++) {
			btn[j].setLeftNode(btn[2 * j + 1]);
			btn[j].setRightNode(btn[2 * j + 2]);
		}
	}

	// 递归方法前序遍历
	public void preOrder(BinaryTreeNode btn) {
		BinaryTreeNode rootNode = btn;
		if (rootNode != null) {
			printNode(rootNode);
			preOrder(rootNode.leftNode);
			preOrder(rootNode.rightNode);
		}
	}

	// 递归方法中序遍历
	public void inOrder(BinaryTreeNode btn) {
		BinaryTreeNode rootNode = btn;
		if (rootNode != null) {
			inOrder(rootNode.leftNode);
			printNode(rootNode);
			inOrder(rootNode.rightNode);
		}
	}

	// 递归方法后序遍历
	public void postOrder(BinaryTreeNode btn) {
		BinaryTreeNode rootNode = btn;
		if (rootNode != null) {
			postOrder(rootNode.leftNode);
			postOrder(rootNode.rightNode);
			printNode(rootNode);
		}
	}

	// 打印节点
	private void printNode(BinaryTreeNode btn) {
		int a = btn.data;
		System.out.print(a+"  ");
	}
}
