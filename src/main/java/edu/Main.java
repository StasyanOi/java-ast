package edu;

import edu.generator.JavaParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class Main {

    public static void main(String[] args) throws IOException {
        Path javaFilePath = null;
        if (args.length == 0) {
            javaFilePath = Paths.get("java");
        }
        try (Stream<Path> list = Files.list(requireNonNull(javaFilePath))) {
            list.map(Path::getFileName)
                    .filter(path -> path.getFileName().toString().endsWith(".java"))
                    .forEach(JavaParser::createAST);
        }
    }
}
