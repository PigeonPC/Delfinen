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

    public int getID() {
        return id;
    }

    public SwimmingDisciplines getDiscipline() {
        return discipline;
    }


    public String getDate() {
        return date;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

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

        sb.append(date);

        return sb.toString();

    }


}
