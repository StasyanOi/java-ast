package com.parts.method;

import com.JavaParser;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Declaration implements com.parts.Declaration {
    String type;
    String var;

    public Declaration(String declaration) {
        String trimedDecl = declaration.trim();
        trimedDecl = trimedDecl.substring(0, trimedDecl.length() - 1);
        String[] typeAndVar = trimedDecl.split(" ");
        this.type = typeAndVar[0];
        this.var = typeAndVar[1];
    }

    @Override
    public MutableNode getNode() {
        MutableNode decl = JavaParser.getNode("decl");
        MutableNode typeNode = JavaParser.getNode(type);
        MutableNode varNode = JavaParser.getNode(var);
        decl.addLink(typeNode);
        decl.addLink(varNode);
        return decl;
    }
}
