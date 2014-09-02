package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    /**
     * This is the main method of the program.
     * @param args
     */
    public static void main(String[] args) {
        TranslateEngine translateEngine = new TranslateEngine(Constants.FILE_PATH);
        Scanner scanner = new Scanner(System.in);
        System.out.print(Constants.START_MESSAGE);
        String nextLine = scanner.nextLine();

        String choice = "";
        if (nextLine.toLowerCase().equals(Constants.DECRYPT)) {
            choice = Constants.DECRYPT;
        } else if (nextLine.toLowerCase().equals(Constants.ENCRYPT)) {
            choice = Constants.ENCRYPT;
        } else {
            System.out.println(Constants.WRONG_COMMAND_MESSAGE);
        }

        if (!choice.isEmpty()) {
            System.out.printf(Constants.CHOICE_MESSAGE, choice);
            System.out.println();
            System.out.printf(Constants.ENTER_STRING_MESSAGE, choice);
            String message = scanner.nextLine();

            if (choice.equals(Constants.DECRYPT)) {
                OutputMessage(String.format(Constants.FINAL_MESSAGE, choice, translateEngine.DecryptString(message)));
            } else if (choice.equals(Constants.ENCRYPT)) {
                OutputMessage(String.format(Constants.FINAL_MESSAGE, choice, translateEngine.EncryptString(message)));
            }
        }
    }

    private static void OutputMessage(String message){
        if(Constants.PRINT_TO_FILE){
            try {
                File file = new File("Output.txt");
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(message);
                output.close();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }

    }
}
