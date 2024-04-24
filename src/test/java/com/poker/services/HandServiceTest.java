package com.poker.services;

import com.poker.models.Hand;
import com.poker.utils.HandUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class HandServiceTest {
  

    // These are the provided examples in the project euler website 
    
    @Test
    void testProblemExample1() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "2C 3S 8S 8D TD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandService.evaluateHands(hand1, hand2)); //passed
    }

    @Test
    void testProblemExample2() {
        String handString1 = "5D 8C 9S JS AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(1, HandService.evaluateHands(hand1, hand2));
    }

    @Test
    void testProblemExample3() {
        String handString1 = "2D 9C AS AH AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "3D 6D 7D TD QD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandService.evaluateHands(hand1, hand2));
    }

    @Test
    void testProblemExample4() {
        // Pair of Queens
        String handString1 = "4D 6S 9H QH QC";
        Hand hand1 = HandUtils.parseHand(handString1);

        // Pair of Queens
        String handString2 = "3D 6D 7H QD QS";
        Hand hand2 = HandUtils.parseHand(handString2);
        // if same rank ==> highestcard ==> 9> 7 ==> hand 1 wins 

        assertEquals(1, HandService.evaluateHands(hand1, hand2)); //passed
    }

    @Test
    void testProblemExample5() {
        // Full House With Three Fours(three of a kind + pair)
        String handString1 = "2H 2D 4C 4D 4S";
        Hand hand1 = HandUtils.parseHand(handString1);

        // Full House With Three Fours(three of a kind + pair)
        String handString2 = "3C 3D 3S 9S 9D";
        Hand hand2 = HandUtils.parseHand(handString2);

        // both players have Full House hands ==> we compare the ranking of their three of a kind
        //  player 1 has three Fours, which is a higher rank than three Threes ==> player 1 wins

        assertEquals(1, HandService.evaluateHands(hand1, hand2)); //passed
    }

    @Test
    void getPlayer1Case6() {
        String handString1 = "TC JC QC KC AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "TD JD QD KD AD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandService.evaluateHands(hand1, hand2));
    }

    
}
