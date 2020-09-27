package com.parts;

import guru.nidi.graphviz.model.Factory;
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
public class ImportDeclaration {
    private String name;

    public MutableNode getNode(){
        return Factory.mutNode(name);
    }
}
