package com.think.reusing;

class Poppet {
    public int m;

    public Poppet(int i) {
    }
}

public class BlankFinal {
    private final Poppet p;

    public BlankFinal() {
        p = new Poppet(1);
    }

    public BlankFinal(int i) {
        p = new Poppet(i);
    }

    public static void main(String[] args) {
        BlankFinal aBlankFinal = new BlankFinal();
        BlankFinal blankFinal = new BlankFinal(40);
        aBlankFinal.p.m=10;
//        aBlankFinal.p=new Poppet(5);
        blankFinal.p.m=1;
       
    }

}
