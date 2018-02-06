package chapter09.interfaces;

abstract class AbsTest {
	abstract void fun();
}

public class Exercise4 extends AbsTest {
	void fun() {
		System.out.println("print fun.");
	}

	static void staticMethod(AbsTest absTest) {
		Exercise4 exercise4 = (Exercise4) absTest;
		exercise4.toString();
	}

	public static void main(String[] args) {
		Exercise4.staticMethod(new Exercise4());
	}
}
