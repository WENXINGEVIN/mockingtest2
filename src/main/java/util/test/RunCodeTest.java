package util.test;

import java.nio.file.Paths;
import org.junit.Test;
import util.RunCode;
import util.SourceCode;

public class RunCodeTest {

	private String testFileNoCompilationError = "E:\\mockingtest2\\src\\main\\java\\util\\HelloWorld.txt";

    private String getFileName(String fileName) {
    	return Paths.get(fileName).getFileName().toString().replaceFirst("[.][^.]+$", "");
    }
    
    @Test
	public void testRunCodeWithNoCompilationError() {
	// Test above code here.
    SourceCode sourceCode = null;
    try {
        String codeInString = RunCode.readFileAsString(testFileNoCompilationError);
        sourceCode = new SourceCode(getFileName(testFileNoCompilationError), codeInString);
        RunCode.executeCode(sourceCode);
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
}
