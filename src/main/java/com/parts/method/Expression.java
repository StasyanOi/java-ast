package com.parts.method;

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

    }

    @Override
    public MutableNode getNode() {
        return null;
    }
}
