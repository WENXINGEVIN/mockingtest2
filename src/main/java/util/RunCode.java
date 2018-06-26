package util;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RunCode {
	private Runtime runtime;
	
	public RunCode() {
        runtime = Runtime.getRuntime();   
	}
	// Reconstruct source code to ".java" file, and return absolute file path
	private String generateSourceFile(SourceCode sourceCode) throws IOException {
		String usrDir = "";
		try {
			usrDir = RunCode.createTempDirectory().getAbsolutePath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fileFullPath = usrDir + sourceCode.getTitle() + ".java";
        File javaFile = new File(fileFullPath);
        FileWriter fileWriter;
		fileWriter = new FileWriter(javaFile);
		fileWriter.write(sourceCode.getCode());
     	fileWriter.close();
		
		return fileFullPath;
	}
	
	// The following code might contain bug, it is not tested
	private static File createTempDirectory() throws IOException {
		    final File temp;
		    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));
		    if(!(temp.delete())) {
		        throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
		    }
		    if(!(temp.mkdir())) {
		        throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
		    }
		    return (temp);
		}
	
	
	
	public void compileSourceCode(String fullFilePath, CodeResult result) throws IOException {
        /*
         * Javac does not guarantee .class file will be generated as
         * our UI code editor will not lint java syntax instantly,
         * so if syntax error or runtime exception happens, we should reap the exception
         * and save it to the CodeResult and return.
         * */
	    // using the Runtime exec method to run :

        Process compile = runtime.exec("javac " + fullFilePath);
        
        /* TODO set result????
         * 
         * 1/ No exception
         * 2/ Runtime Exception, such as file not found
         * 3/ ArrayOutOfBound
         * 4/ Syntax error
         * 5/ 
         */
        
	}
	
	public void runSourceCode(String fullFilePath, CodeResult result) {
		String binaryFile = "";
		if (fullFilePath.contains(".")) {
			binaryFile = fullFilePath.split(".")[0]+".class";
		}
		
		String line;
		try {
	        // Run binary file in full file path
			Process run = runtime.exec("java " + binaryFile);
	        PrintWriter printWriter = new PrintWriter("log.txt");
	        BufferedReader stdInput = new BufferedReader(new
	        InputStreamReader(run.getInputStream()));
	        BufferedReader stdError = new BufferedReader(new 
	        InputStreamReader(run.getErrorStream()));

	        // read the output from the command
	        System.out.println("Here is the standard output of the command:\n");
	        printWriter.println("Here is the standard output of the command:\n");
	        while ((line = stdInput.readLine()) != null) {
	                System.out.println(line);
	                printWriter.println(line);
	            }
	            
	            // read any errors from the attempted command
	            System.out.println("Here is the standard error of the command (if any):\n");
	            printWriter.println("Here is the standard error of the command (if any):\n");
	            while ((line = stdError.readLine()) != null) {
	                System.out.println(line);
	                printWriter.println(line);
	            }
	            printWriter.close();
	            System.exit(0);
	        }
	        catch (IOException e) {
	            System.out.println("IOException happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
	        catch (Exception e) {
	            System.out.println("Exception happened - here's what I know: ");
	            e.printStackTrace();
	            System.exit(-1);
	        }
	}
	public CodeResult executeCode(SourceCode sourceCode) throws Exception {
		String s = null;
	
		//Generate source code
		String fileFullPath = generateSourceFile(sourceCode);
		
		// Check source code file exists
		File f = new File(fileFullPath);
		if(!f.exists() || f.isDirectory()) { 
		    throw new Exception("souce file " + fileFullPath + "does not exists or is not a file");
		}
		
		CodeResult result = new CodeResult();
		// Compile source code 
		compileSourceCode(fileFullPath,result);
		
		/*
		 * Code result should have subclass about 
		 * class CompilingInfo {
		 *   boolean compileSuccess;  //
		 *   CompileFailType failType; //
		 *   String failMsg;  //
		 *   String exceptionName; //
		 * }
		 * 
		 *　see if we need more info
		 *
		 * Enum compileFailType {
		 *   SyntaxError, Exception
		 * }
		 * */ 
		if (true/* result 's compile property is failed*/) {
			return result;
		}
		
       
        return null;
	}

	
	// This is a temp function
    public static  String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}