package chapter11.holding;

import java.util.AbstractCollection;
import java.util.Iterator;

public class CollectionSequence<E> extends AbstractCollection<E> {

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public E next() {
				return null;
			}
		};
	}

	@Override
	public int size() {
		return 0;
	}

}
