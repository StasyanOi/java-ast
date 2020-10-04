package com.parts;

import com.JavaParser;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.StringTokenizer;

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
}
