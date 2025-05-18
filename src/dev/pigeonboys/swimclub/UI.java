package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.CompetitiveSwimmer;
import dev.pigeonboys.member.Member;
import dev.pigeonboys.member.MemberManager;
import dev.pigeonboys.member.Trainer;

import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
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
            System.out.print(ANSI_GREEN + "Indtast et tal fra 0-3: " + ANSI_RESET);
            if (scanner.hasNextInt()) {
                s = scanner.nextInt();
                if (s >= 0 && s <= 4) {
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
        System.out.println("3." + ANSI_BLUE + "\"Kasserer\"" + ANSI_RESET);
        System.out.println("0." + ANSI_BLUE + "\"Exit\"" + ANSI_RESET);


    }

    public static void handleChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 0:
                break;
            case 1:
                formand(scanner);
                break;

            case 2:
                traener(scanner);
                break;

            case 3:
                kasser(scanner);
                break;

            default:
                System.out.println("Invalid choice!");
                break;
        }

    }

    public static void formand(Scanner scanner) {

        boolean exitApp = false;


        while (!exitApp) {
            System.out.println("\nForetag et valg:");
            System.out.println("1. Tilføj nyt medlem");
            System.out.println("2. Rediger medlem");
            System.out.println("3. Slet medlem");
            System.out.println("0. Return");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    //0. Exit
                    exitApp = true;
                    getUserInput(scanner);
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
                default:
                    System.out.println("Please choose a valid option");
                    break;
            }


        }

    }

    public static void traener(Scanner scanner){
        Trainer trainer = new Trainer("");
        CompetitiveSwimmer cs = new CompetitiveSwimmer(0,"",0,"",false,false,trainer);

        boolean toContinue = false;

        while(!toContinue) {

            System.out.println("\nForetag et valg:");
            System.out.println("1. Tilføj konkurrence resultater");
            System.out.println("2. Se tidligere konkurrence resultater");
            System.out.println("3. Tilføj trænings resultater");
            System.out.println("4. Se tidliger trænings resultater");
            System.out.println("0. Return");

            try {

                int choice = scanner.nextInt();

                switch(choice) {
                    case 0:
                        toContinue = true;
                        getUserInput(scanner);
                        break;
                    case 1:
                        cs.addCompetitionResult(scanner);
                        cs.updateCompetitiveResults();
                        break;
                    case 2:
                        cs.viewCompetitiveResults(scanner);
                        break;
                    case 3:
                        cs.addTrainingResult(scanner);
                        cs.updateTrainingResults();
                        break;
                    case 4:
                        cs.viewTrainingResults(scanner);
                        break;
                    default:
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input." + e.getMessage());
                scanner.nextLine();
            }

        }

    }

    public static void kasser(Scanner scanner) {

        boolean toContinue = false;

        Member member = new Member(0, "", 0, "", false, false);
        MemberManager mm = new MemberManager();
        FileHandler fh = new FileHandler();
        FeeManager fm = new FeeManager(fh);

        while(!toContinue) {

            System.out.println("\nForetag et valg:");
            System.out.println("1. Vis forventet indtægt fra kontingenter.");
            System.out.println("2. Vis medlemmer i restance.");
            System.out.println("0. Return");

            try {

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch(choice) {
                    case 0:
                        toContinue = true;
                        getUserInput(scanner);
                        break;
                    case 1:
                        System.out.println(member.calculateTotalFee(mm.getMembers()));
                        break;
                    case 2:

                        for(Member m : fm.getUnpaidMembers()) {
                            System.out.println(m);
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }

        }


    }

}





