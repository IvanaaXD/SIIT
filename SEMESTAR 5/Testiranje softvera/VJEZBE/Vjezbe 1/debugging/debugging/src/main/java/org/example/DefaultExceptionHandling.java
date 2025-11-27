package org.example;

public class DefaultExceptionHandling {
    // Main driver method
    public static void main(String[] args)
    {
        // Taking an array of size 4
        int[] arr = new int[4];

        // Now this statement will cause an exception
        int i = arr[4];

        // This statement will never execute
        // as above we caught with an exception
        System.out.println("Hi, I want to execute");
    }
}
