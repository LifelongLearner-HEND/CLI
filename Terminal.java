import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.io.File;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

class Terminal {
    File currentDirectory = new File(System.getProperty("user.dir"));

    // Command 1: lists the contents of the current directory
    // sorted in alphabetical order or reversed
    void ls(String command, String[] input) {
        File[] files = currentDirectory.listFiles();
        // check if the directory is empty
        if (files == null) {
            System.out.println("Directory is Empty!");
            return;
        }
        // sort the files in alphabetical order
        if (command.equals("ls") && input.length == 0) {
            Arrays.sort(files);
            for (File file : files) {
                System.out.println(file.getName());
            }
        }
        // sort the files in reversed order
        else if (command.equals("ls -r") && input.length == 0) {
            Arrays.sort(files);
            Collections.reverse(Arrays.asList(files));
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Invalid Command Arguments!");
        }
    }

    // Command 2: changes the current directory
    void cd(String[] args) {
        // cd to home directory
        if (args.length == 0) {
            // check if the user is in the home directory
            if (currentDirectory.getAbsolutePath().equals(System.getProperty("user.home"))) {
                System.out.println("You are already in the home directory!");
            } else {
                // Change the current directory to the user's home directory
                String userHome = System.getProperty("user.home");
                System.setProperty("user.dir", userHome);
                currentDirectory = new File(userHome);
                System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
            }
        }

        // cd to parent directory
        else if (args[0].equals("..") && args.length == 1) {
            // Change the current directory to the parent directory
            File parentDirectory = currentDirectory.getParentFile();
            // check that we don't stand at the root directory
            if (parentDirectory != null) {
                System.setProperty("user.dir", parentDirectory.getAbsolutePath());
                currentDirectory = parentDirectory;
            } else {
                System.out.println("You are already in the root directory!");
            }
            System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
        }

        // cd to relative or full path
        else if (args.length == 1) {
            // check if the path exists
            File file = new File(args[0]);
            if (!file.exists()) {
                System.out.println("Path doesn't exits!");
            } else {
                // Change the current directory to the given path
                System.setProperty("user.dir", args[0]);
                currentDirectory = new File(System.getProperty("user.dir"));
                System.out.println("Current Directory: " + currentDirectory.getAbsolutePath());
            }
        }
        // another invalid command argument
        else {
            System.out.println("Invalid Command Arguments!");
        }
    }

    // Command 3: creates a new file in a given path
    void touch(String[] input) {
        // touch create absolute path
        if (input.length == 1 && input[0].contains(":")) {
            // parse the path into the file name and the absolute path
            String fileName = input[0].substring(input[0].lastIndexOf("\\") + 1);
            String filePath = input[0].substring(0, input[0].lastIndexOf("\\"));
            // check if the path exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Path doesn't exits!");
            } else {
                // add the file to the given path
                file = new File(filePath, fileName);
                try {
                    // check if the file is already created
                    boolean isCreated = file.createNewFile();
                    if (isCreated) {
                        System.out.println("File created successfully!");
                    } else {
                        System.out.println("File already exists!");
                    }
                } catch (IOException e) {
                    System.out.println("Error occurred!");
                }
            }
        }
        // touch create relative path
        else if (input.length == 1 && input[0].contains("\\")) {
            String relativePath = input[0];
            File file = new File(relativePath);
            File parentDirectory = file.getParentFile();
            // check if the directory exists
            if (!parentDirectory.exists()) {
                boolean created = parentDirectory.mkdirs();
                if (created) {
                    System.out.println("Directory created successfully!");
                } else {
                    System.out.println("Error occurred!");
                }
            }
            try {
                // check if the file is already created
                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File created successfully!");
                } else {
                    System.out.println("File already exists!");
                }
            } catch (IOException e) {
                System.out.println("Error occurred!");
            }
        }

        // touch create file in the current directory
        else if (input.length == 1 && !input[0].contains("\\")) {
            String fileName = input[0];
            // create the file in the current directory
            File file = new File(currentDirectory, fileName);
            try {
                // check if the file is already created
                boolean isCreated = file.createNewFile();
                if (isCreated) {
                    System.out.println("File created successfully!");
                } else {
                    System.out.println("File already exists!");
                }
            } catch (IOException e) {
                System.out.println("Error occurred!");
            }
        }
        // Handle invalid command arguments
        else {
            System.out.println("Invalid Command Arguments!");
        }
    }

    // Command 4: list the history of the commands
    void history(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((i + 1) + "  " + arr.get(i));
        }
    }

    // Command 5: print the current working directory
    public static String pwd(String[] args) {
        if (args.length != 0) {
            return "Invalid arguments this method does not take an argument";
        }
        return System.getProperty("user.dir");
    }

    // Command 6: Method mkdir that takes a list or arguments and makes a directory for each
    public static void mkdir(String[] args) {

        for (String arg : args) {
            Path directory;
            directory = Paths.get(arg);
            //Path.get() method returns a Path object that represents the file or directory specified by the input String.
            //automatically handles platform-specific path syntax (such as using backslashes on Windows and forward slashes on Unix-based systems)
            // check if directory is not empty
            if (directory.toString().equals("")) {
                System.out.println("Failed to make Directory missing name ");
                return;
            }
            //check if directory already exists
            if (Files.exists(directory)) {
                System.out.println("Directory already exists: " + directory);
                continue;

            }
            //in this case
            // If the argument is a path, create the directory at the specified path.
            // If it's just a directory name, create it in the current directory.
            // tries to create the directory
            try {
                Files.createDirectories(directory);
                System.out.println("Directory created: " + directory);

            } catch (IOException e) {
                System.err.println("Failed to create directory: " + directory);
                e.printStackTrace();
            }
        }
    }

    //Command 7: Copies file to another file
    public static void cp(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("invalid number of arguments ");
        }
        Path sourcePath = Paths.get(args[0]);
        Path destinationPath = Paths.get(args[1]);

        //check if directory already exists
        if (Files.exists(destinationPath)) {
            System.out.println("File already exists: " + destinationPath);
            return;
        }

        //Checks is source file exists
        //we don't have to check on the destination file,because if it doesn't exist it will be created in the file directory
        if (Files.exists(sourcePath)) {
            try {
                //Files.copy method expects Path objects as arguments, not Strings,so we need to convert it to path type;
                Files.copy(sourcePath, destinationPath, REPLACE_EXISTING);
                System.out.println("File copied successfully.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Source file does not exist" + sourcePath);
        }
    }

    // Command 8: Moves file to another file
    public static void cp_r(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("invalid number of arguments ");
        }

        // Convert source and destination paths to Path objects for better manipulation
        Path sourcePath = Paths.get(args[0]);
        Path destinationPath = Paths.get(args[1]);

        //check if directory already exists
        if (Files.exists(destinationPath)) {
            System.out.println("Directory already exists: " + destinationPath);
            return;
        }

        // Checking if the source path exists
        // Don't have to check on the target(to be copied),because if it doesn't exist it will be created in the same directory
        if (Files.exists(sourcePath)) {
            //  Files.walk() to traverses the directory tree starting from the sourcePath
            try {
                Files.walk(sourcePath)
                        .forEach(file -> {
                            // relativize() :finds the path from the source directory to a specific file. It tells you how to get from the source directory to that file in the simplest way.
                            // resolve () combines the destination directory with this simple path, showing you where the file should be placed in the destination directory.

                            Path destinationFile = destinationPath.resolve(sourcePath.relativize(file));

                            try {
                                // Copy the current file to the calculated destination path
                                Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                // If an error occurs during the copy operation, throw a RuntimeException
                                System.out.println("Error copying file: " + e.getMessage());
                            }
                        });
                System.out.println("Directory Copied successfully");
            } catch (IOException e) {
                System.out.println("Error traversing the directory tree" + e.getMessage());
            }
        } else {
            // If the source directory does not exist, throw an IOException
            throw new IOException("Source directory does not exist: " + sourcePath);
        }
    }

    // Command 9: takes one argument and prints it
    void echo(String[] arr) {
        if (arr.length == 1) {
            System.out.println(arr[0]);
        } else {
            System.out.println("Invalid Command Arguments!");
        }
    }

    void cat(String[] arr) {
        if (arr.length == 1) {
            try {
                File myObj = new File(arr[0]);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else if (arr.length == 2) {
            try {
                File file1 = new File(arr[0]);
                Scanner myReader = new Scanner(file1);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                File file2 = new File(arr[1]);
                myReader = new Scanner(file2);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else
            System.out.println("An error occurred.");
    }

    // Command 10: removes file
    void rm(String[] arr) {
        if (arr.length == 1) {
            File file = new File(arr[0]);
            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            } else {
                System.out.println("An error occurred.");
            }
        } else
            System.out.println("An error occurred.");
    }

    // Command 13
    void wc(String[] arr) {
        if (arr.length == 1) {
            int lines = 0, words = 0, characters = 0;
            try {
                File file = new File(arr[0]);
                Scanner scl = new Scanner(file);
                while (scl.hasNextLine()) {
                    scl.nextLine();
                    lines++;
                }
                Scanner scw = new Scanner(file);
                while (scw.hasNext()) {
                    scw.next();
                    words++;
                }
                Scanner scc = new Scanner(file);
                while (scc.hasNext()) {
                    String temp = scc.nextLine();
                    characters += temp.length();
                }
                scl.close();
                scw.close();
                scc.close();
                System.out.print(lines);
                System.out.print(" ");
                System.out.print(words);
                System.out.print(" ");
                System.out.print(characters);
                System.out.print(" ");
                System.out.println(arr[0]);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    // Program Interface
    void chooseCommandAction() throws IOException {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        Parser parserObject = new Parser();
        ArrayList<String> commandHistory = new ArrayList<>();
        while (!input.equals("exit")) {
            parserObject.getArgs(input);
            if (parserObject.parse(parserObject.getCommandName())) {
                commandHistory.add(input);
                // echo command
                if (parserObject.getCommandName().equals("echo"))
                    echo(parserObject.getArgs(input));
                    // pwd command
                else if (parserObject.getCommandName().equals("pwd")) {
                    System.out.println(pwd(parserObject.getArgs(input)));
                }
                // cd command
                else if (parserObject.getCommandName().equals("cd")) {
                    cd(parserObject.getArgs(input));
                }
                // ls command
                else if (parserObject.getCommandName().equals("ls")) {
                    ls(parserObject.getCommandName(), parserObject.getArgs(input));
                }
                // ls -r command
                else if (parserObject.getCommandName().equals("ls -r")) {
                    ls(parserObject.getCommandName(), parserObject.getArgs(input));
                }
                // touch command
                else if (parserObject.getCommandName().equals("touch")) {
                    touch(parserObject.getArgs(input));
                }
                // history command
                else if (parserObject.getCommandName().equals("history")) {
                    history(commandHistory);
                }
                // mkdir command
                else if (parserObject.getCommandName().equals("mkdir")) {
                    mkdir(parserObject.getArgs(input));
                }
                // cat command
                else if (parserObject.getCommandName().equals("cat")) {
                    cat(parserObject.getArgs(input));
                }
                // wc command
                else if (parserObject.getCommandName().equals("wc")) {
                    wc(parserObject.getArgs(input));
                }
                // cp command
                else if ((parserObject.getCommandName().equals("cp"))) {
                    cp(parserObject.getArgs(input));
                }
                //cp -r command
                else if ((parserObject.getCommandName().equals("cp -r"))) {
                    cp_r(parserObject.getArgs(input));
                }
                // rm command
                else if (parserObject.getCommandName().equals("rm")) {
                    rm(parserObject.getArgs(input));
                }
            } else {
                System.out.println("Invalid Command!");
            }
            input = scanner.nextLine();
        }
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        Terminal terminal = new Terminal();
        terminal.chooseCommandAction();
    }
}
