package com.tplate.coresystem.core;

public class SegregationProblem {
    /**
     * When segregating interfaces it's possible have 2 interfaces with de same method definitions.
     * The conflict is resolved by overriding conflicted methods and calling interfaces in the
     * required order using keyword super, just like this example.
     */
}

interface MyInterface1{
    public static int num = 100;
    public default void display() {
        System.out.println("display method of MyInterface1");
    }
}
interface MyInterface2{
    public static int num = 1000;
    public default void display() {
        System.out.println("display method of MyInterface2");
    }
}
class InterfaceExample implements MyInterface1, MyInterface2{
    public void display() {
        MyInterface1.super.display();
        //or,
        MyInterface2.super.display();
    }
    public static void main(String args[]) {
        InterfaceExample obj = new InterfaceExample();
        obj.display();
    }
}