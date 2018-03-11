package com.algorithm;

public class SortDemo {

	public static void main(String[] args) {
		int[] a = { 100, 300, 27, 19,88,88,88, 222 };
		myQuickSort(a, 0, a.length-1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "  ");
		}
	}

	/**
	 * 將一個數字插入到已經排序好的數組中
	 * 
	 * @param a
	 */
	public static void insertIntoSort(int[] a, int cur) {
		int[] result = new int[a.length + 1];
		// 排序方式：true为递增排序，false递减排序
		boolean sortFlag = true;
		if (a.length > 2) {
			if (a[0] > a[a.length - 1]) {
				sortFlag = false;
			}
		}
		for (int i = 0; i < a.length; i++) {
			if (sortFlag) {
				if (cur < a[i]) {
					result[i] = cur;
					// 已經插入排序內容，後續直接移動即可
					for (int j = i; j < a.length; j++) {
						result[j + 1] = a[j];
					}
					break;
				} else if (cur >= a[i] && i != (a.length - 1)) {
					result[i] = a[i];
				} else {
					result[i + 1] = cur;
				}

			} else {
				if (cur > a[i]) {
					result[i] = cur;
					// 已經插入排序內容，後續直接移動即可
					for (int j = i; j < a.length; j++) {
						result[j + 1] = a[j];
					}
					break;
				} else if (cur <= a[i] && i != (a.length - 1)) {
					result[i] = a[i];
				} else {
					result[i + 1] = cur;
				}
			}
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + "  ");
		}
	}

	/**
	 * 冒泡排序
	 * 
	 * @param a
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - i - 1; j++) {
				if (array[j] > array[j + 1]) {
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 快速排序基本思想： 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
	 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
	 */
	public static void quickSort(int[] a, int start, int end) {
		// { 100, 300, 27, 19, 222 } (0,4) key = 100
		if (start >= end) {
			return;
		}
		int key = a[start];// 选基准值
		int temp;// 记录中间值
		int i = start;
		int j = end;
		do {
			if ((a[i] < key) && i < end) {
				i++;
			}
			if ((a[j] > key) && j > start) {
				j--;
			}
			if (i <= j) {
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);

		if (start < j) {
			quickSort(a, start, j);
		}
		if (end > i) {
			quickSort(a, i, end);
		}
	}

	public static void myQuickSort(int[] a, int low, int high) {
		if (high <= low) {
			return;
		}
		int key = a[low];
		int temp = 0;
		int i = low;
		int j = high;

		while (i < j) {
			if ((a[i] < key) && (i < high)) {
				i++;
			}
			if ((a[j] > key) && (low < j)) {
				j--;
			}
			if (i <= j) {
				temp = a[j];
				a[j] = a[i];
				a[i] = temp;
				i++;
				j--;
			}
		} 
		if (i < high) {
			myQuickSort(a, i, high);
		}
		if (j > low) {
			myQuickSort(a, low, j);
		}
	}
}
