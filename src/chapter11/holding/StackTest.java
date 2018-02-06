package chapter11.holding;

import com.think.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        for (String s : "My dog has fleas".split(" ")) {
            stack.push(s);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
