package basic.lec12;

public class JavaPerson {

    private static final int MIN_AGE = 1;
    private String name;
    private int age;

    private JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static JavaPerson newBaby(String name) {
        return new JavaPerson(name, MIN_AGE);
    }

}

