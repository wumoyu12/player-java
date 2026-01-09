package javaPack;

import java.util.Scanner;

public class runner_Player
{

    public static void main(String[] args)
    {

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome! Each round creates a new player and runs an attack.");

        Player p1 = new Player();
        System.out.println("\n--- Default Player (P1) ---");
        System.out.println(p1);

        boolean keepGoing = true;

        while (keepGoing)
        {

            System.out.println("\n==============================");
            System.out.println("Create a new player");
            System.out.println("==============================");

            String name = readNonEmptyName(input);

            int x = readIntInRange(input, "Enter x coordinate: ", -100000, 100000);
            int y = readIntInRange(input, "Enter y coordinate: ", -100000, 100000);
            int z = readIntInRange(input, "Enter z coordinate: ", -100000, 100000);

            int hp = readIntInRange(input, "Enter health (>= 0): ", 0, 100000);
            int direction = readIntInRange(input, "Enter direction (1-6): ", 1, 6);

            Player target = new Player(name, x, y, z, hp, direction);

            int damage = readIntInRange(
                    input,
                    "Enter damage (>= 0): ",
                    0,
                    100000
            );

            int p1HpBefore = p1.getHp();
            int targetHpBefore = target.getHp();

            p1.attack(target, damage);

            System.out.println("\n--- AFTER ATTACK RESULT ---");
            System.out.println("P1 HP: " + p1HpBefore + " -> " + p1.getHp());
            System.out.println(target.getName() + " HP: " + targetHpBefore + " -> " + target.getHp());

            System.out.println("\nFull P1 info:\n" + p1);
            System.out.println("\nFull target info:\n" + target);

            keepGoing = askAnotherRound(input);
        }

        System.out.println("\nProgram ended.");
        input.close();
    }

    // Read non-empty name
    private static String readNonEmptyName(Scanner input) {
        while (true)
        {
            System.out.print("Enter name (cannot be empty): ");
            String name = input.nextLine();

            if (isBlank(name))
            {
                System.out.println("Invalid name.");
            }
            else
            {
                return trimSpaces(name);
            }
        }
    }

    // Ask another round
    private static boolean askAnotherRound(Scanner input)
    {
        while (true)
        {
            System.out.print("\nAnother round? (y/yes/n/no): ");
            String ans = input.nextLine();

            if (isBlank(ans))
            {
                System.out.println("Invalid input.");
            }
            else
            {
                String s = toLower(trimSpaces(ans));

                if (s.equals("y") || s.equals("yes"))
                {
                    return true;
                }
                else if (s.equals("n") || s.equals("no"))
                {
                    return false;
                }
                else
                {
                    System.out.println("Invalid input.");
                }
            }
        }
    }

    // Read integer with ASCII and range check
    private static int readIntInRange(Scanner input, String prompt, int min, int max) {
        while (true)
        {
            System.out.print(prompt);
            String s = input.nextLine();

            if (isBlank(s))
            {
                System.out.println("Invalid input.");
            }
            else if (hasLeadingOrTrailingSpaces(s))
            {
                System.out.println("Invalid input.");
            }
            else if (isIntegerAscii(s) == false)
            {
                System.out.println("Invalid input.");
            }
            else
            {
                int value = parseIntAscii(s);

                if (value < min || value > max)
                {
                    System.out.println("Invalid input.");
                }
                else
                {
                    return value;
                }
            }
        }
    }

    // Helper methods

    private static boolean isBlank(String s)
    {
        if (s == null)
        {
            return true;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c != ' ' && c != '\t' && c != '\n' && c != '\r')
            {
                return false;
            }
        }
        return true;
    }

    private static boolean hasLeadingOrTrailingSpaces(String s) {
        if (s.length() == 0)
        {
            return false;
        }

        if (s.charAt(0) == ' ' || s.charAt(s.length() - 1) == ' ')
        {
            return true;
        }

        return false;
    }

    private static boolean isIntegerAscii(String s) {
        int i = 0;

        if (s.charAt(0) == '-')
        {
            if (s.length() == 1)
            {
                return false;
            }
            i = 1;
        }

        while (i < s.length())
        {
            int code = (int) s.charAt(i);
            if (code < 48 || code > 57)
            {
                return false;
            }
            i++;
        }

        return true;
    }

    private static int parseIntAscii(String s) {
        int i = 0;
        int sign = 1;

        if (s.charAt(0) == '-')
        {
            sign = -1;
            i = 1;
        }

        int value = 0;
        while (i < s.length())
        {
            int digit = ((int) s.charAt(i)) - 48;
            value = value * 10 + digit;
            i++;
        }

        return value * sign;
    }

    private static String trimSpaces(String s)
    {
        int start = 0;
        int end = s.length() - 1;

        while (start <= end && s.charAt(start) == ' ')
        {
            start++;
        }

        while (end >= start && s.charAt(end) == ' ')
        {
            end--;
        }

        if (start > end)
        {
            return "";
        }

        return s.substring(start, end + 1);
    }

    private static String toLower(String s)
    {
        String out = "";

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int code = (int) c;

            if (code >= 65 && code <= 90) {
                out = out + (char) (code + 32);
            } else {
                out = out + c;
            }
        }

        return out;
    }
}
