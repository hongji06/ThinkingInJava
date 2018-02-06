package chapter11.holding;

import java.util.LinkedList;

public class LinkedListFeatures {

    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkedList<String> pets = new LinkedList<>();
        pets.add("Rat");
        pets.add("Manx");
        pets.add("Cymric");
        pets.add("Mutt");
        pets.add("Pug");
        
        System.out.println(pets);
        System.out.println("pets.getFirst():"+pets.getFirst());
        System.out.println("pets.element():"+pets.element());
        System.out.println("pets.peek():"+pets.peek());
        System.out.println("pets.peekFirst():"+pets.peekFirst());
        System.out.println("pets.poll():"+pets.poll());
        System.out.println(pets);
        pets.clear();
        //System.out.println("pets.getFirst():"+pets.getFirst());
        //System.out.println("pets.element():"+pets.element());
        System.out.println("pets.peek():"+pets.peek());
        System.out.println("pets.poll():"+pets.poll());
        System.out.println("pets.remove():"+pets.remove());
    }

}
