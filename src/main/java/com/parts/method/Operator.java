package com.parts.method;

public interface Operator {

    boolean isRightAssociative();


    int comparePrecedence(Operator o);


    char getSymbol();


}