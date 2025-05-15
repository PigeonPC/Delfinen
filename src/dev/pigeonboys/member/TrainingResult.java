package dev.pigeonboys.member;

public class TrainingResult {

    private int id;
    private double time;
    private SwimmingDisciplines discipline;
    private String date;

    public TrainingResult(int id, double time, SwimmingDisciplines discipline, String date) {
        this.id = id;
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }

    public double getTime() {
        return time;
    }
}
