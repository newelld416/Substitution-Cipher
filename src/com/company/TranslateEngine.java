package com.company;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Daniel Newell on 9/1/2014.
 */
public class TranslateEngine {

    private char[][] cipher = new char[56][2];
    private String KeyWord = "";

    /**
     * This is the constructor method for this class.
     */
    public TranslateEngine(String keyWord) {
        KeyWord = RemoveDuplicateLetters(keyWord.toUpperCase());
        PopulateCipherArray();
    }

    /**
     * This is a public method used to encrypt a plain string.
     * @param decryptedString
     * @return
     */
    public String EncryptString(String decryptedString) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < decryptedString.length(); i++){
            sb.append(EncryptCharacter(decryptedString.charAt(i)));
        }

        return sb.toString();
    }

    /**
     * This is a private method used to encrypt a single plain character.
     * @param decryptedCharacter
     * @return
     */
    private char EncryptCharacter(char decryptedCharacter){
        for (int i = 0; i < 26; i++) {
            if (cipher[i][0] == decryptedCharacter) {
                return cipher[i][1];
            }
        }

        return decryptedCharacter;
    }

    /**
     * This is a public method used to decrypt an encrypted string.
     * @param encryptedString
     * @return
     */
    public String DecryptString(String encryptedString) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < encryptedString.length(); i++){
            sb.append(DecryptCharacter(encryptedString.charAt(i)));
        }

        return sb.toString();
    }

    /**
     * This is a private method used to decrypt a single encrypted character.
     * @param encryptedCharacter
     * @return
     */
    private char DecryptCharacter(char encryptedCharacter) {
        for (int i = 0; i < 26; i++) {
            if (cipher[i][1] == encryptedCharacter) {
                return cipher[i][0];
            }
        }

        return encryptedCharacter;
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

        String message = String.format("%s\n%s", PlainTextForward, EncryptionKey);
        OutputMessage(message, Constants.ENCRYPTION_FILENAME);
    }

    /**
     * This method removes the duplicate letter in the keyword.
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
                System.out.println(Constants.OUTPUT_MESSAGE);
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } else {
            System.out.println(message);
        }

    }

}