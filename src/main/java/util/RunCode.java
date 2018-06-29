package util;
import ch.ethz.ssh2.StreamGobbler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class RunCode {
	public static CodeResult executeCode(SourceCode sourceCode)  {
		String s = null;
		
        try {
            System.out.println("Java Home: " + System.getProperty("java.home") + "bin/java");
            System.out.println("User Home: " + System.getProperty("user.home"));
            System.out.println("User Directory: " + System.getProperty("user.dir"));

            Path userFolderPath = Paths.get(System.getProperty("user.dir") + "/src/main/java/user/" + getUserId());
            if (!Files.exists(userFolderPath)) {
                try {
                    Files.createDirectories(userFolderPath);
                } catch (IOException e) {
                    //fail to create directory
                    e.printStackTrace();
                }
            }

            ProcessBuilder builder = new ProcessBuilder();
            builder.directory(userFolderPath.toFile());

            // reconstruct source code to ".java" file
//            File javaFile = new File("/Users/VINCENTWEN/local_workspace/mockingtest2/src/main/java/user/" + sourceCode.getTitle() + ".java");
            File javaFile = new File(userFolderPath.toString() + "/" + sourceCode.getTitle() + ".java");
            FileWriter fileWriter = new FileWriter(javaFile);
            fileWriter.write(sourceCode.getCode());
            fileWriter.close();

            /*
             * Javac does not guarantee .class file will be generated as
             * our UI code editor will not lint java syntax instantly,
             * so if syntax error or runtime exception happens, we should reap the exception
             * and save it to the CodeResult and return.
             * */

            String compileCommand = "javac " + javaFile.getName();
            String runCommand = "java " + sourceCode.getTitle();
            String[] commandArray = {"/bin/sh", "-c", compileCommand + ";" + runCommand};
            File userDir = new File("/Users/VINCENTWEN/local_workspace/mockingtest2/src/main/java/user");

            builder.command("/bin/sh", "-c", compileCommand + ";" + runCommand);
            Process p = builder.start();
            // If the filename.class already exists, we need to delete it.
            /*
             * String tempdir = system_temp_dir();
             * String userTempDir = tmepdir<>JavaUUID<>"Uid"; //path
             * everything is created under this dir.
             * */

            CodeResult codeResult = new CodeResult();

            PrintWriter printWriter = new PrintWriter("log.txt");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            printWriter.println("printWrite: Here is the standard output of the command:\n");
            StringBuilder output = new StringBuilder();
            while ((s = stdInput.readLine()) != null) {
                printWriter.println(s);
                output.append(s);
                output.append(System.getProperty("line.separator"));
            }
            codeResult.setStdout(output.toString());
            System.out.println(codeResult.getStdout());

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            printWriter.println("printWrite: Here is the standard error of the command (if any):\n");
            StringBuilder error = new StringBuilder();
            while ((s = stdError.readLine()) != null) {
                printWriter.println(s);
                error.append(s);
                error.append(System.getProperty("line.separator"));
            }
            codeResult.setException(error.toString());
            System.out.println(codeResult.getException());

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


	public static String getUserId() {
	    return UUID.randomUUID().toString();
    }


    public static String readFileAsString(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }


    public static void main(String[] args) throws FileNotFoundException {
        // Test above code here.
        try {
            String fileName = "/Users/VINCENTWEN/local_workspace/mockingtest2/src/main/java/user/HelloWorld2.txt";
            String codeInString = readFileAsString(fileName);
            SourceCode sourceCode = new SourceCode(Paths.get(fileName).getFileName().toString().replaceFirst("[.][^.]+$", ""), codeInString);
            executeCode(sourceCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}