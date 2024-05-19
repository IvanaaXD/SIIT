namespace CRUDExample.Console;

static class ConsoleViewUtils
{
    public static int SafeInputInt()
    {
        int input;

        string rawInput = System.Console.ReadLine() ?? string.Empty;

        while (!int.TryParse(rawInput, out input))
        {
            System.Console.WriteLine("Not a valid number, try again: ");

            rawInput = System.Console.ReadLine() ?? string.Empty;
        }

        return input;
    }
}