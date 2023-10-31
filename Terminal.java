import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Terminal {
//    public static void main(String[] args) {
//        System.out.println("Hello world!");
//    }
    Scanner scanner = new Scanner(System.in);
    Parser parser = new Parser();
    void echo(String[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    void cat(String[] arr){
        if (arr.length == 1){
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
        }
        else if (arr.length == 2){
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
        }
        else
            System.out.println("An error occurred.");
    }
    void rm(String[] arr){
        if (arr.length == 1){
            File file = new File(arr[0]);
            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            } else {
                System.out.println("An error occurred.");
            }
        }
        else
            System.out.println("An error occurred.");
    }
    void wc (String[] arr){
        if (arr.length == 1){
            int lines = 0,words = 0,characters = 0;
            try {
                File file = new File(arr[0]);
                Scanner scl = new Scanner(file);
                while (scl.hasNextLine()) {
                    scl.nextLine();
                    lines++;
                }
                Scanner scw = new Scanner(file);
                while (scw.hasNext()){
                    scw.next();
                    words++;
                }
                Scanner scc = new Scanner(file);
                while (scc.hasNext()){
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

}
