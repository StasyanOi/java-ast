package com.parts.method;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ShuntingYard {

    private final Map<Character, Operator> operators;

    private static void addNode(Stack<ASTNode> stack, char operator) {
        final ASTNode rightASTNode = stack.pop();
        final ASTNode leftASTNode = stack.pop();
        stack.push(new ASTNode(operator, leftASTNode, rightASTNode));
    }

    public ShuntingYard(Collection<Operator> operators) {
        this.operators = new HashMap<>();
        for(Operator o : operators) {
            this.operators.put(o.getSymbol(), o);
        }
    }

    public ASTNode convertInfixNotationToAST(final String input) {
        final Stack<Character> operatorStack = new Stack<>();
        final Stack<ASTNode> operandStack = new Stack<>();
        final char[] chars = input.toCharArray();
        main:
        for(char c : chars) {
            char popped;
            switch(c) {
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    break;
                case ')':
                    while(!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if('(' == popped) {
                            continue main;
                        } else {
                            addNode(operandStack, popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if(operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while(!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if((!o1.isRightAssociative() &&
                                    0 == o1.comparePrecedence(o2)) ||
                                    o1.comparePrecedence(o2) < 0) {
                                operatorStack.pop();
                                addNode(operandStack, o2.getSymbol());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        operandStack.push(new ASTNode(c, null, null));
                    }
                    break;
            }
        }
        while(!operatorStack.isEmpty()) {
            addNode(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }

    public String convertInfixNotationToRPN(final String input) {
        final Stack<Character> operatorStack = new Stack<>();
        final StringBuilder sb = new StringBuilder();
        final char[] chars = input.toCharArray();
        main:
        for(char c : chars) {
            char popped;
            switch(c) {
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    break;
                case ')':
                    while(!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if('(' == popped) {
                            continue main;
                        } else {
                            sb.append(" ").append(popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if(operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while(!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if((!o1.isRightAssociative() &&
                                    0 == o1.comparePrecedence(o2)) ||
                                    o1.comparePrecedence(o2) < 0) {
                                operatorStack.pop();
                                sb.append(" ").append(o2.getSymbol());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        if(sb.length() > 0) {
                            sb.append(" ");
                        }
                        sb.append(c);
                    }
                    break;
            }
        }
        while(!operatorStack.isEmpty()) {
            sb.append(" ").append(operatorStack.pop());
        }
        return sb.toString();
    }


}