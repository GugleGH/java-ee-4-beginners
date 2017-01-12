package oopdemo;

public class Logger {
   public void log(String message, int level) {
       if (filter(level)) {
           save(message);
       }
   }

    private void save(String message) {

    }

    private boolean filter(int level) {
        return false;
    }
}
