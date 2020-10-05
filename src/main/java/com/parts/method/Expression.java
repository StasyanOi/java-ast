package com.parts.method;

import com.JavaParser;
import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expression implements Declaration {
    Declaration leftOperand;
    String expression;
    Declaration rightOperand;

    public Expression(String expressions) {
        this.expression = expressions;
    }

    @Override
    public MutableNode getNode() {
        MutableNode expr = JavaParser.getNode("expr");
        MutableNode expression = JavaParser.getNode(this.expression);
        expr.addLink(expression);
        return expr;
    }
}
