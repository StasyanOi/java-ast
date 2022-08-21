package edu.generator.parts.method;

import edu.generator.JavaParser;
import edu.generator.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Body implements Declaration {

    private final List<If> ifs;
    private final List<edu.generator.parts.method.Declaration> declarations;
    private final List<Expression> expressions;
    private final List<ForLoop> forLoops;
    private final List<WhileLoop> whileLoops;

    public Body(String body) {
        body = body.replace("{", "\n{\n")
                .replace(";", ";\n")
                .replace("}", "}\n");

        body = body.substring(0, body.length() - 1);
        String[] split = body.split("\n");

        String bodyLine = String.join("", split);

        //get ifs
        List<String> ifs = getIfs(split);
        for (String anIf : ifs) {
            bodyLine = bodyLine.replace(anIf, "");
        }

        this.ifs = ifs.stream()
                .map(If::new)
                .collect(toList());

        //get for loops
        List<String> forLoops = getForLoops(split);
        for (String forLoop : forLoops) {
            bodyLine = bodyLine.replace(forLoop, "");
        }

        this.forLoops = forLoops.stream()
                .map(ForLoop::new)
                .collect(toList());

        //get while loops
        List<String> whileLoops = getWhileLoops(split);
        for (String whileLoop : whileLoops) {
            bodyLine = bodyLine.replace(whileLoop, "");
        }

        this.whileLoops = whileLoops.stream()
                .map(WhileLoop::new)
                .collect(toList());

        //get expression
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings = bodyLine.split("\n");
        List<String> expressions = getExpressions(strings);
        bodyLine = bodyLine.replace("\n", "");
        for (String expression : expressions) {
            bodyLine = bodyLine.replace(expression, "");
        }

        this.expressions = expressions.stream()
                .map(Expression::new)
                .collect(toList());

        //get declaration
        bodyLine = bodyLine.replace("{", "{\n").replace(";", ";\n");
        String[] strings1 = bodyLine.split("\n");
        List<String> declarations = getDeclarations(strings1);
        for (String declaration : declarations) {
            bodyLine = bodyLine.replace(declaration, "");
        }

        this.declarations = declarations.stream()
                .map(edu.generator.parts.method.Declaration::new)
                .collect(toList());
    }

    private List<String> getDeclarations(String[] strings1) {
        return Arrays.stream(strings1).filter(line -> line.trim()
                        .matches("[A-Za-z0-9]* [a-z][a-zA-Z0-9]*;"))
                .collect(toList());
    }

    private List<String> getExpressions(String[] split) {
        return Arrays.stream(split)
                .filter(line -> line.matches(".*[+-/*=].*"))
                .collect(toList());
    }

    private List<String> getWhileLoops(String[] split) {
        List<String> whileLoops = new ArrayList<>();
        int bracket = 0;
        for (int i = 0; i < split.length; i++) {
            StringBuilder whileLoop = new StringBuilder();
            if (split[i].contains("while") && bracket == 1) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    whileLoop.append(split[i]);
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1) {
                        break;
                    }
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    if (split[i].contains("{")) {
                        ++bracket;
                    }
                    if (split[i].contains("}")) {
                        --bracket;
                    }
                    ++i;
                }
                whileLoops.add(whileLoop.toString());
            }
            if (split[i].contains("{")) {
                ++bracket;
            }
            if (split[i].contains("}")) {
                --bracket;
            }
        }
        return whileLoops;
    }

    private List<String> getForLoops(String[] split) {
        List<String> forLoops = new ArrayList<>();
        int bracket = 0;
        for (int i = 0; i < split.length; i++) {
            StringBuilder forLoop = new StringBuilder();
            if (split[i].contains("for") && bracket == 1) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    forLoop.append(split[i]);
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1) {
                        break;
                    }
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    if (split[i].contains("{")) {
                        ++bracket;
                    }
                    if (split[i].contains("}")) {
                        --bracket;
                    }
                    ++i;
                }
                forLoops.add(forLoop.toString());
            }
            if (split[i].contains("{")) {
                ++bracket;
            }
            if (split[i].contains("}")) {
                --bracket;
            }
        }
        return forLoops;
    }

    private List<String> getIfs(String[] split) {
        List<String> Ifs = new ArrayList<>();
        int bracket = 0;
        for (int i = 0; i < split.length; i++) {
            StringBuilder anIf = new StringBuilder();
            if (split[i].contains("if") && bracket == 1) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    anIf.append(split[i]);
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1) {
                        break;
                    }
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    if (split[i].contains("{")) {
                        ++bracket;
                    }
                    if (split[i].contains("}")) {
                        --bracket;
                    }
                    ++i;
                }
                Ifs.add(anIf.toString());
            }
            if (split[i].contains("{")) {
                ++bracket;
            }
            if (split[i].contains("}")) {
                --bracket;
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
