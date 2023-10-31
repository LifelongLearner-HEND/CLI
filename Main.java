import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.io.File;  // Import the File class


public class Main {
    public static void main(String[] args) {

        Parser parser = new Parser();
        Terminal terminal = new Terminal();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] arr = parser.getArgs(input);

        String command = parser.getCommandName();
        if (parser.parse(command)){
            if (command.equals("echo"))
                terminal.echo(arr);
            else if (command.equals("cat"))
                terminal.cat(arr);
            else if (command.equals("rm"))
                terminal.rm(arr);
            else if (command.equals("wc"))
                terminal.wc((arr));
        }

    }



    //System.out.println(command);
        //System.out.println(parser.parse(command));


}