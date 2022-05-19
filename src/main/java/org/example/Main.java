package org.example;

public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.compute("a + (a * b)"));
        System.out.println(calculator.calculate("2 2 2 * +"));
    }
}
