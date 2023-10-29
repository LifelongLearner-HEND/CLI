import java.util.Arrays;
import java.util.List;
public class Parser {
    private String commandName;
    private String[] args;

    // check if the command is valid
    public boolean parse(String command) {

        return false;
    }
    // parse the input to --> command + list of arguments
    public String[] getArgs(String input) {
        // remove leading and trailing spaces
        input = input.trim();
        // split the input by spaces
        args = input.split("\\s+");
        commandName = args[0];
        return args;
    }
    // get the command name if valid
    public String getCommandName() {
        return commandName;
    }

}
