package com.think.initialazation;

public class FinalizeTestMain {

    public static void main(String[] args) {
        FinalizeTest finalizeTest = new FinalizeTest("hello world.");
        finalizeTest.sayHi();
        try {
            finalizeTest.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.gc();
    }

}
