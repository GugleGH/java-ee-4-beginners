package oopdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Logger<T extends String & Serializable, Integer> {
    private Saver saver;
    private Filter filter; //DI

    public Logger(Saver saver, Filter filter) {
        this.saver = saver;
        this.filter = filter;
    }

    public void log(T message, int level) { //1M
       if (filter.filter(level)) {
           if (saver instanceof Serializable) {
               saver.save(message);
           }
       }
   }

   public T[] getLast10SavedObjects() {
        return null;
   }

   public <U> U m(U arg) { return null; }
}

class Main {
    public static void main(String[] args) {
        Logger<String> logger = new Logger<>(
            new FileSaver(""),
            new LevelFilter()
        );

        logger.log("", 1);
        logger.log("", 1);
        logger.log("", 1);

        String[] savedObjects = logger.getLast10SavedObjects();
        for (String element : savedObjects) {
            System.out.println(element.toUpperCase());
        }
    }
}

class Saver {
    public void save(Object message) {
        //.....
    }
}

class FileSaver extends Saver {
    private String path;

    FileSaver(String path) {
        this.path = path;
    }

    @Override
    public void save(String message) {
        //......IO
        super.save(message);
        //......
    }
}

interface Filter {
    boolean filter(int level);
}

@MySuperPuperFlag(p = "fff")
class LevelFilter implements Filter, Serializable {
    @Override
    public boolean filter(int level) {
        return false;
    }
}