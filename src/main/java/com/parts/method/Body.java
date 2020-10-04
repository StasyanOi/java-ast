package com.parts.method;

import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Body implements Declaration {

    private List<com.parts.method.Declaration> declarations;
    private List<com.parts.method.Expression> expressions;
    private List<com.parts.method.ForLoop> forLoops;
    private List<com.parts.method.WhileLoop> whileLoops;

    public Body(String body) {
        String[] split = body.split("\n");

        String bodyLine = String.join("", split);

        //get for loops
        List<String> forLoops = getForLoops(split);
        for (int i = 0; i < forLoops.size(); i++) {
            bodyLine = bodyLine.replace(forLoops.get(i), "");
        }

        //get while loops
        List<String> whileLoops = getWhileLoops(split);
        for (int i = 0; i < whileLoops.size(); i++) {
            bodyLine = bodyLine.replace(whileLoops.get(i), "");
        }

        //get expression
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings = bodyLine.split("\n");
        List<String> expressions = getExpressions(strings);
        bodyLine = bodyLine.replace("\n","");
        for (int i = 0; i < expressions.size(); i++) {
            bodyLine = bodyLine.replace(expressions.get(i),"");
        }

        //get declaration
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings1 = bodyLine.split("\n");
        List<String> declarations = getDeclarations(strings1);
        for (int i = 0; i < declarations.size(); i++) {
            bodyLine = bodyLine.replace(declarations.get(i),"");
        }




    }

    private List<String> getDeclarations(String[] strings1) {
        List<String> collect = Arrays.stream(strings1).filter(line -> line.trim()
                .matches("[A-Za-z0-9]* [a-z][a-zA-Z0-9]*;"))
                .collect(Collectors.toList());
        return collect;
    }

    private List<String> getExpressions(String[] split) {
        List<String> expressions = Arrays.stream(split)
                .filter(line -> line.matches(".*[+-/*=].*"))
                .collect(Collectors.toList());
        return expressions;
    }

    private List<String> getWhileLoops(String[] split) {
        List<String> whileLoops = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String whileLoop = "";
            if (split[i].contains("while")) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    whileLoop += split[i];
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1){
                        break;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    ++i;
                }
                whileLoops.add(whileLoop);
            }
        }
        return whileLoops;
    }

    private List<String> getForLoops(String[] split) {
        List<String> forLoops = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String forLoop = "";
            if (split[i].contains("for")) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    forLoop += split[i];
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1){
                        break;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    ++i;
                }
                forLoops.add(forLoop);
            }
        }
        return forLoops;
    }

    @Override
    public MutableNode getNode() {
        return null;
    }
}
