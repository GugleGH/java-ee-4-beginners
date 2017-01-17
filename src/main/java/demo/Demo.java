package demo;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.Locale;

public class Demo {
    private transient int id; //don't serialize

    public static void main(String[] args) throws IOException {
        File file = new File("target" + File.separator + "text.txt");
        File file2 = new File("target", "text.txt");
        file.exists();

        final BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(
                new BufferedInputStream(
                    new FileInputStream(file2)), "UTF-8")); //chaining

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(">> " + line);
        }

        bufferedReader.close();

    }
}