package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.Member;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {
    private static final String FILE_PATH = "members.txt";

    public void saveMember(Member member) {

        try(FileWriter fw = new FileWriter(FILE_PATH, true));
        BufferedWriter writer = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(writer)) {

            out.println(member.getId() + "|" + member.getName() + "|" +
                    member.getAddress());
        } catch(IOException e) {
            System.err.println("Error adding member" + e.getMessage());
        }

    }


}
