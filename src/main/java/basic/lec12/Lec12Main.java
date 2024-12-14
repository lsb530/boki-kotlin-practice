package basic.lec12;

public class Lec12Main {

    public static void main(String[] args) {
        PersonWithJvmStaticObject.newBaby("ABC");

        PersonWithNoNameObject.Companion.newBaby("ABC");
        Person.Factory.newBaby("ABC");

        moveSomething(new Movable() {
            @Override
            public void move() {
                System.out.println("움직임");
            }

            @Override
            public void fly() {
                System.out.println("난다");
            }
        });
    }

    private static void moveSomething(Movable movable) {
        movable.move();
        movable.fly();
    }

}
