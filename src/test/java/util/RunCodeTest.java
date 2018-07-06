package util;

import org.junit.Test;

import java.nio.file.Paths;

public class RunCodeTest {

    private String getFileName(String fileName) {
        return Paths.get(fileName).getFileName().toString().replaceFirst("[.][^.]+$", "");
    }

    private void runCodeTest(String fileName, String ext) {
        SourceCode sourceCode = null;
        try {
            String codeInString = RunCode.readFileAsString(fileName);
            sourceCode = new SourceCode(getFileName(fileName), codeInString, ext);
            RunCode runCode = new RunCode(sourceCode);
            runCode.executeCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRunCodeSuccess() {
        // Test above code here.
        String testFileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/test/java/util/HelloWorld.txt";
        runCodeTest(testFileName, "java");
    }

    @Test
    public void testRunCodeNotCompileError() {
        // Test above code here.
        String testFileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/test/java/util/HelloWorld1.txt";
        runCodeTest(testFileName, "java");
    }

    @Test
    public void testRunCodeWithException() {
        // Test above code here.
        String testFileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/test/java/util/HelloWorld2.txt";
        runCodeTest(testFileName, "java");
    }

    @Test
    public void testRunCodePython() {
        // Test above code here.
        String testFileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/test/java/util/HelloWorldPython.txt";
        runCodeTest(testFileName, "py");
    }
}
