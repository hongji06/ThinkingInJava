package chapter11.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

class ReversibleArrayList<E> extends ArrayList<E> {
	private static final long serialVersionUID = -932778841246005060L;

	public ReversibleArrayList() {
	}

	public ReversibleArrayList(Collection<E> c) {
		super(c);
	}

	public Iterable<E> reversed() {
		return new Iterable<E>() {

			@Override
			public Iterator<E> iterator() {
				return new Iterator<E>() {
					int current = size() - 1;

					@Override
					public boolean hasNext() {
						return current > -1;
					}

					@Override
					public E next() {
						return get(current--);
					}
				};
			}
		};
	}

}

public class AdapterMethodIdiom {
	public static void main(String[] args) {
		ReversibleArrayList<String> reversibleArrayList = new ReversibleArrayList<String>(
				Arrays.asList("To be or not to be".split(" ")));
		for (String s : reversibleArrayList) {
			System.out.print(s + " ");
		}
		System.out.println();
		for (String s : reversibleArrayList.reversed()) {
			System.out.print(s + " ");
		}
	}

}
