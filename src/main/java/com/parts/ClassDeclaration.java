package com.parts;

import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static guru.nidi.graphviz.model.Factory.mutNode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassDeclaration {
    private String name;
    private List<String> modifiers;
    private String body;

    public MutableNode getNode(){
        MutableNode clazz = mutNode(name, true);
        MutableNode body = mutNode("body", true);
        MutableNode mod = mutNode("modifiers", true);

        clazz.addLink(mod);
        clazz.addLink(body);

        List<MutableNode> modifierNodes = modifiers.stream().map(mutNode -> mutNode(mutNode, true))
                .collect(Collectors.toList());
        //change content to separate classes
        MutableNode content = mutNode(this.body, true);

        modifierNodes.forEach(mod::addLink);
        body.addLink(content);

        return clazz;
    }
}
