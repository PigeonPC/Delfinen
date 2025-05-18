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

    private List<CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public static void sortCompetitionTime(List<CompetitionResult> competitionResults) {

        competitionResults.sort(Comparator.comparingDouble(CompetitionResult::getTime));

    }

    public static void sortTrainingTime(List<TrainingResult> trainingResults) {

        trainingResults.sort(Comparator.comparingDouble(TrainingResult::getTime));

    }

    public CompetitiveSwimmer(int id, String name, int age, String address, boolean hasPaid, boolean active, Trainer trainer) {

        super(id, name, age, address, hasPaid, active);
        this.trainer = trainer;
        competitionResults = loadCompetitionResults();
        trainingResults = loadTrainingResults();

    }

    public void addCompetitionResult(Scanner scanner) {


        int memberID;
        boolean memberExists;

        do {

            System.out.println("Please enter ID of swimmer.");

            memberID = scanner.nextInt();
            scanner.nextLine();

            memberExists = false;

            for (Member member : MemberManager.getMembers()) {
                if (member.getId() == memberID) {
                    memberExists = true;
                    break;
                }
            }

        } while ((!memberExists));

        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Brystsvømning");
        System.out.println();
        System.out.println("Pick a discipline");

        int pickDisciplin = scanner.nextInt();
        scanner.nextLine();
        SwimmingDisciplines discipline = null;

        switch (pickDisciplin) {
            case 1:
                discipline = SwimmingDisciplines.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDisciplines.CRAWL;
                break;
            case 3:
                discipline = SwimmingDisciplines.RYGCRAWL;
                break;
            case 4:
                discipline = SwimmingDisciplines.BRYSTSVOEMNING;
                break;
            default:
                System.out.println("Not a disciplin.");
        }

        System.out.println("Please enter the date.");
        String date = scanner.nextLine();

        System.out.println("Please enter competition.");
        String competitionName = scanner.nextLine();

        System.out.println("Please enter time.");
        double time = scanner.nextDouble();
        scanner.nextLine();

        CompetitionResult competitionResult = new CompetitionResult(memberID, time, discipline, competitionName, date);

        boolean alreadyExists = false;
        for (int i = 0; i < competitionResults.size(); i++) {

            if (competitionResults.get(i).getID() == memberID) {
                if (competitionResults.get(i).getDiscipline() == discipline) {
                    if (competitionResults.get(i).getTime() > time) {

                        competitionResults.set(i, competitionResult);
                        System.out.println("New Best Time in " + discipline + ". Old result was overwritten.");

                    } else {
                        System.out.println("Result was not better than the best result.");
                    }
                    alreadyExists = true;
                    break;
                }

            }

        }
        if (!alreadyExists) {
            competitionResults.add(competitionResult);
            System.out.println("There was no previous Best Time.");
            System.out.println("New Best Time in " + discipline);
        }

    }

    public void updateCompetitiveResults() {

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

    public List<CompetitionResult> loadCompetitionResults() {
        List<CompetitionResult> competitionResults = new ArrayList<>();
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
            //System.out.println("Loaded " + competitionResults.size() + " competition results from file.");
            return competitionResults;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return competitionResults;
        }
    }

    public void viewCompetitiveResults(Scanner scanner) {

        MemberManager mm = new MemberManager();
        Trainer trainer = new Trainer("");
        CompetitiveSwimmer cs = new CompetitiveSwimmer(1, "", 2, "", false, false, trainer);

        sortCompetitionTime(competitionResults);

        int counter = 0;
        int spaceCounter = 0;

        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println();
        System.out.println("Pick an age group");

        int ageGroupChoice = scanner.nextInt();
        scanner.nextLine();

        MembershipTypes ageGroup1 = null;
        MembershipTypes ageGroup2 = null;

        switch (ageGroupChoice) {
            case 1:
                ageGroup1 = MembershipTypes.JUNIOR;
                break;
            case 2:
                ageGroup1 = MembershipTypes.SENIOR;
                ageGroup2 = MembershipTypes.SUPERSENIOR;
                break;
            default:
                System.out.println("Not an age group.");


        }

        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Brystsvømning");
        System.out.println();
        System.out.println("Pick a discipline");

        int disciplineChoice = scanner.nextInt();
        scanner.nextLine();

        SwimmingDisciplines discipline = null;

        switch (disciplineChoice) {
            case 1:
                discipline = SwimmingDisciplines.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDisciplines.CRAWL;
                break;
            case 3:
                discipline = SwimmingDisciplines.RYGCRAWL;
                break;
            case 4:
                discipline = SwimmingDisciplines.BRYSTSVOEMNING;
                break;
            default:
                System.out.println("Not a discipline.");

        }

        System.out.println("Name                ID        Discipline          Time      Competition         Date");

        for (CompetitionResult cr : competitionResults) {

            if (cr.getDiscipline() == discipline) {
                for (Member s : MemberManager.getMembers()) {
                    if (cr.getID() == s.getId() && (s.getMembershipType() == ageGroup1 || s.getMembershipType() == ageGroup2)) {
                        System.out.print(s.getName());
                        while (s.getName().length() + spaceCounter < 20) {
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

    public void addTrainingResult(Scanner scanner) {

        int memberID;
        boolean memberExists;

        do {

            System.out.println("Please enter ID of swimmer.");

            memberID = scanner.nextInt();
            scanner.nextLine();

            memberExists = false;

            for (Member member : MemberManager.getMembers()) {
                if (member.getId() == memberID) {
                    memberExists = true;
                    break;
                }
            }

        } while ((!memberExists));

        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Brystsvømning");
        System.out.println();
        System.out.println("Pick a discipline");

        int pickDisciplin = scanner.nextInt();
        scanner.nextLine();
        SwimmingDisciplines discipline = null;

        switch (pickDisciplin) {
            case 1:
                discipline = SwimmingDisciplines.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDisciplines.CRAWL;
                break;
            case 3:
                discipline = SwimmingDisciplines.RYGCRAWL;
                break;
            case 4:
                discipline = SwimmingDisciplines.BRYSTSVOEMNING;
                break;
            default:
                System.out.println("Not a disciplin.");
        }

        System.out.println("Please enter the date.");
        String date = scanner.nextLine();

        System.out.println("Please enter time.");
        double time = scanner.nextDouble();
        scanner.nextLine();

        TrainingResult trainingResult = new TrainingResult(memberID, time, discipline, date);

        boolean alreadyExists = false;
        for (int i = 0; i < trainingResults.size(); i++) {

            if (trainingResults.get(i).getID() == memberID) {
                if (trainingResults.get(i).getDiscipline() == discipline) {
                    if (trainingResults.get(i).getTime() > time) {

                        trainingResults.set(i, trainingResult);
                        System.out.println("New Best Time in " + discipline + ". Old result was overwritten.");

                    } else {
                        System.out.println("Result was not better than the best result.");
                    }
                    alreadyExists = true;
                    break;
                }

            }

        }
        if (!alreadyExists) {
            trainingResults.add(trainingResult);
            System.out.println("There was no previous Best Time.");
            System.out.println("New Best Time in " + discipline);
        }

    }

    public void updateTrainingResults() {

        try {
            FileWriter myWriter = new FileWriter("trainingResults.txt");

            for (TrainingResult result : trainingResults) {

                String memberData = String.format("%d|%s|%s|%s%n",

                        result.getID(),
                        result.getDiscipline(),
                        result.getTime(),
                        result.getDate());

                myWriter.write(memberData);

            }

            myWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<TrainingResult> loadTrainingResults() {

        List<TrainingResult> trainingResults = new ArrayList<>();

        try {
            File results = new File("trainingResults.txt");
            Scanner myReader = new Scanner(results);


            while (myReader.hasNextLine()) {

                String memberData = myReader.nextLine();

                String[] split = memberData.split("\\|");

                int id = Integer.parseInt(split[0].trim());
                SwimmingDisciplines disciplin = SwimmingDisciplines.valueOf(split[1].trim());
                double time = Double.parseDouble(split[2].trim());
                String date = split[3].trim();

                trainingResults.add(new TrainingResult(id, time, disciplin, date));
            }
            //System.out.println("Loaded " + competitionResults.size() + " competition results from file.");
            return trainingResults;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return trainingResults;
        }
    }

    public void viewTrainingResults(Scanner scanner) {

        MemberManager mm = new MemberManager();
        Trainer trainer = new Trainer("");
        CompetitiveSwimmer cs = new CompetitiveSwimmer(1, "", 2, "", false, false, trainer);

        sortTrainingTime(trainingResults);

        int counter = 0;
        int spaceCounter = 0;

        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println();
        System.out.println("Pick an age group");

        int ageGroupChoice = scanner.nextInt();
        scanner.nextLine();

        MembershipTypes ageGroup1 = null;
        MembershipTypes ageGroup2 = null;

        switch (ageGroupChoice) {
            case 1:
                ageGroup1 = MembershipTypes.JUNIOR;
                break;
            case 2:
                ageGroup1 = MembershipTypes.SENIOR;
                ageGroup2 = MembershipTypes.SUPERSENIOR;
                break;
            default:
                System.out.println("Not an age group.");


        }

        System.out.println("1. Butterfly");
        System.out.println("2. Crawl");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Brystsvømning");
        System.out.println();
        System.out.println("Pick a discipline");

        int disciplineChoice = scanner.nextInt();
        scanner.nextLine();

        SwimmingDisciplines discipline = null;

        switch (disciplineChoice) {
            case 1:
                discipline = SwimmingDisciplines.BUTTERFLY;
                break;
            case 2:
                discipline = SwimmingDisciplines.CRAWL;
                break;
            case 3:
                discipline = SwimmingDisciplines.RYGCRAWL;
                break;
            case 4:
                discipline = SwimmingDisciplines.BRYSTSVOEMNING;
                break;
            default:
                System.out.println("Not a discipline.");

        }

        System.out.println("Name                ID        Discipline          Time      Competition         Date");

        for (TrainingResult tr : trainingResults) {

            if (tr.getDiscipline() == discipline) {
                for (Member member : MemberManager.getMembers()) {
                    if (tr.getID() == member.getId() && (member.getMembershipType() == ageGroup1 || member.getMembershipType() == ageGroup2)) {
                        System.out.print(member.getName());
                        while (member.getName().length() + spaceCounter < 20) {
                            System.out.print(" ");
                            spaceCounter++;
                        }
                        System.out.println(tr);
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
