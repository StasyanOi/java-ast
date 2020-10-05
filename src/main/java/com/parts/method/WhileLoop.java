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
public class WhileLoop implements Declaration {

    Expression expression;
    Body body;

    public WhileLoop(String whileLoop) {

    }

    @Override
    public MutableNode getNode() {
        return null;
    }
}
