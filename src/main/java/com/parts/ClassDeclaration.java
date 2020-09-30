package com.parts;

import com.JavaParser;
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
        MutableNode clazz = JavaParser.getNode(name);
        MutableNode body = JavaParser.getNode("body ");

        MutableNode modifiers = JavaParser.getNode(String.join(" ", this.modifiers));

        //change content to separate classes
        MutableNode content = JavaParser.getNode(this.body);

        body.addLink(content);
        clazz.addLink(body);
        clazz.addLink(modifiers);

        return clazz;
    }
}
