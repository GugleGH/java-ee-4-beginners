package oopdemo;

public class Logger {
    private Saver saver = new FileSaver("////"); //Is-A
    private Filter filter = new LevelFilter();

    public void log(String message, int level) { //1M
       if (filter.filter(level)) {
           saver.save(message);
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

abstract class Filter {
    public abstract boolean filter(int level);
}

class LevelFilter extends Filter {
    @Override
    public boolean filter(int level) {
        return false;
    }
}