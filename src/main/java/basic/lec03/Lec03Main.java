package basic.lec03;

public class Lec03Main {

    public static void main(String[] args) {
        multiLineString();
    }

    public static void printAgeIfPerson(Object obj) {
        if (obj instanceof Person) {
            Person person = (Person) obj;
            System.out.println(person.getAge());
        }
    }

    public static void printNameAndAge(Person person) {
        String personFormat = "이름: %s, 나이: %d";
        System.out.println(String.format(personFormat, person.getName(), person.getAge()));
    }

    public static void multiLineString() {
        String multiLineStr = """
            hello world
            ㅋㅋㅋ
            """.trim();
        System.out.println(multiLineStr);
    }

}
