package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Demo {
    public static void main(String... args) {
        try {
//            fopen();
            ///....
        } catch (NullPointerException | ArithmeticException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
//            Arrays.asList(e.getStackTrace()).forEach(System.out::println);
            throw new RuntimeException("2", e); //re-throw
        } finally {
//            throw new RuntimeException("3"); //Suppress
            /*
            if(f != null) try {
                f.close();
            } catch (Exception e) {

            }
            */
        }

        try (
            Res r = new Res();
            InputStream is = new FileInputStream("")
        ) {
            //....
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fopen() throws MyBEx {
        //.....
        if (true) try {
            throw new Exception("1");
        } catch (Exception e) {
            throw new MyBEx("1111", e);
        }
        //...
    }
}