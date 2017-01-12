package oopdemo;

import java.io.Serializable;

public class Logger {
    private Saver saver;
    private Filter filter; //DI

    public Logger(Saver saver, Filter filter) {
        this.saver = saver;
        this.filter = filter;
    }

    public void log(Object message, int level) { //1M
       if (filter.filter(level)) {
           if (saver instanceof Serializable) {
               saver.save(message);
           }
       }
   }

   public Object[] getLast10SavedObjects() {
        return null;
   }
}

class Main {
    public static void main(String[] args) {
        Logger logger = new Logger(
            new FileSaver(""),
            new LevelFilter()
        );

        logger.log("", 1);
        logger.log("", 1);
        logger.log("", 1);

        Object[] savedObjects = logger.getLast10SavedObjects();
        for (Object element : savedObjects) {
            if (element instanceof String) {
                String realElement = (String) element;
                System.out.println(realElement.toUpperCase());
            }
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