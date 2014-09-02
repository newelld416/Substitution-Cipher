package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Daniel Newell on 9/1/2014.
 */
public class TranslateEngine {

    private char[][] cipher = new char[54][2];
    private String standardString = "";
    private  String codedString = "";
    private String[] bothStrings = new String[2];


    /**
     * This is the constructor method for this class.
     * @param filePath
     */
    public TranslateEngine(String filePath) {
        ReadCipherFile(filePath);
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
        if (decryptedCharacter != ' ') {
            for (int i = 0; i < 54; i++) {
                if (cipher[i][0] == decryptedCharacter) {
                    return cipher[i][1];
                }
            }
        }

        return decryptedCharacter;
    }

    /**
     * This is a public method used to Decrypt an encrypted string.
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
        if (encryptedCharacter != ' ') {
            for (int i = 0; i < 54; i++) {
                if (cipher[i][1] == encryptedCharacter) {
                    return cipher[i][0];
                }
            }
        }
        return encryptedCharacter;
    }

    /**
     * This is a private method that reads the cipher file designated in the constructor.
     * @param fileName
     */
    private void ReadCipherFile(String fileName){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            PopulateCipherArray(sb.toString().split(System.lineSeparator()));

            br.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }

    /**
     * This is a private method used to populate the cipherArray used in encryption and decryption.
     * @param bothStrings
     */
    private void PopulateCipherArray(String[] bothStrings){
        if (bothStrings.length == 2) {
            standardString = bothStrings[0];
            for (int i = 0; i < standardString.length(); i++) {
                cipher[i][0] = standardString.charAt(i);
            }

            codedString = bothStrings[1];
            for (int i = 0; i < codedString.length(); i++) {
                cipher[i][1] = codedString.charAt(i);
            }
        }
    }

}