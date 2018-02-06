/**   
 * @{#} Stack.java by Hongji.Xu 2018年2月6日 上午10:15:59 
 * @Title:  Stack.java   
 * @Package com.think.util   
 * @Description:
 * @author: <a href="https://github.com/hongji06">Hongji.Xu</a>   
 * @version 1.0  
 */   
package com.think.util;

import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();
    
    public void push(T v) {
        storage.addFirst(v);
    }
    
    public T peek() {
        return storage.getFirst();
    }

    public T pop() {
        return storage.removeFirst();
    }
    
    public boolean isEmpty() {
        return storage.isEmpty();
    }
    
    @Override
    public String toString() {
        return storage.toString();
    }
}
