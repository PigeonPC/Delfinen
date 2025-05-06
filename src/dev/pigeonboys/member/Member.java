package dev.pigeonboys.member;

public class Member {

    //15 min - Olga
    private int id;
    private String name;
    private int age;
    private String address;
    private boolean hasPaid;

    public Member(int id, String name, int age, String address, boolean hasPaid){
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.hasPaid = hasPaid;
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
}
