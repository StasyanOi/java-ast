package com;

import static java.lang.Integer.parseInt;

public class Test1{

    static int calculateFactorial(int n){
        int r = 1;
        for (int i = 1; i <= n; i++){
            r = r * i;
            if (r < 400) {
                System.out.println("Test");
                while (true) {
                    System.out.println("Test 2");
                }
            }
        }
        if (r > 100) {
            System.out.println("The factorial is begger than 100");
        }
        return r;
    }

    public static void main(String[] args){

        String string = "10";

        int l = parseInt(string);

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        int x = calculateFactorial(l);
        if (x > 10) {
            System.out.println(x + " is bigger than 10");
        }
        if (x <= 10) {
            System.out.println(x + " is equal or less than 10");
        }
    }
}