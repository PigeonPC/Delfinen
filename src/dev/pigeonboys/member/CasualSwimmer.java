package dev.pigeonboys.member;

public class CasualSwimmer extends Member{

    Trainer trainer;

    public CasualSwimmer(int id, String name, int age, String address, boolean hasPaid, boolean active, Trainer trainer) {
        super(id, name, age, address, hasPaid, active);
        this.trainer = trainer;
    }
}
