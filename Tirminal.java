import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Tirminal {

    //Method pwd that takes no argument and prints the current directory name for user
    public static String pwd (){

        return System.getProperty("user.dir");

    }

    //Method mkdir that takes a list or arguments and makes a directory for each
    public static void mkdir (String [] args){

        for (String arg :args) {
            Path directory;

            directory = Paths.get(arg);
            //Path.get() method returns a Path object that represents the file or directory specified by the input String.
            //automatically handles platform-specific path syntax (such as using backslashes on Windows and forward slashes on Unix-based systems)

            //in this case

            // If the argument is a path, create the directory at the specified path.
            // If it's just a directory name, create it in the current directory.

            // tries to create the directory
            try {
                Files.createDirectories(directory);
                System.out.println("Directory created: " + directory);

            }
            catch ( IOException e ) {
                System.err.println("Failed to create directory: " + directory);
                e.printStackTrace();
            }

        }

    }

    //Copies file to another file
    public static void cp(String source , String destination) throws IOException {

        Path sourcePath = Paths.get( source );
        Path destinationPath = Paths.get( destination );


        //Checks is source file exists
        //we don't have to check on the destination file,because if it doesn't exist it will be created in the file directory

        if (Files.exists( sourcePath )){
            try {
                //Files.copy method expects Path objects as arguments, not Strings,so we need to convert it to path type;
                Files.copy( sourcePath , destinationPath ,REPLACE_EXISTING);
                System.out.println("File copied successfully.");
            }
            catch ( IOException e ) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        else {
            throw new IOException("Source file does not exist: " + sourcePath );
        }


    }

    public static void cp_r(String source, String destination) throws IOException {
        // Convert source and destination paths to Path objects for better manipulation
        Path sourcePath = Paths.get(source);
        Path destinationPath = Paths.get(destination);

        // Checking if the source path exists
        // Don't have to check on the target(to be copied),because if it doesn't exist it will be created in the same directory

        if (Files.exists(sourcePath)) {
            //  Files.walk() to traverses the directory tree starting from the sourcePath
            try {
                Files.walk( sourcePath )
                        .forEach( file -> {
                            // relativize() :finds the path from the source directory to a specific file. It tells you how to get from the source directory to that file in the simplest way.
                            // resolve () combines the destination directory with this simple path, showing you where the file should be placed in the destination directory.

                            Path destinationFile = destinationPath.resolve( sourcePath.relativize( file ) );

                            try {
                                // Copy the current file to the calculated destination path
                                Files.copy( file , destinationFile , StandardCopyOption.REPLACE_EXISTING );
                            } catch ( IOException e ) {
                                // If an error occurs during the copy operation, throw a RuntimeException
                                System.out.println("Error copying file: " + e.getMessage());
                            }
                        } );
                System.out.println("Directory Copied successfully");
            }
            catch ( IOException e ){
                System.out.println("Error traversing the directory tree" + e.getMessage());
            }
        } else {
            // If the source directory does not exist, throw an IOException
            throw new IOException("Source directory does not exist: " + sourcePath);
        }

    }
}
