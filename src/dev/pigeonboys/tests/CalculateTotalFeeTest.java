package dev.pigeonboys.tests;

import dev.pigeonboys.member.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class CalculateTotalFeeTest {

    @Test

    public void calculateTotalFeeTest() {

        Member member1 = new Member(1, "Sofus", 24, "Guldbergsgade 93", true, true);
        Member member2 = new Member(2, "Jonathan", 12, "Guldbergsgade 93", true, false);
        Member member3 = new Member(3, "Olga", 69, "Guldbergsgade 93", true, true);

        List<Member> members = new ArrayList<>();

        members.add(member1);
        members.add(member2);
        members.add(member3);

        //Total Fee burde således være 1600 + 500 + 1200 = 3800

        assertEquals(3800, Member.calculateTotalFee(members));

    }

}
