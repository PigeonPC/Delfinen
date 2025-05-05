package dev.pigeonboys.member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberManager {

    ArrayList<Member> members = new ArrayList<>();

    void addNewMember(String name, int age, String address, String memberShipType, boolean hasPaid) {

        Member member = new Member(name, age, address, memberShipType, hasPaid);

        members.add(member);

    }

    void editMember(Scanner scanner) {

        System.out.println("Please enter the ID of the member whose attributes you want to alter.");

        int memberID = scanner.nextInt();

        for(Member s : members) {
            if(memberID = s.getID()) {
                System.out.println(s);
            }
        }

        System.out.println("Which attribute do you want to edit?");
        System.out.println();
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Address");
        System.out.println("4. Payment Status");

        int input = scanner.nextInt();

        switch(input) {

            case 1:

                System.out.println("What do you want want to change the member's name to?");

                String newName = scanner.nextLine();

                for(Member s : members) {
                    if(memberID = s.getID()) {
                        s.setName(newName);
                    }
                }

                System.out.println("Member " + memberID + "'s name was changed to " + newName + ".");

                break;

            case 2:

                System.out.println("What do you want want to change the member's age to?");

                int newAge = scanner.nextInt();

                for(Member s : members) {
                    if(memberID = s.getID()) {
                        s.setAge(newAge);
                    }
                }

                System.out.println("Member " + memberID + "'s age was changed to " + newAge + ".");

                break;

            case 3:

                System.out.println("What do you want want to change the member's address to?");

                int newAddress = scanner.nextInt();

                for(Member s : members) {
                    if(memberID = s.getID()) {
                        s.setAddress(newAddress);
                    }
                }

                System.out.println("Member " + memberID + "'s age was changed to " + newAddress + ".");

                break;

            case 4:

                System.out.println("What do you want want to change the member's payment status to?");

                for(Member s : members) {
                    if (memberID = s.getID()) {

                        System.out.println(memberID + "'s payment status is currently set to " + s.getHasPaid());

                        System.out.println("Do you want to change the payment status?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");

                        int status = scanner.nextInt();

                        if (status == 1) {

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
            default:
                System.out.println("Input does not correspond with an attribute.");

        }

    }

    void deleteMember(Scanner scanner) {

        System.out.println("Please enter the ID of the member you want to delete.");

        int ID = scanner.nextInt();

        for(Member s : members) {
            if(s.getID() == ID) {
                System.out.println(s.getName() + " was deleted.");
                members.remove(s);
            }
        }


    }

}
