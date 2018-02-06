package com.think.initialazation;

public class FinalizeTest {
    String st="good jod";
    
    public FinalizeTest() {
    }
    
    public FinalizeTest(String st){
        this.st=st;
        System.out.println("init FinalizeTest=>"+st);
    }
    
    public void sayHi() {
        System.out.println("hello=>"+st);
    }
    
    @Override
    protected void finalize() throws Throwable {
        System.out.println("program finalized.");
        super.finalize();
    }

}
