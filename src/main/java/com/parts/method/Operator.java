package com.parts.method;

public interface Operator {

    boolean isRightAssociative();


    int comparePrecedence(Operator o);


    String getSymbol();


}