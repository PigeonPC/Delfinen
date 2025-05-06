package dev.pigeonboys.swimclub;

import java.sql.SQLOutput;
import java.util.Scanner;

public class UI {

    public static void getUserInput() {

        showMenu();

        Scanner scanner = new Scanner(System.in);
        int s;

        while (true) {
            System.out.print("Indtast tal fra 1-5: ");
            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                if (s >= 1 && s <= 5) {
                    break;

                } else {
                    System.out.println("Ugyldigt tal. Indtast venligst et tal fra 1-5: ");
                }
            } else {
                System.out.println("Ugyldig input. Indtast venligst et tal: ");
                scanner.next();
            }

        }
    }

    public static void showMenu() {
        System.out.println("BRUGER MENU:\n----------");
        System.out.println("1. \" \"");
        System.out.println("2. \" \"");
        System.out.println("3. \" \"");
        System.out.println("4. \" \"");
        System.out.println("5. \" \"");


    }

}
