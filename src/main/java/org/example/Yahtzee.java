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

    public static int FourOfAKind(int _1, int _2, int d3, int d4, int d5) {
        int[] tallies;
        tallies = new int[6];
        tallies[_1 - 1]++;
        tallies[_2 - 1]++;
        tallies[d3 - 1]++;
        tallies[d4 - 1]++;
        tallies[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] == 4)
                return (i + 1) * 4;
        return 0;
    }

    public static int ThreeOfAKind(int d1, int d2, int d3, int d4, int d5) {
        int[] t;
        t = new int[6];
        t[d1 - 1]++;
        t[d2 - 1]++;
        t[d3 - 1]++;
        t[d4 - 1]++;
        t[d5 - 1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] == 3)
                return (i + 1) * 3;
        return 0;
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

    public static int FullHouse(int d1, int d2, int d3, int d4, int d5) {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[d1 - 1] += 1;
        tallies[d2 - 1] += 1;
        tallies[d3 - 1] += 1;
        tallies[d4 - 1] += 1;
        tallies[d5 - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
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
        for (int face = 6; face >= 1; face--) {
            int finalFace = face;
            int amountOfFaces = (int) Arrays.stream(dice)
                    .filter(number -> number == finalFace)
                    .count();
            if (amountOfFaces == 2) return 2 * face;
        }
        return 0;
    }

    public int scoreTwoPairs() {
        List<Integer> scoresOfAllPairs = getScoresOfAllPairs();
        if(scoresOfAllPairs.size() == 2) return scoresOfAllPairs.stream().mapToInt(Integer::valueOf).sum();
        return 0;
    }

    private List<Integer> getScoresOfAllPairs() {
        List<Integer> pairs = new ArrayList<>();
        for (int face = 6; face >= 1; face--) {
            int finalFace = face;
            int amountOfFaces = (int) Arrays.stream(dice)
                    .filter(number -> number == finalFace)
                    .count();
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
