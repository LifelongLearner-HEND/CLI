# Command Line Interprter Simulation

## Overview
This project implements a simple Command Line Interpreter (CLI) for your operating system. The CLI allows users to enter commands through the keyboard, parses the input, and executes the indicated command. The program will continue accepting commands until the user enters "exit" to terminate the CLI.

## Project Structure
### Parser class
Attributes:
• `String commandName`: Represents the name of the command.
• `String[] args`: Represents the arguments associated with the command.

Methods:
• `boolean parse(String input)`: Parses the input string and divides it into `commandName` and `args`.
• `String getCommandName()`: Returns the parsed command name.
• `String[] getArgs()`: Returns the parsed command arguments.

### Terminal Class
Attributes:
• `Parser parser`: An instance of the `Parser class` to handle command parsing.

Methods:
• `public String pwd()`: Implements the "pwd" command(for example) to display the current working directory.
• `public void cd(String[] args)`: Implements the "cd" command to change the current directory.
• `public void chooseCommandAction()`: Chooses the suitable command method to be called based on the parsed input.

Main Method:
`public static void main(String[] args)`: The main entry point of the program. Instantiates the Terminal class and manages the CLI loop.

## Available commands 

| Command Name | Functionality |
| -------- | -------- |
| `echo` | Takes 1 argument and prints it. |
| `pwd` | Takes no arguments and prints the current path. |
| `cd` |  `cd` takes no arguments and changes the current path to the path of your home directory. `cd` takes 1 argument which is “..” (e.g. cd ..) and changes the current directory to the previous directory. `cd` takes 1 argument which is either the full path or the relative (short) path and changes the current path to that path.|
