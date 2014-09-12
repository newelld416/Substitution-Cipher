package com.company;

import java.util.Scanner;

/**
 * Created by Daniel Newell on 9/1/2014.
 */
public class Main {

    /**
     * This is the main method of the program.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ValidInput = false;
        String choice = "";
        String continueMessage = "";

        //Doing this in a do-while loop allows the user to encrypt or decrypt multiple strings
        do {
            System.out.println(Constants.SUMMARY_MESSAGE);
            System.out.print(Constants.KEYWORD_MESSAGE);

            //This gets the key word entered by the user and creates the translation engine used throughout the program
            TranslateEngine translateEngine = new TranslateEngine(scanner.nextLine());

            //Doing this in a do-while loop allows the user to continue after a typo until an expected input is received
            do {
                System.out.print(Constants.START_MESSAGE);

                //This allows the user to choose whether they want to encrypt or decrypt a string
                String nextLine = scanner.nextLine();
                if (nextLine.toLowerCase().equals(Constants.DECRYPT)) {
                    choice = TranslateEngine.EncryptionType.decrypt.toString();
                    ValidInput = true;
                } else if (nextLine.toLowerCase().equals(Constants.ENCRYPT)) {
                    choice = TranslateEngine.EncryptionType.encrypt.toString();
                    ValidInput = true;
                }
            } while (!ValidInput);

            if (!choice.isEmpty()) {
                System.out.printf(Constants.CHOICE_MESSAGE, choice);
                System.out.println();
                System.out.printf(Constants.ENTER_STRING_MESSAGE, choice);

                //This gets the message that the user will encrypt or decrypt
                String message = scanner.nextLine();

                //Get s the encryption type specified by the user
                TranslateEngine.EncryptionType encryptionType =
                        choice.equals(Constants.DECRYPT) ?
                                TranslateEngine.EncryptionType.decrypt :
                                TranslateEngine.EncryptionType.encrypt;

                //This prints out the ecrypted or decrypted message
                translateEngine.OutputMessage(
                        String.format(Constants.FINAL_MESSAGE,
                                choice,
                                translateEngine.EncryptDecryptString(message, encryptionType)),
                        Constants.OUTPUT_FILENAME);
            }

            System.out.print(Constants.CONTINUE_MESSAGE);

            //This gives the user the option to continue encrypting or decrypting
            continueMessage = scanner.nextLine().toLowerCase();
            System.out.println();
        } while (continueMessage.equals(Constants.YES));
    }


}
