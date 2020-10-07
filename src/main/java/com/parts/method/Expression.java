package com.parts.method;

import com.JavaParser;
import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expression implements Declaration {
    List<String> expressions = new ArrayList<>();

    public Expression(String expression) {

        if (expression.contains("==")) {
            addExpressions(expression, "==");
        } else if (expression.contains("=>")) {
            addExpressions(expression, "=>");
        } else if (expression.contains("<=")) {
            addExpressions(expression, "<=");
        } else if (expression.contains("!=")) {
            addExpressions(expression, "!=");
        } else if (expression.contains("=")) {
            addExpressions(expression, "=");
        } else if (expression.contains(">")) {
            addExpressions(expression, ">");
        } else if (expression.contains("<")) {
            addExpressions(expression, "<");
        } else {
            if (expression.contains("++")) {
                expressions.add("++");
                expressions.add(expression.replace("++", ""));
            } else if (expression.contains("--")){
                expressions.add("--");
                expressions.add(expression.replace("--", ""));
            } else {
                expressions.add(expression);
            }
        }
    }

    private void addExpressions(String expression, String s) {
        expressions.add(s);
        String[] split = expression.split(s);
        expressions.add(split[0].trim());
        expressions.add(split[1]);
    }

    @Override
    public MutableNode getNode() {
        MutableNode expr = JavaParser.getNode("expr " + expressions.get(0));
        expressions.stream()
                .skip(1)
                .map(this::getNodeInternal)
                .forEach(expr::addLink);
        return expr;
    }

    private MutableNode getNodeInternal(String exprPart) {
        exprPart = exprPart.replace(";", "");
        if (exprPart.contains("+") | exprPart.contains("/") | exprPart.contains("*") | exprPart.contains("-")) {
            if (exprPart.contains("++") | exprPart.contains("--")) {
                if (exprPart.contains("++")) {
                    return JavaParser.getNode("++").addLink(exprPart.replace("++", ""));
                } else {
                    return JavaParser.getNode("--").addLink(exprPart.replace("++", ""));
                }
            } else {
                ASTNode astNode = convertInfixNotationToAST(exprPart);
                MutableNode expr = astNode.getNode();
                return expr;
            }

        } else {
            return JavaParser.getNode(exprPart);
        }

    }

    private static void addNode(Stack<ASTNode> stack, char operator) {
        final ASTNode rightASTNode = stack.pop();
        final ASTNode leftASTNode = stack.pop();
        stack.push(new ASTNode(operator, leftASTNode, rightASTNode));
    }

    public ASTNode convertInfixNotationToAST(final String input) {
        final Collection<Operator> inputOps = new ArrayList<>();
        inputOps.add(new BaseOperator('^', true, 4));
        inputOps.add(new BaseOperator('*', false, 3));
        inputOps.add(new BaseOperator('/', false, 3));
        inputOps.add(new BaseOperator('+', false, 2));
        inputOps.add(new BaseOperator('-', false, 2));

        Map<Character, Operator> operators = new HashMap<>();
        for (Operator o : inputOps) {
            operators.put(o.getSymbol(), o);
        }

        final Stack<Character> operatorStack = new Stack<>();
        final Stack<ASTNode> operandStack = new Stack<>();
        final char[] chars = input.toCharArray();
        main:
        for (char c : chars) {
            char popped;
            switch (c) {
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    break;
                case ')':
                    while (!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if ('(' == popped) {
                            continue main;
                        } else {
                            addNode(operandStack, popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if (operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while (!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if ((!o1.isRightAssociative() &&
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
        while (!operatorStack.isEmpty()) {
            addNode(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }
}
