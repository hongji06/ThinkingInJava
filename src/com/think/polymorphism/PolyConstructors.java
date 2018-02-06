package com.think.polymorphism;

class Glyph{
    private final int j = 7;
    void draw() {
        System.out.println("Glyph.draw()");
    }
    
    public Glyph() {
        System.out.println("Glyph() before draw.");
        draw();
        System.out.println("Glyph() after draw.");
        System.out.println("init final j = "+j);
    }
}

class RoundGlygh extends Glyph{
    private int radius = 1;
    
    RoundGlygh(int i) {
        radius=i;
        System.out.println("RoundGlyph.RoundGlygh,radius = "+radius);
//        System.out.println("super.i = "+super.j);
    }

    void draw() {
        System.out.println("RoundGlyph.draw(),radius = "+radius);
    }
}

public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlygh(5);
    }
}
