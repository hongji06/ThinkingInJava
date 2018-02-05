package itcast.thread;

import java.util.Timer;
import java.util.TimerTask;
class MyTimerTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("bombing.");
		new Timer().schedule(new MyTimerTask(), 2000);
	}
	
}

public class TraditionalTimerTest {
	public static void main(String[] args) {
		new Timer().schedule(new MyTimerTask(), 3000);
	}
}
