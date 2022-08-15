package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Yahtzee {

    protected int[] dice;

    public Yahtzee(int d1, int d2, int d3, int d4, int d5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }
    public static int SmallStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int LargeStraight(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
            return 20;
        return 0;
    }

    public int chance() {
        return Arrays.stream(dice).sum();
    }

    public int yahtzee() {
        Set<Integer> set = Arrays.stream(dice).boxed().collect(Collectors.toSet());
        return (set.size() == 1) ? 50 : 0;
    }

    public int scorePair() {
        return contMultipleOccur(2);
    }

    public int threeOfAKind() {
        return contMultipleOccur(3);
    }

    public int fourOfAKind() {
        return contMultipleOccur(4);
    }

    public int scoreTwoPairs() {
        List<Integer> scoresOfAllPairs = getScoresOfAllPairs();
        if(scoresOfAllPairs.size() == 2) return scoresOfAllPairs.stream().mapToInt(Integer::valueOf).sum();
        return 0;
    }

    /**
     *  If the dice are two of a kind and three of a kind, the
     *   player scores the sum of all the dice.
     *   For example, when placed on "full house"
     *   1,1,2,2,2 scores 8 (1+1+2+2+2)
     * @return value of Full house if exist. else 0
     */
    public int fullHouse() {
        int scoreThree = threeOfAKind();
        int scoreTwo = scorePair();
       return (scoreThree != 0 && scoreTwo != 0) ? scoreThree + scoreTwo :0;
    }

    private int contMultipleOccur(int amountToCheck) {
        for (int face = 6; face >= 1; face--) {
            int amountOfFaces = getAmoutOfEqualFaces(face);
            if (amountOfFaces == amountToCheck) return amountToCheck * face;
        }
        return 0;
    }

    private int getAmoutOfEqualFaces(int face) {
        return (int) Arrays.stream(dice)
                .filter(number -> number == face)
                .count();
    }

    private List<Integer> getScoresOfAllPairs() {
        List<Integer> pairs = new ArrayList<>();
        for (int face = 6; face >= 1; face--) {
            int finalFace = face;
            int amountOfFaces = getAmoutOfEqualFaces(finalFace);
            if (amountOfFaces == 2) pairs.add(face*2);
        }

        return pairs;
    }

    public int sumOf(int i) {
        int sum = 0;
        for (int die : dice) {
            if (die == i) sum += i;
        }
        return sum;
    }

}
