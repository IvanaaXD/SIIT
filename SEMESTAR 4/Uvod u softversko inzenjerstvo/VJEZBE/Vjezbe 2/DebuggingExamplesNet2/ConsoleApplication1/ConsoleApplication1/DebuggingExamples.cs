using System;
using System.Collections.Generic;
using System.Drawing;

class DebuggingExamples
{
    static void Main()
    {
        // Change the code so it doesn't break. Detect which error is happening and why it is happening using debugger.
        //example1();

        // With the use of debugger, draw a conclusion on how referencing works when using classes and structures.
        //example2(); 

        // Change the code so it doesn't break. Detect which error is happening and why it is happening using debugger.
        //example3();

        // Try changing the code while running it in debug mode and returning the location of the debugger before the changed code.
        Console.ReadKey();
    }

    static void example1()
    {
        int positiveNumber = 5;
        int negativeNumber = -5;

        Console.WriteLine($"Factorial of {positiveNumber}: {CalculateFactorial(positiveNumber)}");
        Console.WriteLine($"Factorial of {negativeNumber}: {CalculateFactorial(negativeNumber)}");
    }

    static void example2()
    {

        Pen pen1 = new Pen(Color.Black);
        Pen pen2 = pen1;
        pen2.Color = Color.Blue;
        Console.WriteLine(pen1.Color);     
        Console.WriteLine(pen2.Color);

        Point point1 = new Point(20, 30);
        Point point2 = point1;
        point2.X = 50;
        Console.WriteLine(point1.X);
        Console.WriteLine(point2.X);
    }

    static void example3()
    {

        List<int> values = new List<int>();

        List<int> otherValues = new List<int> { 10, 20, 30, 40, 50 };
        Console.WriteLine("Hello");

        Console.WriteLine("Values in 'values' list:");

        for (int i=0; i<=4; i++)
        {
            values[i] = i;
        }

        for (int i=0; i<=4; i++)
        {
            otherValues[i] = values[i];
        }

    }

    static int CalculateFactorial(int n)
    {
        if (n == 0)
        {
            return 1;
        }
        else
        {
            return n * CalculateFactorial(n - 1);
        }
    }

    public struct Point
    {
        public int X { get; set; }
        public int Y { get; set; }

        public Point(int x, int y)
        {
            X = x;
            Y = y;
        }

    }

    public class Pen
    {
        public Color Color { get; set; }

        public Pen(Color color)
        {
            Color = color;
        }
        
        public Pen()
        {

        }
    }

    class MyObject
    {
        public int Value { get; }

        public MyObject(int value)
        {
            Value = value;
        }
    }

    public enum Color
    {
        Black,
        Blue
    }
}
