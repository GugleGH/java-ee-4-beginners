package oopdemo;

public class Logger {
    private FileSaver saver = new FileSaver("////");
    private LevelFilter filter = new LevelFilter();

    public void log(String message, int level) {
       if (filter.filter(level)) {
           saver.save(message);
       }
   }
}

class FileSaver {
    private String path;

    FileSaver(String path) {
        this.path = path;
    }

    public void save(String message) {

    }
}

class LevelFilter {
    public boolean filter(int level) {
        return false;
    }
}