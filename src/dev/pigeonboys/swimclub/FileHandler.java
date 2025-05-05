package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.Member;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String FILE_PATH = "members.txt";

    public void saveMember(Member member) {

        try(FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter writer = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(writer)) {

            out.println(member.getId() + "|" + member.getName() + "|"
                    + member.getAge() + "|" + member.getAddress() + "|" + member.getHasPaid());
        } catch(IOException e) {
            System.err.println("Error adding member" + e.getMessage());
        }
    }

    public ArrayList<Member> loadMembers() {
        ArrayList<Member> loadedMembers = new ArrayList<>();

        File file = new File(FILE_PATH);
        if(!file.exists()) {
            System.out.println("No saved members found");
            return loadedMembers;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("|");
                if(parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String address = parts[3];
                        boolean hasPaid = Boolean.parseBoolean(parts[4]);

                        Member member = new Member(id, name, age, address, hasPaid);

                        loadedMembers.add(member);
                    } catch(NumberFormatException e) {
                        System.err.println("Invalid format" + e.getMessage());
                    }
                }
            }
        } catch(IOException e) {
            System.err.println("Error loading members from file" + e.getMessage());
        }
        return loadedMembers;
    }
}
