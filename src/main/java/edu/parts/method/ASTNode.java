package edu.parts.method;

import edu.JavaParser;
import edu.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

public class ASTNode implements Declaration {

    private final String value;
    private final ASTNode leftASTNode;
    private final ASTNode rightASTNode;


    public ASTNode(String value, ASTNode leftASTNode, ASTNode rightASTNode) {
        this.value = value;
        this.leftASTNode = leftASTNode;
        this.rightASTNode = rightASTNode;
    }

    public String getValue() {
        return value;
    }


    public ASTNode getLeftASTNode() {
        return leftASTNode;
    }

    public ASTNode getRightASTNode() {
        return rightASTNode;
    }


    @Override
    public MutableNode getNode() {
        MutableNode root = JavaParser.getNode(String.valueOf(this.value));

        if (leftASTNode != null) {
            root.addLink(leftASTNode.getNode());
        }

        if (rightASTNode != null) {
            root.addLink(rightASTNode.getNode());
        }
        return root;
    }
}