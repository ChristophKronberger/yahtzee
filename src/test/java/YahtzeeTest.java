import org.example.Yahtzee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YahtzeeTest {

    @Test
    public void Chance_scores_sum_of_all_dice() {
        assertEquals(15, new Yahtzee(2, 3, 4, 5, 1).chance());
        assertEquals(16, new Yahtzee(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void Yahtzee_scores_50() {
        assertEquals(50, new  Yahtzee(4, 4, 4, 4, 4).yahtzee());
        assertEquals(50, new Yahtzee(6, 6, 6, 6, 6).yahtzee());
        assertEquals(0, new Yahtzee(6, 6, 6, 6, 3).yahtzee());
    }

    @Test
    public void Test_1s() {
        assertEquals(1, new Yahtzee(1, 2, 3, 4, 5).sumOf(1));
        assertEquals(2, new Yahtzee(1, 2, 1, 4, 5).sumOf(1));
        assertEquals(0, new Yahtzee(6, 2, 2, 4, 5).sumOf(1));
        assertEquals(4, new Yahtzee(1, 2, 1, 1, 1).sumOf(1));
    }

    @Test
    public void test_2s() {
        assertEquals(4, new Yahtzee(1, 2, 3, 2, 6).sumOf(2));
        assertEquals(10, new Yahtzee(2, 2, 2, 2, 2).sumOf(2));
    }

    @Test
    public void test_3s() {
        assertEquals(6, new Yahtzee(1, 2, 3, 2, 3).sumOf(3));
        assertEquals(12, new Yahtzee(2, 3, 3, 3, 3).sumOf(3));
    }

    @Test
    public void test_4s() {
        assertEquals(12, new Yahtzee(4, 4, 4, 5, 5).sumOf(4));
        assertEquals(8, new Yahtzee(4, 4, 5, 5, 5).sumOf(4));
        assertEquals(4, new Yahtzee(4, 5, 5, 5, 5).sumOf(4));
    }

    @Test
    public void test_5s() {
        assertEquals(10, new Yahtzee(4, 4, 4, 5, 5).sumOf(5));
        assertEquals(15, new Yahtzee(4, 4, 5, 5, 5).sumOf(5));
        assertEquals(20, new Yahtzee(4, 5, 5, 5, 5).sumOf(5));
    }

    @Test
    public void test_6s() {
        assertEquals(0, new Yahtzee(4, 4, 4, 5, 5).sumOf(6));
        assertEquals(6, new Yahtzee(4, 4, 6, 5, 5).sumOf(6));
        assertEquals(18, new Yahtzee(6, 5, 6, 6, 5).sumOf(6));
    }

    @Test
    public void one_pair() {
        assertEquals(6, new Yahtzee(3, 4, 3, 5, 6).scorePair());
        assertEquals(10, new Yahtzee(5, 3, 3, 3, 5).scorePair());
        assertEquals(12, new Yahtzee(5, 3, 6, 6, 5).scorePair());
    }

    @Test
    public void two_Pair() {
        assertEquals(16, new Yahtzee(3, 3, 5, 4, 5).scoreTwoPairs());
        assertEquals(0, new Yahtzee(3, 3, 5, 5, 5).scoreTwoPairs());
    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, new Yahtzee(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15,new Yahtzee(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(0, new Yahtzee(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void four_of_a_knd() {
        assertEquals(12, new Yahtzee(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, new Yahtzee(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(0,  new Yahtzee(3, 3, 3, 3, 3).fourOfAKind());
    }

    @Test
    public void smallStraight() {
        assertEquals(15,new Yahtzee(1, 2, 3, 4, 5).straight());
        assertEquals(15,new Yahtzee(2, 3, 4, 5, 1).straight());
        assertEquals(0, new Yahtzee(1, 2, 2, 4, 5).straight());
    }

    @Test
    public void largeStraight() {
        assertEquals(20, new Yahtzee(6, 2, 3, 4, 5).straight());
        assertEquals(20, new Yahtzee(2, 3, 4, 5, 6).straight());
        assertEquals(0,  new Yahtzee(1, 2, 2, 4, 5).straight());
    }

    @Test
    public void fullHouse() {
        assertEquals(18,new Yahtzee(6, 2, 2, 2, 6).fullHouse());
        assertEquals(0, new Yahtzee(2, 3, 4, 5, 6).fullHouse());
    }
}
