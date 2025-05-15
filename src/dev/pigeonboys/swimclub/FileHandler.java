package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler {
    private static final String FILE_PATH = "members.txt";

    private static final Pattern MEMBER_PATTERN = Pattern.compile("(\\d+)\\|([^|]+)\\|(\\d+)\\|([^|]+)\\|(true|false)\\|(true|false)");


    public void saveMember(Member member) {

        try(FileWriter fw = new FileWriter(FILE_PATH, true))
        {
            String memberData = String.format("%d|%s|%d|%s|%b|%b%n",
                    member.getId(),
                    member.getName(),
                    member.getAge(),
                    member.getAddress(),
                    member.getHasPaid(),
                    member.getActive());
            fw.write(memberData);

        } catch (IOException e) {
            System.err.println("Error writing to file:" + e.getMessage());
        }
    }

    public List<Member> loadMembers() {
        List<Member> memberList = new ArrayList<>();
        File file = new File(FILE_PATH);

        if(!file.exists()) {
            System.out.println("File not found");
            return memberList;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = reader.readLine()) != null) {
                Matcher matcher = MEMBER_PATTERN.matcher(line);

                if (matcher.matches()) {
                    int id = Integer.parseInt(matcher.group(1));
                    String name = matcher.group(2);
                    int age = Integer.parseInt(matcher.group(3));
                    String address = matcher.group(4);
                    boolean hasPaid = Boolean.parseBoolean(matcher.group(5));
                    boolean active = Boolean.parseBoolean(matcher.group(6));

                    Member member = new Member(id, name, age, address, hasPaid, active);
                    memberList.add(member);
                } else {
                    System.err.println("Invalid member format found: " + line);
                }
            }
            //System.out.println("Loaded " + memberList.size() + " members from file.");
            return memberList;
        } catch(IOException e) {
            System.err.println("Error reading members file: " + e.getMessage());
            return memberList;
        }
    }

    public void updateMembers(List<Member> members) {
        File tempFile = new File(FILE_PATH + ".tmp");

        try (FileWriter fw = new FileWriter(tempFile);
             BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            for (Member member : members) {
                String memberData = String.format("%d|%s|%d|%s|%b|%b%n",
                        member.getId(),
                        member.getName(),
                        member.getAge(),
                        member.getAddress(),
                        member.getHasPaid(),
                        member.getActive());
                fw.write(memberData);
            }

            fw.close();
            reader.close();

            File originalFile = new File(FILE_PATH);
            if (!originalFile.delete()) {
                System.err.println("Could not delete original file");
            }

            if (!tempFile.renameTo(originalFile)) {
                System.err.println("Could not rename temp file");
            }

            System.out.println("Members file updated successfully.");

        } catch (IOException e) {
            System.err.println("Error updating members file: " + e.getMessage());
        }
    }
}
