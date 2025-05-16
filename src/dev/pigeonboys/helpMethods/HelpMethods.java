package dev.pigeonboys.helpMethods;

import java.util.Scanner;

public class HelpMethods {

    public static int validateInt(Scanner scanner) {

        int input = 0;
        boolean isValid = false;

        while (!isValid) {

            try {

                input = scanner.nextInt();
                scanner.nextLine();
                isValid = true;

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }

        }

        return input;
    }

    public static boolean validateBoolean(Scanner scanner) {

        int input = 0;

        while (input == 1 || input == 2) {

            try {

                input = scanner.nextInt();
                scanner.nextLine();

                if (input == 1) {

                    return true;

                } else if (input == 2) {
                    return false;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return true;
    }

}
