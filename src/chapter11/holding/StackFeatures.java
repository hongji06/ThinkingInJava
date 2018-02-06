package chapter11.holding;

import java.util.Stack;

public class StackFeatures {
    public static void main(String[] args) {
       Stack<String> stack= new Stack<String>();
       stack.push("good");
       stack.push("job");
       System.out.println(stack.search("good"));
       System.out.println(stack);
       stack.pop();
       System.out.println(stack);
       System.out.println(stack.search("good"));
       
    }
}
