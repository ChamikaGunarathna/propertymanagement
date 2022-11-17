package com.mycompany.propertymanagement.controller;

public class CalculatorMain {
    public static void main(String[] args) {
        CalculatorController cc = new CalculatorController();
        Double result = cc.add(8.3,9.7);
        System.out.println(result);
    }
}
