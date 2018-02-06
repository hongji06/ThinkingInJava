package chapter11.holding;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Exercise12 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> revList = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }

        ListIterator<Integer> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            revList.add(listIterator.previous());
        }

        System.out.println("list:" + list.toString());
        System.out.println("revList:" + revList.toString());

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }

    }
}
