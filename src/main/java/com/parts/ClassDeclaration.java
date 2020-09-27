package com.parts;

import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.model.Factory.mutNode;
import static guru.nidi.graphviz.model.Factory.node;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDeclaration {
    private String name;
    private List<String> modifiers;
    private String body;

    public MutableNode getNode(){
        MutableNode clazz = mutNode(name);
        MutableNode body = mutNode("body " + name);
        MutableNode mod = mutNode("modifiers " + name);

        List<MutableNode> modifierNodes = modifiers.stream().map(mutNode -> mutNode(mutNode))
                .collect(Collectors.toList());
        //change content to separate classes
        MutableNode content = mutNode(this.body);

        modifierNodes.forEach(mod::addLink);
        body.addLink(content);
        clazz.addLink(mod);
        clazz.addLink(body);

        return clazz;
    }
}
