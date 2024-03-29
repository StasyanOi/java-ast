# java-ast

Abstract Syntax Tree (AST): https://en.wikipedia.org/wiki/Abstract_syntax_tree

DOT file parsing: https://dreampuf.github.io

This library supports parsing of java code to AST for while and for loops, basic ifs (without else), expressions with numbers and 1 letter variables and declarations. 

### Usage:
```
import generator.JavaParser;
import guru.nidi.graphviz.engine.Format;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Path javaFilePath = Paths.get("JavaClass.java");

        String astDotFileData = JavaParser.createAST(javaFilePath, Format.DOT);

        Files.write(Paths.get("JavaClass.dot"), astDotFileData.getBytes(StandardCharsets.UTF_8));
    }
}
```
## Example:
### Source code:
```java
package com;

import static java.lang.Integer.parseInt;

public class Test1{

    static int calculateFactorial(int n){
        int r = 1;
        for (int i = 1; i <= n; i++){
            r = r * i;
        }
        return r;
    }

    public static void main(String[] args){

        String string = "10";

        int l = parseInt(string);

        int x = calculateFactorial(l);

        if (x > 10) {
            System.out.println(x + " is bigger than 10");
        }

        if (x <= 10) {
            for (int i = 0; i < 10; i++) {
                System.out.println(x + " is equal or less than 10");
            }
        }
    }
}
```

### Generated DOT graph:
![Image](images/AST_Test1.svg)
