package dev.pigeonboys.member;

import dev.pigeonboys.swimclub.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
    static FileHandler fileHandler;
    static List<Member> members = new ArrayList<>();

    public MemberManager() {
        fileHandler = new FileHandler();
        members = fileHandler.loadMembers();
    }

    public static List<Member> getMembers() {
        return members;
    }


    public static void addNewMember(Scanner scanner) {

        boolean proceed = false;


        int ID = 0;
        String name = "";
        int age = 0;
        String address = "";

        boolean hasPaid = true;
        boolean isActive = true;

        while(!proceed) {

            try {
                System.out.println("Please enter the ID of the new member.");
                ID = scanner.nextInt();
                scanner.nextLine();

                if(ID > 0) {
                    proceed = true;
                }
                else{
                    System.out.println("Invalid input");
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }

        }

        proceed = false;

        while(!proceed) {

            try {

                System.out.println("Please enter the name of the new member.");
                name = scanner.nextLine();
                proceed = true;

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }

        proceed = false;

        while(!proceed) {

            try {

                System.out.println("Please enter the age of the new member.");
                age = scanner.nextInt();
                scanner.nextLine();

                if(age > 0 && age < 100) {
                    proceed = true;
                }
                else{
                    System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }

        }

        proceed = false;

        while(!proceed) {

            try {
                System.out.println("Please enter the address of the new member.");
                address = scanner.nextLine();
                proceed = true;

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }

        }

        proceed = false;

        int paymentStatus = 0;

        while(!proceed) {
            System.out.println("Please choose a payment status.");
            System.out.println("1. Paid");
            System.out.println("2. Not paid");

            try {

                paymentStatus = scanner.nextInt();
                scanner.nextLine();


                if(paymentStatus == 1 || paymentStatus == 2) {
                    proceed = true;

                    if(paymentStatus == 1) {
                        hasPaid = false;
                    }

                }
                else{
                    System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

        }

        int membershipStatus = 0;

        proceed = false;

        while(!proceed) {
            System.out.println("Please choose membership status.");
            System.out.println("1. Active");
            System.out.println("2. Passive");

            try {

                membershipStatus = scanner.nextInt();
                scanner.nextLine();

                if(membershipStatus == 1 || membershipStatus == 2) {
                    proceed = true;

                    if (membershipStatus == 2) {
                        isActive = false;
                    }
                }
                else{
                    System.out.println("Invalid input.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

        }

        Member member = new Member(ID, name, age, address, hasPaid, isActive);

        members.add(member);
        fileHandler.saveMember(member);

    }

    public static void editMember(Scanner scanner) {

        boolean proceed = false;

        int memberID = 0;

        while (!proceed) {

            System.out.println("Please enter the ID of the member whose attributes you want to alter.");

            try {

                memberID = scanner.nextInt();
                scanner.nextLine();
                proceed = true;

            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

        }

        proceed = false;

        for(Member s : members) {
            if(memberID == s.getId()) {
                System.out.println(s);
                break;

            }
        }

        int input = 0;

        while(!proceed) {

            System.out.println("Which attribute do you want to edit?");
            System.out.println();
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Address");
            System.out.println("4. Payment Status");
            System.out.println("5. Membership Status");

            try {

                input = scanner.nextInt();
                scanner.nextLine();
                proceed = true;

            } catch (Exception e) {

                System.out.println("Invalid input");
                scanner.nextLine();

            }

        }

        proceed = false;

        switch(input) {

            case 1:

                String newName = "";

                while(!proceed) {

                    System.out.println("What do you want want to change the member's name to?");

                    try {

                        newName = scanner.nextLine();
                        proceed = true;

                    } catch (Exception e) {

                        System.out.println("Invalid input.");

                    }

                }

                for(Member s : members) {
                    if(memberID == s.getId()) {
                        s.setName(newName);
                        break;
                    }
                }

                System.out.println("Member " + memberID + "'s name was changed to " + newName + ".");

                break;

            case 2:

                int newAge = 0;

                while(!proceed) {

                System.out.println("What do you want want to change the member's age to?");

                try {

                    newAge = scanner.nextInt();
                    scanner.nextLine();

                    if(newAge > 0 && newAge < 100) {
                        proceed = true;
                    }
                    else{
                        System.out.println("Invalid input.");
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    scanner.nextLine();
                }

                }

                for(Member s : members) {
                    if(memberID == s.getId()) {
                        s.setAge(newAge);
                        break;
                    }
                }

                System.out.println("Member " + memberID + "'s age was changed to " + newAge + ".");

                break;

            case 3:

                String newAddress = "";

                while(!proceed) {

                    System.out.println("What do you want want to change the member's address to?");

                    try {

                        newAddress = scanner.nextLine();
                        proceed = true;

                    } catch (Exception e) {

                        System.out.println("Invalid input.");

                    }

                }

                for(Member s : members) {
                    if(memberID == s.getId()) {
                        s.setAddress(newAddress);
                        break;
                    }
                }

                System.out.println("Member " + memberID + "'s address was changed to " + newAddress + ".");

                break;

            case 4:

                int paymentStatus = 0;

                System.out.println("What do you want want to change the member's payment status to?");

                for(Member s : members) {
                    if (memberID == s.getId()) {

                        System.out.println(memberID + "'s payment status is currently set to " + s.getHasPaid());

                        System.out.println("Do you want to change the payment status?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");

                        while(!proceed) {

                            try {

                                paymentStatus = scanner.nextInt();
                                scanner.nextLine();

                                if(paymentStatus == 1 || paymentStatus == 2) {
                                    proceed = true;
                                }
                                else{
                                    System.out.println("Invalid input.");
                                    scanner.nextLine();
                                }

                            } catch (Exception e) {
                                System.out.println();
                                scanner.nextLine();
                            }

                        }

                        if (paymentStatus == 1) {

                            if (s.getHasPaid()) {
                                s.setHasPaid(false);
                                System.out.println("Member " + memberID + "'s payments status was changed to " + s.getHasPaid() + ".");
                            } else if (!s.getHasPaid()) {
                                s.setHasPaid(true);
                                System.out.println("Member " + memberID + "'s payments status was changed to " + s.getHasPaid() + ".");
                            }
                        }
                    }

                }

                break;

            case 5:

            int membershipStatus = 0;

            System.out.println("What do you want want to change the membership status to?");

            for(Member s : members) {
                if (memberID == s.getId()) {

                    System.out.println(memberID + "'s membership status is currently set to " + s.getActive());

                    System.out.println("Do you want to change the payment status?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    while(!proceed) {

                        try {

                            membershipStatus = scanner.nextInt();
                            scanner.nextLine();

                            if(membershipStatus == 1 || membershipStatus == 2) {
                                proceed = true;
                            }
                            else{
                                System.out.println("Invalid input.");
                                scanner.nextLine();
                            }

                        } catch (Exception e) {
                            System.out.println();
                            scanner.nextLine();
                        }

                    }

                    if (membershipStatus == 1) {

                        if (s.getHasPaid()) {
                            s.setActive(false);
                            System.out.println("Member " + memberID + "'s membership status was changed to " + s.getActive() + ".");
                        } else if (!s.getHasPaid()) {
                            s.setActive(true);
                            System.out.println("Member " + memberID + "'s membership status was changed to " + s.getActive() + ".");
                        }
                    }
                }

            }
            fileHandler.updateMembers(members);

            break;
            default:
                System.out.println("Input does not correspond with an attribute.");

        }

    }

    public static void deleteMember(Scanner scanner) {

        boolean proceed = false;

        int ID = 0;

        while(!proceed) {

            System.out.println("Please enter the ID of the member you want to delete.");

            try {
                ID = scanner.nextInt();
                scanner.nextLine();
                proceed = true;

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }

        }

        for(Member s : members) {

            if(s.getId() == ID) {
                System.out.println(s.getName() + " was deleted.");
                members.remove(s);
                break;
            }
        }

    }

}
