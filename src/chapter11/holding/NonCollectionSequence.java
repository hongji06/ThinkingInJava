package chapter11.holding;

import java.util.Iterator;

class PetSequence {
	protected String[] pets = "Ralph,Eric,Robin,Lacey,Sam".split(",");
}

public class NonCollectionSequence extends PetSequence {
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < pets.length;
			}

			@Override
			public String next() {
				return pets[index++];
			}

		};
	}
	
	public static void main(String[] args) {
		NonCollectionSequence nc = new NonCollectionSequence();
		InterfaceVsIterator.display(nc.iterator());
	}
}
