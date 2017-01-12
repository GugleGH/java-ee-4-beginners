package oopdemo;

import java.io.Serializable;

public class Logger {
    private Saver saver = SaverFactory.create(); //Is-A
    private Filter filter = new LevelFilter(); //Creator

    public void log(String message, int level) { //1M
       if (filter.filter(level)) {
           if (saver instanceof Serializable) {
               saver.save(message);
           }
       }
   }
}

class Saver {
    public void save(String message) {
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