package edu.generator.parts;

import edu.generator.JavaParser;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionDeclaration implements Declaration {

    private String type;
    private String var;
    private String expression;
    private String value;

    public ExpressionDeclaration(String type, String var) {
        this.type = type;
        this.var = var;
    }

    public ExpressionDeclaration(String var, String expression, String value) {
        this.var = var;
        this.expression = expression;
        this.value = value;
    }

    public MutableNode getNode(){
        MutableNode exprNode = JavaParser.getNode(expression);
        MutableNode varNode = JavaParser.getNode(var);
        MutableNode valueNode = JavaParser.getNode(value);
        MutableNode typeNode = JavaParser.getNode(value);
        exprNode.addLink(typeNode);
        exprNode.addLink(varNode);
        exprNode.addLink(valueNode);
        return exprNode;
    }
}
