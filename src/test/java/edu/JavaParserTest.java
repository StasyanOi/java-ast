package edu;

import edu.generator.JavaParser;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class JavaParserTest {

    @Test
    public void testCreateAST() throws IOException {
        Path testClass = Paths.get("Test1.java");
        JavaParser.createAST(testClass);

        String generatedDotFile = Files.lines(Paths.get("ASTs/AST_Test1.dot"))
                .collect(Collectors.joining());
        String expectedDotFile = Files.lines(Paths.get("src/test/resources/expectedTest1.dot"))
                .collect(Collectors.joining());

        assertEquals(generatedDotFile, expectedDotFile);
    }
}