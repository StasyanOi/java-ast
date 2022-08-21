package edu.generator.parts.method;

import edu.generator.JavaParser;
import edu.generator.parts.Declaration;
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

    private Expression expression;
    private Body body;

    public WhileLoop(String whileLoop) {
        int start = whileLoop.indexOf("(");
        int finish = whileLoop.indexOf(")");
        int bodyStart = whileLoop.indexOf("{");
        String expressions = whileLoop.substring(start, finish+1)
                .replace("(", "")
                .replace(")", "");
        this.expression = new Expression(expressions);
        String body = whileLoop.substring(bodyStart);
        this.body = new Body(body);
    }

    @Override
    public MutableNode getNode() {
        MutableNode whileLoop = JavaParser.getNode("while");

        whileLoop.addLink(expression.getNode());
        whileLoop.addLink(body.getNode());
        return whileLoop;
    }
}
