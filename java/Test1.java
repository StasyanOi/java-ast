package com;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.collectingAndThen;

public class Test1 {

    int a = 0;

    public static void main(String[] args) {
        int a = 123;
        int b = 123;
        int c = a + b;

        int i;

        for (int i = 0; i < 10; i++) {
            int g = 123;
            int v = 123;
            int c = g * v;
        }
    }
}

public class Test2 {

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}