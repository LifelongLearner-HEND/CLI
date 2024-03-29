# Command Line Interprter Simulation

## Overview
This project implements a simple Command Line Interpreter (CLI) for your operating system. The CLI allows users to enter commands through the keyboard, parses the input, and executes the indicated command. The program will continue accepting commands until the user enters "exit" to terminate the CLI.

## Project Structure
### Parser class
Attributes: <br>
• `String commandName`: Represents the name of the command. <br>
• `String[] args`: Represents the arguments associated with the command. 

Methods: <br>
• `boolean parse(String input)`: Parses the input string and divides it into `commandName` and `args`. <br>
• `String getCommandName()`: Returns the parsed command name. <br>
• `String[] getArgs()`: Returns the parsed command arguments.

### Terminal Class
Attributes: <br>
• `Parser parser`: An instance of the `Parser class` to handle command parsing.

Methods: <br>
• `public String pwd()`: Implements the "pwd" command(for example) to display the current working directory. <br>

• `public void cd(String[] args)`: Implements the "cd" command to change the current directory. <br>

• `public void chooseCommandAction()`: Chooses the suitable command method to be called based on the parsed input.

Main Method: <br>
`public static void main(String[] args)`: The main entry point of the program. Instantiates the Terminal class and manages the CLI loop.

## Available commands 

| Command Name | Functionality |
| -------- | -------- |
| `echo` | Takes 1 argument and prints it. |
| `pwd` | Takes no arguments and prints the current path. |
| `cd` |  •`cd` takes no arguments and changes the current path to the path of your home directory. <br> •`cd` takes 1 argument which is “..” (e.g. cd ..) and changes the current directory to the previous directory. <br> •`cd` takes 1 argument which is either the full path or the relative (short) path and changes the current path to that path.|
| `ls` | Takes no arguments and lists the contents of the current directory sorted alphabetically. |
| `ls -r` | Takes no arguments and lists the contents of the current directory in reverse order. |
| `mkdir` | Takes 1 or more arguments and creates a directory for each argument. Each argument can be: <br> •Directory name (in this case the new directory is created in the current directory) <br> •Path (full/short) that ends with a directory name (in this case the new directory is created in the given path) |
| `touch` | Takes 1 argument which is either the full path or the relative (short) path that ends with a file name and creates this file. |
| `cp` | Takes 2 arguments, both are files and copies the first onto the second. |
| `cp -r` | Takes 2 arguments, both are directories (empty or not) and copies the first directory (with all its content) into the second one. |
| `rm` | Takes 1 argument which is a file name that exists in the current directory and removes this file. |
| `cat` | Takes 1 argument and prints the file’s content or takes 2 arguments and concatenates the content of the 2 files and prints it. |
| `wc` | Wc stands for “word count,” and as the name suggests, it is mainly used for counting purpose. By default, it displays four-columnar <br> First column shows number of lines present in a file specified, second column shows number of words present in the file, third column shows number of characters present in file and fourth column itself is the file name which are given as arguments <br> Sample Input: <br> `wc file.txt` <br> Sample Output: <br> `9 79 483 file.txt` <br> Explanation: <br> # 9 lines, 79 word, 483 character with spaces, file name |
| `history` | Takes no parameters and displays an enumerated list with the commands you’ve used in the past. <br> Sample Input: <br> `history` <br> Sample Output <br> `1 ls` <br> `2 mkdir tutorial` <br> `3 history` |
| `exit` | Terminates the program. |

## Dependencies
This CLI project is implemented in Java and does not require any external dependencies beyond the standard Java libraries.

## How to use CLI?
To use the CLI, follow these steps:

1- Run the program. <br> 2- The CLI will start and display a prompt, awaiting your input. <br> 3- Enter commands and press Enter to execute them. <br> 4- The CLI will respond with the output of the executed command. <br> 5- Continue entering commands until you want to exit, then enter "exit."

## Contributing
Feel free to contribute to this project by forking and creating pull requests. Report any issues or suggest improvements through the GitHub issue tracker.

## Contributers
This project is developed by the following team members:
- [Hend Ahmad](https://github.com/LifelongLearner-HEND)
- [Farah Mohammad](https://github.com/farah2543)
- [Marawan Ahmad](https://github.com/maro312)
