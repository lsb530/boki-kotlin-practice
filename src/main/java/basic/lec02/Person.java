package basic.lec02;

import org.jetbrains.annotations.NotNull;

public class Person {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    // @Nullable
    @NotNull
    public String getName() {
        return name;
    }

}


