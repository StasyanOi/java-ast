package com.parts;

import com.JavaParser;
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
public class MethodDeclaration implements Declaration{
    private String name;
    private String modifiers;
    private String returnType;
    private String params;
    private String body;

    public MutableNode getNode(){
        MutableNode methodNode = JavaParser.getNode(this.name);
        MutableNode returnTypeNode = JavaParser.getNode(this.returnType);
        MutableNode modifierNode = JavaParser.getNode(this.modifiers);
        MutableNode paramNode = JavaParser.getNode(this.params);
        MutableNode bodyNode = JavaParser.getNode(this.body);

        List<Declaration> declarations = getBodyNodes(this.body);

        methodNode.addLink(modifierNode);
        methodNode.addLink(paramNode);
        methodNode.addLink(bodyNode);
        methodNode.addLink(returnTypeNode);
        return methodNode;
    }

    private List<Declaration> getBodyNodes(String body) {
        String newBody = body.replace("}", "};");

        body = newBody.substring(0,newBody.length() - 1);

        StringTokenizer stringTokenizer = new StringTokenizer( "");

        return null;
    }
    private enum Operator
    {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    }};

    private static boolean isHigerPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

    public static String postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            // operator
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigerPrec(token, stack.peek()))
                    output.append(stack.pop()).append(' ');
                stack.push(token);

                // left parenthesis
            } else if (token.equals("(")) {
                stack.push(token);

                // right parenthesis
            } else if (token.equals(")")) {
                while ( ! stack.peek().equals("("))
                    output.append(stack.pop()).append(' ');
                stack.pop();

                // digit
            } else {
                output.append(token).append(' ');
            }
        }

        while ( ! stack.isEmpty())
            output.append(stack.pop()).append(' ');

        return output.toString();
    }

}
