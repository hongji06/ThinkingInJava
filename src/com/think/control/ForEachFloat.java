package com.think.control;

import java.util.Random;

public class ForEachFloat {
    public static void main(String[] args) {
        Random random = new Random();
        float f[] = new float[10];
        for (int i = 0; i < f.length; i++) {
            f[i]=random.nextFloat();
        }
        
        for (float g : f) {
            System.out.println(g);
        }
        
        int n[]=new int[10];
        for (int i = 0; i < n.length; i++) {
            n[i]=random.nextInt(50);
        }
        for (int i : n) {
            System.out.println(i);
        }
    }
}
