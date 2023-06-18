package hr.mlinx.i;

import hr.mlinx.util.Util;

import java.util.Arrays;
import java.util.stream.IntStream;

import static hr.mlinx.util.Util.shuffleInts;

public class LiterallyRandomStuff {

    public static String birthdayParadox(int numOfPeople, int precision) {
        double totalHits = 0;

        for (int i = 0; i < precision; ++i) {
            if (Util.R.ints(numOfPeople, 0, 365).distinct().count() < numOfPeople)
                ++totalHits;
        }

        return String.format("%.3f%% of %d trials had matching birthdays for %d people.",
                totalHits / precision * 100, precision, numOfPeople);
    }

    public static String ballsInBin(int balls, int bins) {
        int chosenBin = Util.R.nextInt(bins);
        long ballHits = Util.R.ints(balls, 0, bins).filter(bin -> bin == chosenBin).count();

        return String.format("Randomly chosen bin #%d/%d has %d/%d balls (expect around balls/bins balls to be in any bin on average).",
                chosenBin + 1, bins, ballHits, balls);
    }

    public static String tossesUntilBinHasBall(int bins) {
        int chosenBin = Util.R.nextInt(bins);
        int tosses = 0;

        while (Util.R.nextInt(bins) != chosenBin) ++tosses;

        return String.format("Randomly chosen bin #%d/%d needed %d tosses to get a ball (expect tosses to equal around num. of bins for any given bin on average).",
                chosenBin + 1, bins, tosses);
    }

    public static String tossesUntilEveryBinHasOneOrMore(int bins) {
        Boolean[] binsToHit = new Boolean[bins];
        Arrays.fill(binsToHit, Boolean.FALSE);
        int tosses = 0;

        while (!Arrays.stream(binsToHit).allMatch(Boolean::booleanValue)) {
            ++tosses;
            binsToHit[Util.R.nextInt(bins)] = Boolean.TRUE;
        }

        return String.format("Took %d tosses for all %d bins to have at least 1 ball (expect around bins*ln(bins) tosses on average).",
                tosses, bins);
    }

    public static String longestStreakOfHeads(int numOfFlips) {
        int currentStreak = 0, longestStreak = 0;

        // heads - 0
        for (int i = 0; i < numOfFlips; ++i) {
            if (Util.R.nextInt(2) == 0) {
                ++currentStreak;
            } else {
                if (currentStreak > longestStreak)
                    longestStreak = currentStreak;
                currentStreak = 0;
            }
        }

        return String.format("Longest streak of heads flipped for %d flips was %d (expect around log2(flips) on average).",
                numOfFlips, longestStreak);
    }

    public static String onlineHiringProblem(int numOfCandidates, int trials) {
        int k = (int) Math.round(numOfCandidates / Math.E);
        int timesHiredBestCandidate = 0, bestScore;
        int[] candidateScores = IntStream.range(0, numOfCandidates).toArray();

        for (int i = 0; i < trials; ++i) {
            bestScore = Integer.MIN_VALUE;
            shuffleInts(candidateScores);

            for (int j = 0; j < k; ++j) {
                if (candidateScores[j] > bestScore)
                    bestScore = candidateScores[j];
            }

            for (int j = k; j < numOfCandidates; ++j) {
                if (candidateScores[j] > bestScore) {
                    if (candidateScores[j] == numOfCandidates - 1)
                        ++timesHiredBestCandidate;

                    break;
                }
            }
        }

        return String.format("Succeeded in hiring the best candidate %f%% of the time (expect around 1/e~36.788%% for k=trialsRun/e on average).",
                timesHiredBestCandidate * 100d / trials);
    }

}
