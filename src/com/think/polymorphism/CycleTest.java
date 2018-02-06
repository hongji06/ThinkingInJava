package com.think.polymorphism;

public class CycleTest {
    public static void ride(Cycle cycle) {
        cycle.ride();
    }

    public static void main(String[] args) {
        Bicycle bicycle = new Bicycle();
        Unicycle unicycle = new Unicycle();
        Tricycle tricycle = new Tricycle();
        ride(bicycle);
        ride(unicycle);
        ride(tricycle);
    }

}
