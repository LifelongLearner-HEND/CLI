import java.util.Arrays;
import java.util.List;
public class Parser {
    private String commandName;
    private String[] args;

    // check if the command is valid
    public boolean parse(String command) {
        if (commandName.equals("echo"))
            return true;
        else if (commandName.equals("cat"))
            return true;
        return false;
    }
    // parse the input to --> command + list of arguments
    public String[] getArgs(String input) {
        // remove leading and trailing spaces
        input = input.trim();
        // split the input by spaces
        args = input.split("\\s+");
        commandName = args[0];

        // handling "cp -r" and "ls -r" command
        if(commandName.equals("cp") || commandName.equals("ls")) {
            if(args[1].equals("-r")) {
                commandName += " -r";
                // remove the "-r" and commandName from the list
                args = Arrays.copyOfRange(args, 2, args.length);
            }
        }
        else {
            // remove the command name from the list
            commandName = args[0];
            args = Arrays.copyOfRange(args, 1, args.length);
        }
        return args;
    }
    // get the command name if valid
    public String getCommandName() {
        return commandName;
    }

}
