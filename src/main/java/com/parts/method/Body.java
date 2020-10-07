package com.parts.method;

import com.JavaParser;
import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Body implements Declaration {

    private List<com.parts.method.If> ifs;
    private List<com.parts.method.Declaration> declarations;
    private List<com.parts.method.Expression> expressions;
    private List<com.parts.method.ForLoop> forLoops;
    private List<com.parts.method.WhileLoop> whileLoops;

    public Body(String body) {
        body = body.replace("{", "\n{\n")
                .replace(";", ";\n")
                .replace("}", "}\n");

        body = body.substring(0, body.length() - 1);
        String[] split = body.split("\n");

        String bodyLine = String.join("", split);

        //get for loops
        List<String> forLoops = getForLoops(split);
        for (int i = 0; i < forLoops.size(); i++) {
            bodyLine = bodyLine.replace(forLoops.get(i), "");
        }

        this.forLoops = forLoops.stream()
                .map(ForLoop::new)
                .collect(toList());

        //get while loops
        List<String> whileLoops = getWhileLoops(split);
        for (int i = 0; i < whileLoops.size(); i++) {
            bodyLine = bodyLine.replace(whileLoops.get(i), "");
        }

        this.whileLoops = whileLoops.stream()
                .map(WhileLoop::new)
                .collect(toList());

        //get ifs
        List<String> ifs = getIfs(split);
        for (int i = 0; i < ifs.size(); i++) {
            bodyLine = bodyLine.replace(ifs.get(i),"");
        }

        this.ifs = ifs.stream()
                .map(com.parts.method.If::new)
                .collect(toList());



        //get expression
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings = bodyLine.split("\n");
        List<String> expressions = getExpressions(strings);
        bodyLine = bodyLine.replace("\n","");
        for (int i = 0; i < expressions.size(); i++) {
            bodyLine = bodyLine.replace(expressions.get(i),"");
        }

        this.expressions = expressions.stream()
                .map(Expression::new)
                .collect(toList());

        //get declaration
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings1 = bodyLine.split("\n");
        List<String> declarations = getDeclarations(strings1);
        for (int i = 0; i < declarations.size(); i++) {
            bodyLine = bodyLine.replace(declarations.get(i),"");
        }

        this.declarations = declarations.stream()
                .map(com.parts.method.Declaration::new)
                .collect(toList());


    }

    private List<String> getDeclarations(String[] strings1) {
        List<String> collect = Arrays.stream(strings1).filter(line -> line.trim()
                .matches("[A-Za-z0-9]* [a-z][a-zA-Z0-9]*;"))
                .collect(toList());
        return collect;
    }

    private List<String> getExpressions(String[] split) {
        List<String> expressions = Arrays.stream(split)
                .filter(line -> line.matches(".*[+-/*=].*"))
                .collect(toList());
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

    private List<String> getIfs(String[] split) {
        List<String> Ifs = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String If = "";
            if (split[i].contains("if")) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    If += split[i];
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
                Ifs.add(If);
            }
        }
        return Ifs;
    }

    @Override
    public MutableNode getNode() {
        MutableNode body = JavaParser.getNode("body");
        expressions.forEach(expression -> body.addLink(expression.getNode()));
        declarations.forEach(declaration -> body.addLink(declaration.getNode()));
        forLoops.forEach(forLoop -> body.addLink(forLoop.getNode()));
        whileLoops.forEach(whileLoop -> body.addLink(whileLoop.getNode()));
        ifs.forEach(If -> body.addLink(If.getNode()));
        return body;
    }
}
