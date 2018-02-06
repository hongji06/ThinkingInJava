package chapter11.holding;

import com.think.util.Stack;

public class Exercise15 {

    public static void main(String[] args) {
        String string = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+-+r+u--+l+e+s---";
        char[] ch = string.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '+') {
                stack.push(ch[++i]);
            }
            if (ch[i] == '-') {
                System.out.print(stack.pop());
            }
        }
    }
}
