package com.parts;

import com.JavaParser;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Factory.node;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDeclaration implements Declaration {
    private String name;
    private List<String> modifiers;
    private String body;

    public MutableNode getNode(){
        MutableNode clazz = JavaParser.getNode(name);
        MutableNode body = JavaParser.getNode("body ");

        MutableNode modifiers = JavaParser.getNode(String.join(" ", this.modifiers));

        //change content to separate classes

        String[] bodyLines = this.body.replace("{", "{\n")
                .replace("}", "}\n")
                .replace(";", ";\n")
                .replace("{", " \n{")
                .split("\n");
        bodyLines = Arrays.stream(bodyLines).filter(s -> !s.isBlank()).toArray(String[]::new);

        List<Declaration> expressionsAndMethods = getExpressionsAndMethods(bodyLines);

        expressionsAndMethods.forEach(declaration -> body.addLink(declaration.getNode()));
        clazz.addLink(body);
        clazz.addLink(modifiers);

        return clazz;
    }

    private List<Declaration> getExpressionsAndMethods(String[] bodyLines) {

        List<ExpressionDeclaration> expressions = new ArrayList<>();
        List<MethodDeclaration> methods = new ArrayList<>();

        for (int i = 0; i < bodyLines.length; i++) {
            String bodyLine = bodyLines[i];
            if (bodyLine.contains("=") && bodyLine.contains(";")) {
                String[] expr = bodyLine.trim().split(" ");

                ExpressionDeclaration expressionDeclaration;
                if(expr.length == 2) {
                    expressionDeclaration = new ExpressionDeclaration(expr[1]);
                } else {
                    expressionDeclaration = new ExpressionDeclaration(expr[1], expr[2], expr[3]);
                }
                expressions.add(expressionDeclaration);
            } else if (bodyLine.contains("(") && bodyLine.contains(")") && !bodyLine.contains(";")) {
                int pivot = bodyLine.indexOf("(");
                String modifiersAndName = bodyLine.substring(0, pivot);
                int lastSpace = modifiersAndName.lastIndexOf(" ");
                String name = modifiersAndName.substring(lastSpace, pivot);
                String modifiersAndReturnType = modifiersAndName.substring(0, lastSpace);
                String returnType = modifiersAndReturnType.substring(modifiersAndReturnType.lastIndexOf(" "));
                String methodBody = getMethodBody(i, bodyLines);
                String modifiers = modifiersAndReturnType.replace(returnType, "");
                String params = getParams(bodyLine.substring(pivot));
                i = methodBody.charAt(methodBody.length()-1);
                methodBody = methodBody.substring(0,methodBody.length() - 1);
                MethodDeclaration methodDeclaration = new MethodDeclaration(name, modifiers, returnType, params, methodBody);
                methods.add(methodDeclaration);
            }
        }

        Stream<ExpressionDeclaration> expressionDeclarationStream = expressions.stream();
        Stream<MethodDeclaration> methodDeclarationStream = methods.stream();

        return Stream.concat(expressionDeclarationStream, methodDeclarationStream)
                .collect(Collectors.toList());
    }

    private String getParams(String params) {
        return params.replace("{", "").replace("(", "").replace(")", "");
    }

    private String getMethodBody(int i, String[] bodyLines) {
        StringBuilder methodBody  = new StringBuilder("");
        ++i;
        while (!bodyLines[i].contains("}")) {
            methodBody.append(bodyLines[i]).append("\n");
            ++i;
        }
        methodBody.append("}\n");
        return methodBody.toString()+i;
    }
}
