package com.company;

import java.util.Scanner;

public class Main {

    private static final String DECRYPT = "decrypt";
    private static final String ENCRYPT = "encrypt";
    private static final String FILE_PATH = "C:\\School Workspaces\\Substitution-Cipher\\src\\com\\company\\EncryptionKey.txt";

    /**
     * This is the main method of the program.
     * @param args
     */
    public static void main(String[] args) {
        TranslateEngine translateEngine = new TranslateEngine(FILE_PATH);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter decrypt or encrypt: ");
        String cipher = scanner.nextLine();
        if (cipher.toLowerCase().equals(DECRYPT)) {
            System.out.println("You chose to decrypt a message");
            System.out.print("Please enter the string you would like to decrypt: ");
            String message = scanner.nextLine();
            System.out.println("Your decrypted string is " + translateEngine.DecryptString(message));
        } else if (cipher.toLowerCase().equals(ENCRYPT)) {
            System.out.println("You chose to encrypt a message");
            System.out.print("Please enter the string you would like to encrypt: ");
            String message = scanner.nextLine();
            System.out.println("Your decrypted string is " + translateEngine.EncryptString(message));
        } else {
            System.out.println("Sorry I do not understand that command, I give up on you.");
        }

    }


}
