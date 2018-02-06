/**   
 * @{#} SimpleRead.java Create on 2018年1月31日 上午11:10:27 
 * @Title:  SimpleRead.java   
 * @Package chapter13.strings   
 * @Description:   
 * @author: <a href="https://github.com/hongji06">hongji06</a>   
 * @version 1.0 
 * Copyright (c) 2018 by XuHongji.   
 */
package chapter13.strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class SimpleRead {

    private static BufferedReader input = new BufferedReader(new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name");

            String name = input.readLine();
            System.out.println(name);
            System.out.println("How old are you? what is your favorite double?");
            System.out.println("input:<age> <double>");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.println("age:" + age);
            System.out.println("favorite:" + favorite);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
