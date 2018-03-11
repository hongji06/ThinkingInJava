package com.examination;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述  
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组； 
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * 
 * 输入描述: 
 * 连续输入字符串(输入2次,每个字符串长度小于100)
 * 
 * 输出描述:
 * 输出到长度为8的新字符串数组
 * 
 * 输入例子:
 * abc 
 * 123456789
 * 
 * 输出例子:
 * abc00000 
 * 12345678 
 * 90000000
 * 
 * @author 11H0075
 *
 */
public class StringDivide {

	public static void main(String[] args) {
		int n=0;
		String[] readStr = new String[100];
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String nextLine = scanner.nextLine();
			if(nextLine.equals("bye")) {
				break;
			}
			//补齐为8的整倍数
			readStr[n++]= completeStr(nextLine);
		}
		
		for (int i = 0; i < n; i++) {
			printFormmat(readStr[i]);
		}
		scanner.close();
	}

	private static void printFormmat(String string) {
		int len = string.length()/8;
		for (int i = 1; i < len; i++) {
			System.out.println(string.substring((i-1)*8, i*8));
		}
	}

	private static String completeStr(String nextLine) {
		int mod = nextLine.length()%8;
		char[] ch = null ;
		if(mod!=0) {
			ch = new char[8-mod];
			Arrays.fill(ch, '0');
			return nextLine.concat(new String(ch));
		}
		return nextLine;
	}

}
