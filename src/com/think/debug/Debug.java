package com.think.debug;

public class Debug {
    public static void debug(String...st) {
        for (String string : st) {
            System.out.println(string);
        }
    }
}
