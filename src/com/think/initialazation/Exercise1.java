package com.think.initialazation;

public class Exercise1 {

    public static void main(String[] args) {
       Test test=  new Test();
       System.out.println(test.string);
       
//       String s1 = "abc";
//       String s2 = "abc";
//       System.out.println(s1==s2);//结果是true
       
//       String s1 = "abc";
//       System.out.println(s1 == new String("abc"));//打印结果是false
       
       String s=new String("abc"); 
       String s1="abc"; 
       String s2=new String("abc"); 
       System.out.println(s==s1);
       System.out.println(s1==s2);
       System.out.println(s==s2);
       
    }

}
