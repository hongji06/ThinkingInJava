package chapter11.holding;

import java.util.PriorityQueue;
import java.util.Random;

public class Excersise28 {
	public static void main(String[] args) {
		Random random = new Random();

		PriorityQueue<Double> doublePQ = new PriorityQueue<>();
		for (int i = 0; i < 10; i++) {
			doublePQ.offer(random.nextDouble());
		}
		
		while(!doublePQ.isEmpty()) {
			System.out.println(doublePQ.poll());
		}
	}
}
