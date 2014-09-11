package com.company;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Daniel Newell on 9/1/2014.
 */
public class TranslateEngine {

    private char[][] cipher = new char[54][2];
    private String KeyWord = "";
    public enum EncryptionType { encrypt, decrypt }

    /**
     * This is the constructor method for this class.
     */
    public TranslateEngine(String keyWord) {
        KeyWord = RemoveDuplicateLetters(keyWord);
        PopulateCipherArray();
    }

    /**
     * This is a public method used to encrypt or decrypt a string.
     * @param message
     * @param encryptionType
     * @return
     */
    public String EncryptDecryptString(String message, EncryptionType encryptionType) {
        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < 54; j++) {
                // If statement is true than character was found in cipher
                if (cipher[j][encryptionType.equals(EncryptionType.encrypt) ? 0 : 1] == message.charAt(i)) {
                    returnString.append(cipher[j][encryptionType.equals(EncryptionType.encrypt) ? 1 : 0]);
                    break;
                }

                // If statement is true than character was not found in cipher
                if (j == 53) { returnString.append(message.charAt(i)); }
            }
        }

        return returnString.toString();
    }

    /**
     * This is a private method used to populate the cipherArray used in encryption and decryption.
     */
    private void PopulateCipherArray(){
        String PlainTextForward = Constants.PlainTextForward;
        for (int i = 0; i < PlainTextForward.length(); i++){
            cipher[i][0] = PlainTextForward.charAt(i);
        }

        String EncryptionKey = RemoveDuplicateLetters(KeyWord + Constants.PlainTextBackward);
        for (int i = 0; i < EncryptionKey.length(); i++){
            cipher[i][1] = EncryptionKey.charAt(i);
        }

        OutputMessage(String.format("%s\n%s", PlainTextForward, EncryptionKey), Constants.ENCRYPTION_FILENAME);
    }

    /**
     * This method removes the duplicate letters in the key passed in.
     * @param key
     * @return
     */
    private String RemoveDuplicateLetters(String key){
        char[] chars = key.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }

        return sb.toString();
    }

    /**
     * This method decides whether you should be outputting to the console or the a file, and outputs the given message.
     * @param message
     */
    public void OutputMessage(String message, String fileName){
        if(Constants.PRINT_TO_FILE){
            try {
                File file = new File(fileName);
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(message);
                output.close();
                System.out.printf(Constants.OUTPUT_MESSAGE, fileName);
                System.out.println();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }

    }

}