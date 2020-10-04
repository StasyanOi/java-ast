package com.parts.method;

import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

import java.util.List;

public class Body implements Declaration {

    private List<com.parts.method.Declaration> declarations;
    private List<com.parts.method.Expression> expressions;
    private List<com.parts.method.ForLoop> forLoops;

    @Override
    public MutableNode getNode() {
        return null;
    }
}
