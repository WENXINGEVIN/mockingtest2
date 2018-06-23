package com.webbertech.web.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RunCode {
	static CodeResult executeCode(SourceCode sourceCode)  {

		String s = null;
		
        try {

            // reconstruct source code to ".java" file
            File javaFile = new File(sourceCode.getTitle() + ".java");
            FileWriter fileWriter = new FileWriter(javaFile);
            fileWriter.write(sourceCode.getCode());
            fileWriter.close();

            // using the Runtime exec method to run :
            Runtime runtime = Runtime.getRuntime();
            Process compile = runtime.exec("javac " + javaFile.getName());
            Process run = runtime.exec("java " + sourceCode.getTitle());

            PrintWriter printWriter = new PrintWriter("log.txt");

            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(run.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(run.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            printWriter.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                printWriter.println(s);
            }
            
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            printWriter.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
                printWriter.println(s);
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
        
        return null;
        
	}

    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

	public static void main(String[] args) throws FileNotFoundException {
		// Test above code here.
        SourceCode sourceCode = null;
        try {
            String fileName = "E:\\mockingtest\\app\\HelloWorld.txt";
            String codeInString = readFileAsString(fileName);
            sourceCode = new SourceCode(Paths.get(fileName).getFileName().toString().replaceFirst("[.][^.]+$", ""), codeInString);
            executeCode(sourceCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}



