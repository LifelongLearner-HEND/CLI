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

}
