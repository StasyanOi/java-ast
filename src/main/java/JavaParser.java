import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

public class JavaParser {
    public static void parse(String[] args) throws IOException {

        Path javaFilePath = null;

        if (args.length == 0){
            javaFilePath = Paths.get("java");
        }

        Files.list(requireNonNull(javaFilePath))
                .filter(path -> path.getFileName().toString().endsWith(".java"))
                .map(Path::getFileName)
                .forEach(JavaParser::createAST);
    }

    @SneakyThrows
    private static void createAST(Path javaFile) {

        //create dot representation

        String ASTFileName = "AST_" + javaFile.toString().replace(".java", "") + ".dot";
        Path ASTFile = Paths.get("ASTs", ASTFileName);

        Files.deleteIfExists(ASTFile);
        Files.createFile(ASTFile);
    }
}
