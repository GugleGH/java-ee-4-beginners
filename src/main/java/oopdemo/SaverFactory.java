package oopdemo;

/**
 * Created by eugene on 12/01/2017.
 */
public class SaverFactory {
    public static Saver create() {
        return new FileSaver("");
    }
}
