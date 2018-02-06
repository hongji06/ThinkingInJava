package com.think.control;

import java.util.Random;

public class Exercise2 {

	public static void main(String[] args) {
		Random random= new Random();
		int first = random.nextInt(2);
		for (int i = 0; i < 25; i++) {
			int current = random.nextInt();
			if(current>first) {
				System.out.println(">:"+current);
			}else if (current==first) {
				System.out.println("=:"+current);
			}else {
				System.out.println("<:"+current);
			}
		}
		
	}

}
