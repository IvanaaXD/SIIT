package org.example;

public class Test {

    public static void main(String[] args) {
        try {
            bar();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
        foo();
    }

    public static void bar() {}

    public static void foo() throws NullPointerException {}

}