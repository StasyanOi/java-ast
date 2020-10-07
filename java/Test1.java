package com;

import static java.lang.Integer.parseInt;

public class Test1{

    static int calculateFactorial(int n){
        int r = 1;
        for (int i = 1; i <= n; i++){
            r = r * i;
            if (r < 100)
            {
                while (r > 20) {
                    ++r;
                }
            }
        }

        return r;
    }

    public static void main(String[] args){

        String string = "10";

        int l = parseInt(string);
        if (l == 10) {
            l = 100;
        }

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