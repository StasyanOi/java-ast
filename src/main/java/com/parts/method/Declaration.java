package com.parts.method;

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

    @Override
    public MutableNode getNode() {
        return null;
    }
}
