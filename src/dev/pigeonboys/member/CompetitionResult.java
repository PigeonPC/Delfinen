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

    public String toString() {

        StringBuilder sb = new StringBuilder();

        Trainer traener = new Trainer("");
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(0, "", 0, "", false, false, traener);

            sb.append(id);

            while (sb.length() < 10) {
                sb.append(" ");
            }

            sb.append(discipline);

            while (sb.length() < 30) {
                sb.append(" ");
            }

            sb.append(time);

            while (sb.length() < 40) {
                sb.append(" ");
            }

            sb.append(competitionName);

            while (sb.length() < 60) {
                sb.append(" ");
            }

            sb.append(date);

            return sb.toString();

        }

}
