package com;

import static java.lang.Integer.parseInt;

public class Test1{

    static int calculateFactorial(int n){
        int r = 1;
        for (int i = 1; i <= n; i++){
            r = r * i;
        }
        return r;
    }

    public static void main(String[] args){

        String string = "10";

        int l = parseInt(string);

        int x = calculateFactorial(l);

        if (x > 10) {
            System.out.println(x + " is bigger than 10");
        }

        if (x <= 10) {
            System.out.println(x + " is equal or less than 10");
        }
    }
}