package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Yahtzee {

    protected int[] dices;


    public Yahtzee(int d1, int d2, int d3, int d4, int d5) {
        dices = new int[5];
        dices[0] = d1;
        dices[1] = d2;
        dices[2] = d3;
        dices[3] = d4;
        dices[4] = d5;
    }

    /**
     * Small straight:
     *   When placed on "small straight", if the dice read
     *   1,2,3,4,5, the player scores 15 (the sum of all the dice.
     *
     * Large straight:
     *   When placed on "large straight", if the dice read
     *   2,3,4,5,6, the player scores 20 (the sum of all the dice).
     * @return 20 if its a large, 15 if its a small straight. 0 if there is no straight.
     */
    public int straight() {
        Set<Integer> occurred = new HashSet<>();
        for(int dice : dices){
            occurred.add(dice);
        }
       return (occurred.size() == 5) ? smallOrBigStraight(occurred) : 0;
    }

    private int smallOrBigStraight(Set<Integer> occurred) {
        return (occurred.contains(6)) ? 20 :15 ;
    }

    public int chance() {
        return Arrays.stream(dices).sum();
    }

    public int yahtzee() {
        Set<Integer> set = Arrays.stream(dices).boxed().collect(Collectors.toSet());
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
        return (int) Arrays.stream(dices)
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
        for (int die : dices) {
            if (die == i) sum += i;
        }
        return sum;
    }
}
