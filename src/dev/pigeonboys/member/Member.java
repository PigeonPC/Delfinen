package dev.pigeonboys.member;

import java.util.List;

public class Member implements Pay{

    //15 min - Olga
    private int id;
    private String name;
    private int age;
    private String address;
    private final Enum<MembershipTypes> membershipType;
    private boolean hasPaid;
    private boolean active;

    public Member(int id, String name, int age, String address, boolean hasPaid, boolean active){
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.hasPaid = hasPaid;
        this.membershipType = assignMembershipTypes(age);
        this.active = active;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

    public Enum<MembershipTypes> getMembershipType() {
        return membershipType;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Member: \n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Has paid: ").append(hasPaid).append("\n");
        return sb.toString();
    }

    public Enum<MembershipTypes> assignMembershipTypes(int age) {

        if(age < 18) {
            return MembershipTypes.JUNIOR;
        }
        else if(age < 60) {
            return MembershipTypes.SENIOR;
        }
        else {
            return MembershipTypes.SUPERSENIOR;
        }

    }

    public static double calculateTotalFee(List<Member> members) {

        double totalFee = 0;

        for (Member member : members) {

            Enum<MembershipTypes> fee = member.getMembershipType();

            if (member.getActive()) {

                if (fee == MembershipTypes.JUNIOR) {
                    totalFee += 1000;
                } else if (fee == MembershipTypes.SENIOR) {
                    totalFee += 1600;
                } else {
                    totalFee += 1200;
                }

            } else {
                totalFee += 500;
            }

        }

        return totalFee;

    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
