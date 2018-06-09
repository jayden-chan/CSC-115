/*
 * Name: Jayden Chan
 * Date: Jan 15 2018
 * Filename: VigenereCipher.java
 * Details: CSC 115 Assignment 1
 */

public class VigenereCipher implements Cipher {

    private String key;

    public VigenereCipher() {
        this("thisisthedefaultkey");
    }

    public VigenereCipher(String key) {
        this.key = key;
    }

    /**
     * Sets the key to be used by the Cipher, especially if this needs
     * to change during the life of a Cipher object (i.e., the
     * constructor already establishes the key when a Cipher object
     * is first created).
     * @param key A plain text key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Converts plain text to cipher text.
     * @param plainText The text to be encrypted.
     * @return the ciphertext.
     */
    public String encrypt(String plainText) {

        int[] plainInts = stringToIntArray(plainText);
        int[] keyInts = stringToIntArray(key);
        int[] encryptedInts = new int[plainText.length()];

        for(int i = 0; i < plainInts.length; i++) {
            encryptedInts[i] = (plainInts[i] + keyInts[i % key.length()]) % 26;
        }

        return intArrayToString(encryptedInts);
    }

    /**
     * Converts cipher text to plain text.
     * @param cipherText The previously encrypted text.
     * @return the plain text, decrypted.
     */
    public String decrypt(String cypherText) {

        int[] cypherCharInts = stringToIntArray(cypherText);
        int[] keyCharInts = stringToIntArray(key);
        int[] decryptedInts = new int[cypherCharInts.length];

        for(int i = 0; i < cypherText.length(); i++) {
            decryptedInts[i] = (26 + cypherCharInts[i] - keyCharInts[i % keyCharInts.length]) % 26;
        }

        return intArrayToString(decryptedInts);
    }

    /**
     * Prints out the provided text followed by the comma-delimited
     * contents of the array.
     * @param array The array to be printed.
     * @param text The text to be printed before the array contents.
     */
    private void dumpArray(int[] array, String text) {

        System.out.print(text);

        for(int i = 0; i < array.length-1; i++) {
            System.out.print(array[i] + ", ");
        }

        System.out.println(array[array.length - 1]);
    }

    /**
     * Converts an array of integers into a string of characters
     * Ex: 0 = A, 2 = B etc...
     * @param encodedText The array of integers to be converted.
     * @return The string of converted integers.
     */
    private String intArrayToString(int[] encodedText) {

        StringBuffer toReturn = new StringBuffer();

        for(int index : encodedText) {
            toReturn.append((char) (index + 97));
        }

        return toReturn.toString();
    }

    /**
     * Converts a string into an array of integers corresponding to
     * the integer indices of the alphabet.
     * @param text The string to be converted.
     * @return Array of alphabet integer indices.
     */
    private int[] stringToIntArray(String text) {

        int[] toReturn = new int[text.length()];

        for(int i = 0; i < text.length(); i++) {
            toReturn[i] = (text.charAt(i) - 97);
        }

        return toReturn;
    }
}
