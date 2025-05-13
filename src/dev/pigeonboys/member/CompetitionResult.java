package dev.pigeonboys.member;

public class CompetitionResult {

    private int id;
    private double time;
    private SwimmingDisciplines discipline;
    private String competitionName;
    private String date;

    public CompetitionResult(int id, double time, SwimmingDisciplines discipline, String competitionName, String date) {
        this.id = id;
        this.time = time;
        this.discipline = discipline;
        this.competitionName = competitionName;
        this.date = date;
    }

    public int getID() {
        return id;
    }

    public SwimmingDisciplines getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getDate() {
        return date;
    }
}
