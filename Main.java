import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        parser.parse(input);
        System.out.println(Arrays.toString(parser.getArgs(input)));
        System.out.println(parser.getCommandName());
    }
}