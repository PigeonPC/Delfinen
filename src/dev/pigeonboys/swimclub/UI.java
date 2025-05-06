package dev.pigeonboys.swimclub;

import java.sql.SQLOutput;
import java.util.Scanner;





public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";



    public static void getUserInput() {

        showMenu();

        Scanner scanner = new Scanner(System.in);
        int s;

        while (true) {
            System.out.print(ANSI_GREEN + "Indtast tal fra 1-5: " + ANSI_RESET);
            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                if (s >= 1 && s <= 3) {
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
        System.out.println("1." + ANSI_BLUE + "\"Formand\"" + ANSI_RESET);
        System.out.println("2." + ANSI_BLUE + "\"Træner\"" + ANSI_RESET);
        System.out.println("1." + ANSI_BLUE + "\"Kasér\"" + ANSI_RESET);



    }

    public static void main (String[] args){
        getUserInput();

    }

}
