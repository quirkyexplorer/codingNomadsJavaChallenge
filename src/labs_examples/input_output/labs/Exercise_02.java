package labs_examples.input_output.labs;

import java.io.*;

/**
 * Input/Output Exercise 2: File encryption
 *
 *      -Using the BufferedReader, read a file character by character and write an encrypted version to a new file.
 *      -For example, change every 'a' to '-' and every 'e' to '~' .
 *      -Make sure you close the connections to both files.
 *
 *      Then, ead back the encrypted file using the BufferedReader and
 *      print out the unencrypted version. Does it match the original file?
 *
 */

public class Exercise_02 {
    public static void main(String[] args) {

        String n = "src/labs_examples/input_output/files/encryptedData.txt";
        try (
                BufferedReader bf =
                        new BufferedReader(
                                new FileReader("src/labs_examples/input_output/files/char_data.txt"));
                BufferedWriter bw =
                        new BufferedWriter(
                                new FileWriter(n))
            ) {
            int ch;
            char toEncrypt;
            while( (ch = bf.read()) != -1) {
                toEncrypt = (char) ch;
                System.out.println(toEncrypt);

                if (toEncrypt == 'e' ) {

                    bw.write('~');
                }
                else if (toEncrypt == 'a') {
                    bw.write('-');
                }

                else {
                    bw.write(toEncrypt);
                }
            }

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}