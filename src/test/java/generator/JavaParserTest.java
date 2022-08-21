package generator;

import guru.nidi.graphviz.engine.Format;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class JavaParserTest {

    @Test
    public void testCreateAST() throws IOException {
        Path testClass = Paths.get("Test1.java");

        String dotAstString = JavaParser.createAST(testClass, Format.DOT);

        try (Stream<String> lines = Files.lines(Paths.get("src/test/resources/expectedTest1.dot"))) {
            String expectedDotFile = lines
                    .collect(Collectors.joining("\n"));
            assertEquals(dotAstString, expectedDotFile);
        }
    }
}