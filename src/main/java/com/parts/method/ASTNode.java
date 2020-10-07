package com.parts.method;

import com.JavaParser;
import com.parts.Declaration;
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

    /***
     *
     * @return The value held by the node.
     */
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