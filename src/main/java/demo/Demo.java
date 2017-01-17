package demo;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class Demo {
    private transient int id; //don't serialize

    public static void main(String[] args) throws IOException {
        File file = new File("target" + File.separator + "text.txt");
        File file2 = new File("target", "text.txt");
        file.exists();

        
    }
}