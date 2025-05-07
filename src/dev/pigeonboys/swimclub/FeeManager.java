package dev.pigeonboys.swimclub;

import dev.pigeonboys.member.Member;

import java.util.ArrayList;
import java.util.List;

public class FeeManager {
    private static final String PAYMENT_FILE = "payments.txt";

    private final FileHandler fileHandler;

    private static final double JUNIOR_FEE = 500.0;
    private static final double SENIOR_FEE = 1000.0;

    public FeeManager(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public double calculateFee(Member member) {
        if (!member.getActive()) {
            return 0.0;
        }

        double baseFee = member.getAge() < 18 ? JUNIOR_FEE : SENIOR_FEE;
        return baseFee;
    }

    public List<Member> getUnpaidMembers() {
        List<Member> allMembers = fileHandler.loadMembers();
        List<Member> unpaidMembers = new ArrayList<>();

        for (Member member : allMembers) {
            if (member.getActive() && !member.getHasPaid()) {
                unpaidMembers.add(member);
            }
        }

        return unpaidMembers;
    }

}
