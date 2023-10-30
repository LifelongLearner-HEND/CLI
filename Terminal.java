import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Terminal {
    Parser parser = new Parser();
    File currentDirectory = new File(System.getProperty("user.dir"));
    void ls() {
        File[] files = currentDirectory.listFiles();
        // sort and print if the directory is not empty
        if(files != null) {
            Arrays.sort(files);
            for(File file : files) {
                System.out.println(file.getName());
            }
        }
        else {
            System.out.println("Directory is Empty!");
        }
    }
    void lsReversed() {
        File[] files = currentDirectory.listFiles();
        // sort and print if the directory is not empty
        if(files != null) {
            // sort the files in reverse order
            Arrays.sort(files);
            Collections.reverse(Arrays.asList(files));
            for(File file : files) {
                System.out.println(file.getName());
            }
        }
        else {
            System.out.println("Directory is Empty!");
        }
    }
    void cdToHome() {
        // check if the user is in the home directory
        if(currentDirectory.getAbsolutePath().equals(System.getProperty("user.home"))) {
            System.out.println("You are already in the home directory!");
        }
        else {
            // Change the current directory to the user's home directory
            String userHome = System.getProperty("user.home");
            System.setProperty("user.dir", userHome);
            currentDirectory = new File(userHome);
            System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
        }
    }
    void cdToParent() {
        // Change the current directory to the parent directory
        File parentDirectory = currentDirectory.getParentFile();
        // check that we don't stand at the root directory
        if(parentDirectory != null) {
            System.setProperty("user.dir", parentDirectory.getAbsolutePath());
            currentDirectory = parentDirectory;
        }
        else {
            System.out.println("You are already in the root directory!");
        }
        System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
    }
    void cdToPath(String path) {
        // check if the path exists
        File file = new File(path);
        if(!file.exists()) {
            System.out.println("Path doesn't exits!");
        }
        else {
            // Change the current directory to the given path
            System.setProperty("user.dir", path);
            currentDirectory = new File(System.getProperty("user.dir"));
            System.out.println(currentDirectory.getAbsolutePath());
        }
    }
    void touchAbs(String input) {
        // create a file in the given absolute path
        String []tempArg = parser.getArgs(input);
        String filePath = tempArg[0];
        // parse the path into the file name and the absolute path
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        System.out.println(fileName);
        String path = filePath.substring(0, filePath.lastIndexOf("\\"));
        System.out.println(path);
        // check if the path exists
        File file = new File(path);
        if(!file.exists()) {
            System.out.println("Path doesn't exits!");
        }
        else {
            // add the file to the given path
            file = new File(path, fileName);
            try {
                // check if the file is already created
                boolean isCreated = file.createNewFile();
                if(isCreated) {
                    System.out.println("File created successfully!");
                }
                else {
                    System.out.println("File already exists!");
                }
            } catch (IOException e) {
                System.out.println("Error occurred!");
            }
        }
    }
    void touchRelative(String input) {
        String []tempArg = parser.getArgs(input);
        String relativePath = tempArg[0];
        File file = new File(relativePath);
        File parentDirectory = file.getParentFile();
        // check if the directory exists
        if(!parentDirectory.exists()) {
            boolean created = parentDirectory.mkdirs();
            if(created) {
                System.out.println("Directory created successfully!");
            }
            else {
                System.out.println("Error occurred!");
            }
        }
        try {
            // check if the file is already created
            boolean isCreated = file.createNewFile();
            if(isCreated) {
                System.out.println("File created successfully!");
            }
            else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error occurred!");
        }
    }
    void touchFileName(String input) {
        String []tempArg = parser.getArgs(input);
        String fileName = tempArg[0];
        // create the file in the current directory
        File file = new File(currentDirectory, fileName);
        try {
            // check if the file is already created
            boolean isCreated = file.createNewFile();
            if(isCreated) {
                System.out.println("File created successfully!");
            }
            else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("Error occurred!");
        }
    }
}
