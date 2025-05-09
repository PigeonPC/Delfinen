package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.MemberManager;

import java.util.Scanner;

public class Main {
    MemberManager mm;
    Scanner scanner;

    public Main() {
        mm = new MemberManager();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        UI.getUserInput(scanner);

    }

}