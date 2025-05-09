package dev.pigeonboys.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompetitiveSwimmer extends Member{

    Trainer trainer;
    List<Enum<SwimmingDisciplines>> disciplines;

    public CompetitiveSwimmer(int id, String name, int age, String address, boolean hasPaid, boolean active, Trainer trainer) {

        super(id, name, age, address, hasPaid, active);
        this.trainer = trainer;
        disciplines = new ArrayList<>();

    }

    void assignDisciplines(Scanner scanner) {

        boolean toContinue = false;
        boolean disciplineAssigned = false;
        int memberID = scanner.nextInt();
        int assignedDiscipline = scanner.nextInt();

        while(!toContinue) {

            System.out.println("Please enter the ID of the Swimmer that you want to assign disciplines to.");

            try {

                memberID = scanner.nextInt();
                toContinue = true;
                scanner.nextLine();


            } catch (Exception e) {
                System.out.println();
                scanner.nextLine();
            }

        }

        toContinue = false;

        while(!toContinue) {

            if (!disciplines.isEmpty()) {

                System.out.println("Member " + memberID + " is already assigned the following disciplines.");

                for (Enum<SwimmingDisciplines> s : disciplines) {
                    System.out.println(s);
                }

            }

            while(!disciplineAssigned) {

                System.out.println("Which disciplines do you want to assign the swimmer?");
                System.out.println();
                System.out.println("1. Butterfly.");
                System.out.println("2. Crawl.");
                System.out.println("3. Rygcrawl.");
                System.out.println("4. Brystsvoemning.");

                try {

                    assignedDiscipline = scanner.nextInt();
                    scanner.nextLine();

                } catch (Exception e) {

                    System.out.println("Invalid input.");
                    scanner.nextLine();

                }


                Enum<SwimmingDisciplines> addedDiscipline = null;

                switch (assignedDiscipline) {

                    case 1:
                        addedDiscipline = SwimmingDisciplines.BUTTERFLY;
                        disciplineAssigned = true;
                        break;
                    case 2:
                        addedDiscipline = SwimmingDisciplines.CRAWL;
                        disciplineAssigned = true;
                        break;
                    case 3:
                        addedDiscipline = SwimmingDisciplines.RYGCRAWL;
                        disciplineAssigned = true;
                        break;
                    case 4:
                        addedDiscipline = SwimmingDisciplines.BRYSTSVOEMNING;
                        disciplineAssigned = true;
                        break;

                    default:
                        System.out.println("Invalid input.");

                }

                if (disciplines.contains(addedDiscipline)) {

                    System.out.println("Swimmer is already assigned " + addedDiscipline);

                }
                else{
                    disciplines.add(addedDiscipline);
                    System.out.println("Swimmer has been assigned " + addedDiscipline);
                }

            }

            disciplineAssigned = false;

            int assignMore = 0;

            while(assignMore != 1 && assignMore != 2) {

                System.out.println("Do you want to assign more disciplines to the swimmer?");

                System.out.println("1. Yes");
                System.out.println("2. No");

                try {

                    assignMore = scanner.nextInt();
                    scanner.nextLine();

                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    scanner.nextLine();
                }

                switch (assignMore) {
                    case 1:

                        break;
                    case 2:
                        toContinue = true;
                        break;
                    default:
                        System.out.println("Invalid input.");
                }

            }

        }

    }
}
