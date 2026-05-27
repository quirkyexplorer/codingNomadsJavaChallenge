package labs_examples.input_output.labs;

import java.io.*;

/**
 * Input/Output Exercise 1: File input/output
 *
 *      Using the BufferedInputStream, read a text file 5 bytes at a time and write each byte to a new file.
 *      Make sure you close the connections to both files.
 *
 *
 */

class Example {
    public static void main(String[] args) throws IOException {

        FileInputStream input = null;
        BufferedInputStream bf = null;
        String path = "src/labs_examples/input_output/files/byte_data";
        String newPath = "src/labs_examples/input_output/files/newByte_data";


        FileOutputStream out = null;
        BufferedOutputStream bfOut = null;

        try {
            input  = new FileInputStream(path);

            bf = new BufferedInputStream(input);

            out = new FileOutputStream(newPath);

            bfOut = new BufferedOutputStream(out);

            byte[] buffer = new byte[5];

            int bytesRead = 0;

            while ((bytesRead = bf.read(buffer)) != -1) {
                   bfOut.write(buffer, 0 , bytesRead);
            }
        }
        catch(IOException e ) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally {

            try {
                bf.close();
                input.close();
                bfOut.close();
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}