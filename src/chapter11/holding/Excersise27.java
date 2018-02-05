package chapter11.holding;

import java.util.LinkedList;
import java.util.Queue;

class Command {
	private String name;
	
	public Command(String name) {
		this.name=name;
	}

	public void operater() {
		System.out.println(name);
	}
}

class Producer{
	public static void produce(Queue<Command> queue) {
		queue.offer(new Command("hello"));
		queue.offer(new Command("world"));
		queue.offer(new Command("good"));
		queue.offer(new Command("luck"));
	}
}

class Consumer{
	public static void consumer(Queue<Command> queue) {
		while(queue.peek()!=null) {
			queue.remove().operater();
		}
	}
}

public class Excersise27 {
	public static void main(String[] args) {
		Queue<Command> queue = new LinkedList<>();
		Producer.produce(queue);
		Consumer.consumer(queue);
	}
}
