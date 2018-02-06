package chapter09.interfaces;

public class TestSub extends TestBase {

	private int num=100;
	
	void print() {
		System.out.println(num);
	}
	
	public static void main(String[] args) {
		TestSub testSub = new TestSub();
		testSub.print();
	}

}
