package net.mindview.util;

import java.util.LinkedList;
/**
 * make stack structure
 * @author 11H0075
 *
 * @param <T>
 */
public class Stack<T> {
    
   private LinkedList<T> storage=  new LinkedList<T>();
    
    public void push(T t) {
        storage.addFirst(t);
    }
    
    public T pop() {        
        return storage.removeFirst();
    }
    
    public T peek() {
        return storage.getFirst();
    }
    
    public boolean isEmpty() {
        return storage.isEmpty();
    }
    
    public String toString() {
        return storage.toString();
    }
}
