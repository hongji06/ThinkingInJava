package com.think.reusing;

class Insect {
    private int i = 9;
    protected int j;

    Insect() {
        System.out.println("i=" + i + ";j=" + j);
        j = 39;
    }
    // private static int x1=printInt("static Insect.x1 initialized");

    static int printInt(String string) {
        System.out.println(string);
        return 47;
    }
}

public class Beetle extends Insect {
    private int k = printInt("Beetle.k initialized");

    public Beetle() {
        System.out.println("k=" + k);
        System.out.println("j=" + j);
    }

    static int x2 = printInt("static Beetle.x2 initailized");

    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        // Beetle beetle = new Beetle();
        // Beetle beetle2 = new Beetle();
    }

}
