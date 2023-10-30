import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        // Change the current directory to the user's home directory
        String userHome = System.getProperty("user.home");
        System.setProperty("user.dir", userHome);
        currentDirectory = new File(userHome);
        System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
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
        // Change the current directory to the given path
        System.setProperty("user.dir", path);
        currentDirectory = new File(System.getProperty("user.dir"));
        System.out.println(currentDirectory.getAbsolutePath());
    }
    void touchRelative(String input) {
        String []tempArg = parser.getArgs(input);
        String filePath = tempArg[0];
        Path path = FileSystems.getDefault().getPath(filePath);
        File file = path.toFile();
    }
    void touchAbs() {

    }
}
