package labs_examples.input_output.labs;

import java.io.*;

/**
 * Input/Output Exercise 3: 
 *
 *    1) Demonstrate the use of the DataOutputStream and DataInputStream
 *
 */


public class Exercise_03 {


    public static void main(String[] args) throws Exception {

        String path = "src/labs_examples/input_output/files/newData.dat";



        try(
                DataOutputStream dO = new DataOutputStream ( new FileOutputStream(path));
                DataInputStream di = new DataInputStream( new FileInputStream(path))
        ) {

            dO.writeInt(50);
            dO.writeDouble(100.5985);
            dO.writeUTF("Don't tell my mama");


            int rInt = di.readInt();
            double rdouble = di.readDouble();
            String rString = di.readUTF();

            System.out.printf("reading: %d, %.2f, %s", rInt, rdouble, rString  );
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}