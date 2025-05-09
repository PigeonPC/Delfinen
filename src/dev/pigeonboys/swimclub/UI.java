package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.MemberManager;

import java.util.Scanner;

import static dev.pigeonboys.member.MemberManager.addNewMember;


public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";


    public static void getUserInput(Scanner scanner) {

        showMenu();
        int s;

        while (true) {
            System.out.print(ANSI_GREEN + "Indtast et tal fra 1-3: " + ANSI_RESET);
            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                if (s >= 1 && s <= 3) {
                    break;

                } else {
                    System.out.println("Ugyldigt tal. Indtast venligst et tal fra 1-3: ");
                }
            } else {
                System.out.println("Ugyldig input. Indtast venligst et tal: ");
                scanner.next();
            }

        }

        handleChoice(s, scanner);

    }


    public static void showMenu() {

        System.out.println("BRUGER MENU:\n----------");
        System.out.println("1." + ANSI_BLUE + "\"Formand\"" + ANSI_RESET);
        System.out.println("2." + ANSI_BLUE + "\"Træner\"" + ANSI_RESET);
        System.out.println("3." + ANSI_BLUE + "\"Kasér\"" + ANSI_RESET);


    }


    public static void handleChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                formand(scanner);
                break;

            case 2:
                System.out.println("placeholder");
                break;

            case 3:
                System.out.println("placeholder");
                break;

            default:
                System.out.println("Invalid choice!");

        }


    }

    public static void formand(Scanner scanner) {

        boolean exitApp = false;


        while (!exitApp) {
            System.out.println("\nForetag et valg:");
            System.out.println("1. Tilføj nyt medlem");
            System.out.println("2. Rediger medlem");
            System.out.println("3. Slet medlem");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    //0. Exit
                    exitApp = true;
                    break;

                case 1:
                    //1.add new member
                    addNewMember(scanner);

                    break;

                case 2:
                    //2. edit member
                    MemberManager.editMember(scanner);
                    break;

                case 3:
                    //2. delete member
                    MemberManager.deleteMember(scanner);

                    break;
            }


        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UI.getUserInput(scanner);

        scanner.close();
    }


}





