package dev.pigeonboys.member;

import dev.pigeonboys.exceptions.FileOperationException;
import dev.pigeonboys.exceptions.InvalidMemberDataException;
import dev.pigeonboys.helpMethods.HelpMethods;
import dev.pigeonboys.swimclub.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    static FileHandler fileHandler;
    static List<Member> members = new ArrayList<>();

    public MemberManager() {
        this.fileHandler = new FileHandler();
        this.members = new ArrayList<>();
        loadAllData();

    }

    public static List<Member> getMembers() {
        return members;
    }

    public static void addNewMember(Scanner scanner) throws InvalidMemberDataException {

        boolean memberIDAvailable = true;
        int memberID;

        do {

            System.out.println("Please enter member ID.");
            memberID = HelpMethods.validateInt(scanner);

            for (Member member : members) {
                if (memberID == member.getId()) {
                    memberIDAvailable = false;
                    System.out.println("memberID not available. Please enter an available ID.");
                    break;
                }
            }

        } while (memberIDAvailable);

        System.out.println("Please enter name of member.");
        String name = scanner.nextLine();

        System.out.println("Please enter age of member.");
        int age = HelpMethods.validateInt(scanner);

        System.out.println("Please enter address of member.");
        String address = scanner.nextLine();

        System.out.println("Please choose a payment status.");
        System.out.println("1. Paid");
        System.out.println("2. Not paid");
        boolean paymentStatus = HelpMethods.validateBoolean(scanner);

        System.out.println("Please choose a membership status.");
        System.out.println("1. Active");
        System.out.println("2. Passive");
        boolean membershipStatus = HelpMethods.validateBoolean(scanner);

        Member member = new Member(memberID, name, age, address, paymentStatus, membershipStatus);

        members.add(member);

        try {
            fileHandler.saveMember(member);
        } catch (FileOperationException e) {
            System.err.println("Fejl ved gem af medlemsdata: " + e.getMessage());
        }

    }

    public static void editMember(Scanner scanner) {

        boolean memberExists = false;
        int memberID = 0;

        do {

            System.out.println("Please enter the ID of the member whose attributes you want to change");

            memberID = HelpMethods.validateInt(scanner);

            for (Member member : members) {
                if (member.getId() == memberID) {
                    memberExists = true;
                    break;
                }
            }

        } while ((!memberExists));

        for (Member s : members) {
            if (memberID == s.getId()) {
                System.out.println(s);
                break;

            }
        }

        int input;

        do {

            System.out.println("Which attribute do you want to edit?");
            System.out.println();
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Address");
            System.out.println("4. Payment Status");
            System.out.println("5. Membership Status");

            input = HelpMethods.validateInt(scanner);

        } while (!(input > 0 && input <= 5));

        switch (input) {

            case 1:

                System.out.println("What do you want want to change the member's name to?");
                String newName = scanner.nextLine();

                for (Member s : members) {
                    if (memberID == s.getId()) {
                        s.setName(newName);
                        break;
                    }
                }

                saveAllData();
                break;

            case 2:

                int newAge;

                do {

                    System.out.println("What do you want want to change the member's age to?");
                    newAge = HelpMethods.validateInt(scanner);

                    if (newAge < 0) {

                        System.out.println("Invalid input. Member's age can't be negative");
                    }

                } while (newAge < 0);

                for (Member s : members) {
                    if (memberID == s.getId()) {
                        s.setAge(newAge);
                        break;
                    }
                }

                saveAllData();
                break;

            case 3:

                System.out.println("What do you want want to change the member's address to?");
                String newAddress = scanner.nextLine();

                for (Member s : members) {
                    if (memberID == s.getId()) {
                        s.setAddress(newAddress);
                        break;
                    }
                }

                saveAllData();
                break;

            case 4:

                System.out.println("What do you want want to change the member's payment status to?");

                for (Member member : members) {
                    if (memberID == member.getId()) {

                        System.out.println(memberID + "'s payment status: " + member.getHasPaid());

                        System.out.println("Do you want to change the payment status?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");

                        boolean paymentStatus = HelpMethods.validateBoolean(scanner);

                        if (paymentStatus) {

                            if (member.getHasPaid()) {
                                member.setHasPaid(false);

                            } else if (!member.getHasPaid()) {
                                member.setHasPaid(true);
                            }
                        }
                    }

                }
                saveAllData();
                break;

            case 5:

                System.out.println("What do you want want to change the member's membership status to?");

                for (Member member : members) {
                    if (memberID == member.getId()) {

                        System.out.println(memberID + "'s payment status: " + member.getActive());

                        System.out.println("Do you want to change the membership status?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");

                        boolean membershipStatus = HelpMethods.validateBoolean(scanner);

                        if (membershipStatus) {

                            if (member.getActive()) {
                                member.setActive(false);

                            } else if (!member.getActive()) {
                                member.setActive(true);
                            }
                        }
                    }

                }
                saveAllData();
                break;

            default:
                System.out.println("Input does not correspond with an attribute.");

        }

    }

    public static void deleteMember(Scanner scanner) {

        boolean memberFound = false;

        System.out.println("Please enter the ID of the member you want to delete.");
        int memberID = HelpMethods.validateInt(scanner);

        System.out.println("Please enter the ID of the member you want to delete.");

        for (Member s : members) {

            if (s.getId() == memberID) {
                System.out.println(s.getName() + " was deleted.");
                members.remove(s);
                memberFound = true;
                saveAllData();
                break;
            }
        }
        if(!memberFound) {
            System.out.println("No member found with that ID");
        }

    }

    public static void saveAllData() {
        try {
            fileHandler.updateMembers(members);
        } catch (FileOperationException e) {
            System.err.println("Fejl ved gem af medlemsdata: " + e.getMessage());
        }
    }

    public static void loadAllData() {
        try {
            members = fileHandler.loadMembers();
        } catch (FileOperationException e) {
            System.err.println("Fejl ved indlæsning af medlemsdata: " + e.getMessage());
            members = new ArrayList<>();
        }
    }

}
