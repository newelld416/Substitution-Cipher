package com.company;

public class Main {

    public static void main(String[] args) {
        TranslateEngine translateEngine = new TranslateEngine(GetEncryptionKey());

        System.out.println("Encrypted String: " + args[0]);
        System.out.println("Decrypted String: " + translateEngine.TranslateString(args[0]));
    }

    private static String GetEncryptionKey() {
        return "";
    }
}
