package dev.pigeonboys.comparators;

import dev.pigeonboys.member.TrainingResult;

import java.util.Comparator;

public class ResultTimeComparator implements Comparator<TrainingResult> {
    @Override
    public int compare(TrainingResult r1, TrainingResult r2) {
        return Double.compare(r1.getTime(), r2.getTime());
    }
}