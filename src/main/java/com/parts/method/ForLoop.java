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
public class ForLoop implements Declaration {

    com.parts.method.Expression init;
    com.parts.method.Expression limit;
    com.parts.method.Expression increment;
    com.parts.method.Body body;

    public ForLoop(String forLoop) {
        int start = forLoop.indexOf("(");
        int finish = forLoop.indexOf(")");
        int bodyStart = forLoop.indexOf("{");
        String expressions = forLoop.substring(start, finish+1)
                .replace("(", "")
                .replace(")", "");
        String[] expressionsObj = expressions.split(";");
        this.init = new Expression(expressionsObj[0]);
        this.limit = new Expression(expressionsObj[1]);
        this.increment = new Expression(expressionsObj[2]);
        String body = forLoop.substring(bodyStart);
        this.body = new Body(body);
    }

    @Override
    public MutableNode getNode() {
        MutableNode forLoop = JavaParser.getNode("for");

        forLoop.addLink(init.getNode());
        forLoop.addLink(limit.getNode());
        forLoop.addLink(increment.getNode());
        forLoop.addLink(body.getNode());
        return forLoop;
    }
}
