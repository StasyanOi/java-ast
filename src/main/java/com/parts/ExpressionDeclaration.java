package com.parts;

import com.JavaParser;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionDeclaration implements Declaration {
    private String var;
    private String expression;
    private String value;

    public ExpressionDeclaration(String var) {
        this.var = var;
    }

    public MutableNode getNode(){
        MutableNode exprNode = JavaParser.getNode(expression);
        MutableNode varNode = JavaParser.getNode(var);
        MutableNode valueNode = JavaParser.getNode(value);
        exprNode.addLink(varNode);
        exprNode.addLink(valueNode);
        return exprNode;
    }
}
