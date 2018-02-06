package com.think.operator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringOperators {

    public static void main(String[] args) {
        long nameTime = System.currentTimeMillis();
        System.out.println(nameTime);
        Date date = new Date(nameTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
        System.out.println(simpleDateFormat.format(date));
        int x = 0, y = 1, z = 2;
        String s = "x,y,z ";
        System.out.println(s + x + y + z);
        System.out.println(x + " " + s);
        s += "(summed) = ";
        System.out.println(s + (x + y + z));
        System.out.println("" + x);
    }

}
