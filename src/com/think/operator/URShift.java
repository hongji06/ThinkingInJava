package com.think.operator;

public class URShift {
    public static void main(String[] args) {
        int i=-1;
        System.out.println(Integer.toBinaryString(i));
        i>>>=10;
        System.out.println(Integer.toBinaryString(i));
        long l = -1;
        System.out.println(Long.toBinaryString(l));
        l>>>=10;
        System.out.println(Long.toBinaryString(l));
        
        int x=0,y=-1,z=0;
        System.out.println(Integer.toBinaryString(z));
        System.out.println(Integer.toBinaryString(y));
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x&z));
        System.out.println(Integer.toBinaryString(y>>>2));
        System.out.println(Integer.toBinaryString(x&(y>>>2)));
        
    }
}
