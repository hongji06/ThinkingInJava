package com.think.reusing;

public class Villian {
    private String name;

    protected void set(String name) {
        this.name = name;
    }

    public Villian(String name) {
        this.name = name;
    }

    public String toString() {
        return "I'm a Villian and my name is " + name;
    }
}
