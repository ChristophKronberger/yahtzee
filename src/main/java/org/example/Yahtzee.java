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

    /**
     * Chance:
     *   The player scores the sum of all dice,
     *   no matter what they read.
     *   For example,
     *    1,1,3,3,6 placed on "chance" scores 14 (1+1+3+3+6)
     * @return the value of all dice
     */
    public int chance() {
        return Arrays.stream(dices).sum();
    }

    /**
     * Yahtzee:
     *   If all dice have the same number,
     *   the player scores 50 points.
     *   For example,
     *    1,1,1,1,1 placed on "yahtzee" scores 50
     * @return 50 if all dices are ident. else 0
     */
    public int yahtzee() {
        Set<Integer> set = Arrays.stream(dices).boxed().collect(Collectors.toSet());
        return (set.size() == 1) ? 50 : 0;
    }

    /**
     * Pair:
     *   The player scores the sum of the two highest matching dice.
     *   For example, when placed on "pair"
     *    3,3,3,4,4 scores 8 (4+4)
     * @return score of the pair if present, else 0
     */
    public int scorePair() {
        return countMultipleOccur(2);
    }

    /**
     *  Three of a kind:
     *   If there are three dice with the same number, the player
     *   scores the sum of these dice.
     *   For example, when placed on "three of a kind"
     *     3,3,3,4,5 scores 9 (3+3+3)
     * @return the value of the triplet if present, else 0
     */
    public int threeOfAKind() {
        return countMultipleOccur(3);
    }

    /**
     * Four of a kind:
     *   If there are four dice with the same number, the player
     *   scores the sum of these dice.
     *   For example, when placed on "four of a kind"
     *   2,2,2,2,5 scores 8 (2+2+2+2)
     * @return the value of the Quadruplet if present, else 0
     */
    public int fourOfAKind() {
        return countMultipleOccur(4);
    }

    /**
     * Two pairs:
     *   If there are two pairs of dice with the same number, the
     *   player scores the sum of these dice.
     *   For example, when placed on "two pairs"
     *    1,1,2,3,3 scores 8 (1+1+3+3)
     *  @return score of the two pairs if present. else 0
     */
    public int scoreTwoPairs() {
        List<Integer> scoresOfAllPairs = getScoresOfAllPairs();
        if(scoresOfAllPairs.size() == 2) return scoresOfAllPairs.stream().mapToInt(Integer::valueOf).sum();
        return 0;
    }

    private List<Integer> getScoresOfAllPairs() {
        List<Integer> pairs = new ArrayList<>();
        for (int face = 6; face >= 1; face--) {
            int amountOfFaces = getAmountOfEqualFaces(face);
            if (amountOfFaces == 2) pairs.add(face*2);
        }

        return pairs;
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

    private int countMultipleOccur(int amountToCheck) {
        for (int face = 6; face >= 1; face--) {
            int amountOfFaces = getAmountOfEqualFaces(face);
            if (amountOfFaces == amountToCheck) return amountToCheck * face;
        }
        return 0;
    }

    private int getAmountOfEqualFaces(int face) {
        return (int) Arrays.stream(dices)
                .filter(number -> number == face)
                .count();
    }

    /**
     * Counts multiple Occurrence of a face.
     * @param face  to count
     * @return the score
     */
    public int sumOf(int face) {
        return getAmountOfEqualFaces(face)*face;
    }
}
