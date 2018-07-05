package util;

import org.junit.Test;

import java.nio.file.Paths;

public class RunCodeTest {

    private String getFileName(String fileName) {
        return Paths.get(fileName).getFileName().toString().replaceFirst("[.][^.]+$", "");
    }

    @Test
    public void testRunCodeNotCompileError() {
        // Test above code here.
        SourceCode sourceCode = null;
        try {
            String fileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/test/java/util/HelloWorld.txt";
            String codeInString = RunCode.readFileAsString(fileName);
            sourceCode = new SourceCode(getFileName(fileName), codeInString, "java");
            RunCode runCode = new RunCode(sourceCode);
            runCode.executeCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
