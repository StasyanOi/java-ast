package com.parts.method;

import com.parts.Declaration;
import guru.nidi.graphviz.model.MutableNode;

import java.util.ArrayList;
import java.util.List;

public class Body implements Declaration {

    private List<com.parts.method.Declaration> declarations;
    private List<com.parts.method.Expression> expressions;
    private List<com.parts.method.ForLoop> forLoops;

    public Body(String body) {
        String[] split = body.split("\n");
        List<String> forLoops = getForLoops(split);
        List<String> whileLoops = getWhileLoops(split);

        System.out.println(false);
    }

    private List<String> getWhileLoops(String[] split) {
        List<String> whileLoops = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String whileLoop = "";
            if (split[i].contains("while")) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    whileLoop += split[i];
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1){
                        break;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    ++i;
                }
                whileLoops.add(whileLoop);
            }
        }
        return whileLoops;
    }

    private List<String> getForLoops(String[] split) {
        List<String> forLoops = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String forLoop = "";
            if (split[i].contains("for")) {
                int leftBraceCounter = 0;
                int rightBraceCounter = 0;
                while (true) {
                    forLoop += split[i];
                    if (split[i].contains("{")) {
                        ++leftBraceCounter;
                    }
                    if (split[i].contains("}") && leftBraceCounter - rightBraceCounter == 1){
                        break;
                    }
                    if (split[i].contains("}")) {
                        ++rightBraceCounter;
                    }
                    ++i;
                }
                forLoops.add(forLoop);
            }
        }
        return forLoops;
    }

    @Override
    public MutableNode getNode() {
        return null;
    }
}
