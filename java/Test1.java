package com;

import static java.lang.Integer.parseInt;

public class Test1{

    public static void main(String[] args){

        String string = "10";

        int l = parseInt(string);

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                }
            }
        }

        int x = calculateFactorial(l);
        if (x > 10) {
            while (true) {
                while (true) {
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