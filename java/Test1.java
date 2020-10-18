package com;

import static java.lang.Integer.parseInt;

public class Test1{

    public static void main(String[] args){

        String s = "10";

        int l = parseInt(s);

        for (int i = 0; i < 100; i++) {
            if (true) {
                l = 10 + s;
                System.out.println("Qwe");
                if (true) {
                    l = 10 + s;
                    System.out.println("Qwe");
                }
            }
            if (true) {
                l = 10 + s;
                System.out.println("Qwe");
            }
        }

        int x = calculateFactorial(l);
        if (x > 10) {
            while (true) {
                while (x < 10000) {
                    System.out.println("Test 2");
                }
                System.out.println("Test 2");
            }
            System.out.println(x + " is bigger than 10");
        }

        if (x <= 10) {
            if (x <= 10) {
                System.out.println(x + " is equal or less than 10");
            }
            System.out.println(x + " is equal or less than 10");
            if (x <= 10) {
                System.out.println(x + " is equal or less than 10");
            }
        }
    }
}