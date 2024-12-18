package expert.c_function;

import java.security.PrivilegedExceptionAction;

public class Lec16Java {
    public static void main(String[] args) {
        StringFilter filter1 = new StringFilter() {
            @Override
            public boolean predicate(String str) {
                return str.startsWith("A");
            }
        };

        StringFilter filter2 = s -> s.startsWith("A");

        /* Method Reference
           ㄴReturn Type: Functional Interface
         */
        // Lec16Java::getHello;
    }

    public static String getHello() {
        return "Hello";
    }
}
