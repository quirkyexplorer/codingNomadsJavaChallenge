package labs_examples.input_output.labs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *      Following the video examples and source code found in the src/labs_examples/input_output/examples/csv_parser
 *      package, create a new application that will parse a custom CSV file that you created and map each line of the
 *      csv to a custom POJO that you create.
 *
 *      Then add that object to an arraylist. After you have mapped each row of the csv to objects
 *      and added each object to an arraylist, print out each object using the objects custom toString() method.
 *
 *      Then, write the arraylist of objects back out to a new .csv file. Ensure that the resulting csv file is valid.
 *
 */


//Teacher Name,
//Dance Styles (Couples),
//Years of Experience,
//City/Country,
//Group Class Rate ($/hr),
//Private Lesson Rate ($/hr)

public class Exercise_04 {

//Teacher Name, 0
//Dance Styles (Couples), 1
//Years of Experience, 2
//City/Country, 3
//Group Class Rate ($/hr), 4
//Private Lesson Rate ($/hr), 5
    public static void main(String[] args) {
        String path = "src/labs_examples/input_output/files/danceTeachers.txt";
        String outPath = "src/labs_examples/input_output/files/danceTeachersCOPY.txt";
        try(
                BufferedReader br = new BufferedReader(new FileReader(path));
                BufferedWriter bw = new BufferedWriter(new FileWriter(outPath))
                ) {
                List<Instructor> myList = new ArrayList<>();
                String line = "";
                String[] temp;
                br.readLine();
                while ( (line = br.readLine()) != null ) {
                    // using custome line spliter for multiple values in a field
                    temp = SplitLine.splitCSVLine(line);
                    Instructor i = new Instructor(
                            temp[0],
                            temp[1],
                            Integer.parseInt(temp[2]),
                            temp[3],
                            Double.parseDouble(temp[4]),
                            Double.parseDouble(temp[5]));
                    System.out.println(i);
                    myList.add(i);

                }

                for(Instructor i : myList) {
                    bw.write(toCSVLine(i));
                    bw.newLine();

                }
                System.out.println("my list: " + myList);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    // helper method to format to csv each line before writing
    public static String toCSVLine(Instructor instructor) {

        String firstLine = "ID,Teacher Name,Dance Styles (Couples),Years of Experience,City/Country,Private Lesson Rate ($/hr), Group Class Rate ($/hr)";

        return String.format("%d,%s,\"%s\",%d,\"%s\",%.2f,%.2f",
                instructor.getId(),
                instructor.getName(),
                instructor.getStyles(),
                instructor.getYearsEx(),
                instructor.getCity(),
                instructor.getRateSingle(),
                instructor.getRateClass()
        );
    }
}

// helper class to parse special items like "salsa, bachata, kizomba"
class SplitLine {
    public static String [] splitCSVLine ( String line) {

        List<String> fields = new ArrayList<>();
        Pattern p =  Pattern.compile("\"([^\"]*)\"|([^,]+)");
        Matcher m = p.matcher(line);

        while (m.find()) {
            if (m.group(1) != null) {
                fields.add(m.group(1)); // quoted field → strips the quotes
            } else {
                fields.add(m.group(2).trim()); // unquoted field
            }
        }
        return fields.toArray(new String[0]);
    }
}

 class Instructor {
    private static int counter = 1;private int id;
    private String name;
    private String Styles;
    private int yearsEx;
    private String city;
    private double rateSingle;
    private double rateClass;

    public Instructor () {
    }

    public Instructor(String name, String styles, int yearsEx, String city, double rateSingle, double rateClass) {
        this.id = counter++;
        this.name = name;
        this.Styles = styles;
        this.yearsEx = yearsEx;
        this.city = city;
        this.rateSingle = rateSingle;
        this.rateClass = rateClass;
    }

     public int getId() {
         return id;
     }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearsEx() {
        return yearsEx;
    }

    public void setYearsEx(int yearsEx) {
        this.yearsEx = yearsEx;
    }

    public String getStyles() {
        return Styles;
    }

    public void setStyles(String styles) {
        this.Styles = styles;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRateClass() {
        return rateClass;
    }

    public void setRateClass(double rateClass) {
        this.rateClass = rateClass;
    }

    public double getRateSingle() {
        return rateSingle;
    }

    public void setRateSingle(double rateSingle) {
        this.rateSingle = rateSingle;
    }

    @Override
    public String toString() {
        return  "name: '" + name + '\'' +
                ", Styles: '" + Styles + '\'' +
                ", yearsEx: " + yearsEx +
                ", city: '" + city + '\'' +
                ", rateClass: " + rateClass +
                ", rateSingle: " + rateSingle +
                '.';
    }
}