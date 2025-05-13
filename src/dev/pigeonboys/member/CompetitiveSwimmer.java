package dev.pigeonboys.member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CompetitiveSwimmer extends Member {


    Trainer trainer;
    List<CompetitionResult> competitionResults;
    List<TrainingResult> trainingResults;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Trainer traener = new Trainer("");
        CompetitiveSwimmer swimmer = new CompetitiveSwimmer(0, "", 0, "", false, false, traener);


        swimmer.loadResults(swimmer.getCompetitionResults());
        sortTime(swimmer);
        //swimmer.updateCompetitiveResults();
        swimmer.viewCompetitiveResults(scanner);

    }

    public static void sortTime(CompetitiveSwimmer swimmer) {

        swimmer.competitionResults.sort(Comparator.comparingDouble(CompetitionResult::getTime));

    }

    private List<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public CompetitiveSwimmer(int id, String name, int age, String address, boolean hasPaid, boolean active, Trainer trainer) {

        super(id, name, age, address, hasPaid, active);
        this.trainer = trainer;
        competitionResults = new ArrayList<>();
        trainingResults = new ArrayList<>();

        //Delete
        //competitionResults.add(new CompetitionResult(1, 2.23, SwimmingDisciplines.CRAWL, "C", "22.12.2000"));
        //competitionResults.add(new CompetitionResult(1, 2.23, SwimmingDisciplines.CRAWL, "C", "22.12.2000"));

    }

    public void addCompetitionResult(Scanner scanner) {

        System.out.println("Please enter ID of swimmer.");

        int ID = scanner.nextInt();
        scanner.nextLine();

        //System.out.println("Please enter the discipline of swimmer.");
        SwimmingDisciplines discipline = SwimmingDisciplines.CRAWL;

        System.out.println("Please enter the date.");
        String date = scanner.nextLine();

        System.out.println("Please enter competition.");
        String competitionName = scanner.nextLine();

        System.out.println("Please enter time.");
        double time = scanner.nextDouble();
        scanner.nextLine();

        CompetitionResult competitionResult = new CompetitionResult(ID, time, discipline, competitionName, date);

        for (CompetitionResult result : competitionResults) {
            if (result.getID() == ID) {
                if (result.getDiscipline() == discipline) {
                    if (result.getTime() > time) {
                        competitionResults.remove(result);
                        competitionResults.add(competitionResult);
                        System.out.println("New Best Time in " + discipline);
                    }
                } else if (result.getDiscipline() == null) {
                    competitionResults.add(competitionResult);
                    System.out.println("New Best Time in " + discipline);
                }
            }
        }

    }

    public void updateCompetitiveResults() {

        //Create File. Probably to be deleted later.
        try {

            File competitiveResults = new File("competitiveResults.txt");
            if (competitiveResults.createNewFile()) {
                System.out.println("New file was created.");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("competitiveResults.txt");

            for (CompetitionResult result : competitionResults) {

                String memberData = String.format("%d|%s|%s|%s|%s%n",

                        result.getID(),
                        result.getDiscipline(),
                        result.getTime(),
                        result.getCompetitionName(),
                        result.getDate());

                myWriter.write(memberData);

            }

            myWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadResults(List<CompetitionResult> competitiveResults) {

        try {
            File results = new File("competitiveResults.txt");
            Scanner myReader = new Scanner(results);


            while (myReader.hasNextLine()) {

                String memberData = myReader.nextLine();

                String[] split = memberData.split("\\|");

                int id = Integer.parseInt(split[0].trim());
                SwimmingDisciplines disciplin = SwimmingDisciplines.valueOf(split[1].trim());
                double time = Double.parseDouble(split[2].trim());
                String competitionName = split[3].trim();
                String date = split[4].trim();

                competitionResults.add(new CompetitionResult(id, time, disciplin, competitionName, date));
            }

        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void viewCompetitiveResults(Scanner scanner) {

        //Mangler comparator.

        MemberManager mm = new MemberManager();

        int counter = 0;
        int spaceCounter = 0;

        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Brystsvømning");

        System.out.println("Pick a discipline");

        int disciplineChoice = scanner.nextInt();
        scanner.nextLine();

        SwimmingDisciplines discipline = null;

        switch(disciplineChoice) {
            case 1:
                discipline = SwimmingDisciplines.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDisciplines.CRAWL;
                break;
            case 3:
                discipline = SwimmingDisciplines.RYGCRAWL;
            case 4:
                discipline = SwimmingDisciplines.BRYSTSVOEMNING;
                break;
            default:
                System.out.println("Not a discipline.");

        }

        System.out.println("Name                ID        Discipline          Time      Competition         Date");

        for(CompetitionResult cr : competitionResults) {

            if(cr.getDiscipline() == discipline) {

                for(Member s : MemberManager.getMembers()) {
                    if(cr.getID() == s.getId()) {
                        System.out.print(s.getName());
                        while(s.getName().length() + spaceCounter < 20) {
                            System.out.print(" ");
                            spaceCounter++;
                        }
                        System.out.println(cr);
                        spaceCounter = 0;
                        counter++;

                        }

                }

                if (counter == 5) {
                    break;
                }

            }

        }

    }


}
