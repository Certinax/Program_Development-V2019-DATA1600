package client;

import java.lang.reflect.Method;

public class ClassTester {

    public static void main(String[] args) {

        try {
            Class<?> c = Class.forName("Testclient");
            printMethods(c);

            Class parent = c.getSuperclass();
            while (parent != null) {
                printMethods(parent);
                parent = parent.getSuperclass();
            }

            // production code should handle this exception more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static void printMethods(Class c) {
        System.out.format("Methods from %s%n", c);
        Method[] meths = c.getDeclaredMethods();
        if (meths.length != 0) {
            for (Method m : meths)
                System.out.format("  Method:  %s%n", m.toGenericString());
        } else {
            System.out.format("  -- no methods --%n");
        }
        System.out.format("%n");
    }
}
