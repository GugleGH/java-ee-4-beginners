package demo;

import java.io.IOException;
import java.util.Properties;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;

public class Demo {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        System.currentTimeMillis();
        System.nanoTime();
        final Properties properties = System.getProperties();
//        System.arraycopy(); //JNI/JNA/....

//        final Process process = Runtime.getRuntime().exec("ls dir");
//        process.getInputStream();
        Runtime.getRuntime().availableProcessors();

        Cat cat = new Cat(); //LSP
        //...
        System.out.println(cat.clone());
        cat.equals(new TomCat());

        "".split(",");

        //Pattern  p = "(a{0..6})(a*)[a..zA..Z]:w+".compile
        //Matcher

        Integer i1 = 9; //byte
        Integer i2 = 9;
        System.out.println(i1 == i2);

        parseDouble("");
        valueOf("");
        Integer.getInteger("file.enc");

    }
}

class TomCat extends Cat {
    private int color;
}

class Cat extends Object implements Cloneable {
    private Cat friendship;
    private String name;
    private int age;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Cat clone() throws CloneNotSupportedException {
        return (Cat)super.clone();
    }


    //Effective Java: Bloch ->
    //Java Pitfalls
    @Override
    public boolean equals(Object o) { //o = tomcat
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;

        Cat cat = (Cat) o;

        if (age != cat.age) return false;
        if (friendship != null ? !friendship.equals(cat.friendship) : cat.friendship != null) return false;
        return name != null ? name.equals(cat.name) : cat.name == null;
    }

    @Override
    public int hashCode() {
        int result = friendship != null ? friendship.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}