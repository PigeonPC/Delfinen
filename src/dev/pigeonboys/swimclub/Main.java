package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.MemberManager;

import java.util.Scanner;

public class Main {
    MemberManager mm;
    Scanner scanner;
    UI ui;

    public Main() {
        mm = new MemberManager();
        scanner = new Scanner(System.in);
        ui = new UI();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        ui.getUserInput(scanner);

    }



}
