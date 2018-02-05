package chapter11.holding;

import java.util.ArrayList;

class Apple {
	private static long counter;
	private final long id = counter++;

	public long id() {
		return id;
	}

	@Override
	public String toString() {
		System.out.println("Apple class");
		return super.toString();
	}
}

class Orange {
	@Override
	public String toString() {
		System.out.println("Orange class");
		return super.toString();
	}
}

public class ApplesAndOrangesWithoutGenerics {

	public static void main(String[] args) {

		ArrayList<Apple> apples = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}

		for (int i = 0; i < apples.size(); i++) {
			System.out.println(apples.get(i).id());
		}
		
		for (Apple apple : apples) {
			System.out.println(apple.id());
		}
	}
}
