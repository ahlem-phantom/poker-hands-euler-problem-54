package com.poker.domains;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class HandRankEvaluatorTest {

    
    @Test
    void testCompareHands_Player1Wins() {
        // player 1 has a royal flush (Ace-high straight flush)
        String handString1 = "AH KH QH JH TH";
        Hand hand1 = Hand.parseHand(handString1);
        
        // player 2 has a straight flush, but not a royal flush
        String handString2 = "9D 8D 7D 6D 5D";
        Hand hand2 = Hand.parseHand(handString2);
        
        // comparator for comparison based on hand ranks
        Comparator<Hand> comparator = Comparator.comparingInt(Hand::getHandRank);
        
        // player 1 (royal flush) should win over Player 2 (straight flush)
        int result = HandRankEvaluator.compareHands(hand1, hand2, comparator);
        
        assertEquals(1, result); 
    }
    

    @Test
    void testCompareHands_Player2Wins() {
        // player 1 has a full house with 4s over 2s, while Player 2 has a full house with 5s over 3s
        String handString1 = "2H 2D 2C 4D 4S" ;
        Hand hand1 = Hand.parseHand(handString1);
        String handString2 = "3H 3D 3C 5D 5S";
        Hand hand2 = Hand.parseHand(handString2);
        Comparator<Hand> comparator = Comparator.comparingInt(Hand::getThreeOfAKindValue)
                .thenComparingInt(Hand::getPairValue);

        int result = HandRankEvaluator.compareHands(hand1, hand2, comparator);

        assertEquals(0, result);
    }
    

    @Test
    void testCompareCardValues_Player1Wins() {
        String handString1 = "2H 2D 4C 4D 4S";
        Hand hand1 = Hand.parseHand(handString1);
        String handString2 = "3H 3D 5C 5D 5S";
        Hand hand2 = Hand.parseHand(handString2);
        Function<Hand, Integer> cardValue = Hand::getHighestNonPairCard;

        int result = HandRankEvaluator.compareCardValues(hand1, hand2, cardValue);

        assertEquals(0, result);
    }

    @Test
    void testCompareCardValues_Player2Wins() {
        String handString1 = "3H 4D AC 5D 5S";
        Hand hand1 = Hand.parseHand(handString1);
        String handString2 = "2H 6D QC 4D 4S";
        Hand hand2 = Hand.parseHand(handString2);
        Function<Hand, Integer> cardValue = Hand::getHighestNonPairCard;

        int result = HandRankEvaluator.compareCardValues(hand1, hand2, cardValue);

        assertEquals(1, result);
    }


    @Test
    void testCompareFullHouse_Player1Wins() {
        String handString1 = "KH KD KC 5D 5S";
        Hand hand1 = Hand.parseHand(handString1);
        String handString2 = "JH JD JC 4D 4S";
        Hand hand2 = Hand.parseHand(handString2);

        int result = HandRankEvaluator.compareFullHouse(hand1, hand2);

        assertEquals(1, result);
    }

    @Test
    void testCompareFullHouse_Player2Wins() {
        String handString1 = "2H 2D 2C 4D 4S";
        Hand hand1 = Hand.parseHand(handString1);
        String handString2 = "3H 3D 3C 5D 5S";
        Hand hand2 = Hand.parseHand(handString2);

        int result = HandRankEvaluator.compareFullHouse(hand1, hand2);

        assertEquals(0, result);
    }

    
    @Test
    void testProblemExample1() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 3S 8S 8D TD";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2)); //passed
    }

    @Test
    void testProblemExample2() {
        String handString1 = "5D 8C 9S JS AC";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(1, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testProblemExample3() {
        String handString1 = "2D 9C AS AH AC";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "3D 6D 7D TD QD";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testProblemExample4() {
        // Pair of Queens
        String handString1 = "4D 6S 9H QH QC";
        Hand hand1 = Hand.parseHand(handString1);

        // Pair of Queens
        String handString2 = "3D 6D 7H QD QS";
        Hand hand2 = Hand.parseHand(handString2);
        // if same rank ==> highestcard ==> 9> 7 ==> hand 1 wins 

        assertEquals(1, HandRankEvaluator.evaluateHands(hand1, hand2)); //passed
    }

    @Test
    void testProblemExample5() {
        // Full House With Three Fours(three of a kind + pair)
        String handString1 = "2H 2D 4C 4D 4S";
        Hand hand1 = Hand.parseHand(handString1);

        // Full House With Three Fours(three of a kind + pair)
        String handString2 = "3C 3D 3S 9S 9D";
        Hand hand2 = Hand.parseHand(handString2);

        // both players have Full House hands ==> we compare the ranking of their three of a kind
        //  player 1 has three Fours, which is a higher rank than three Threes ==> player 1 wins

        assertEquals(1, HandRankEvaluator.evaluateHands(hand1, hand2)); //passed
    }


    @Test
    void testSameRankDifferentSuits() {
        // Test for hands with cards of the same rank but different suits
        String handString1 = "2H 2D 2C 2S 3H"; // Four of a kind with different suits
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2H 2D 2C 2S 3S"; // Four of a kind with one suit
        Hand hand2 = Hand.parseHand(handString2);

        // Assuming hands with the same rank but different suits tie
        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    
    @Test
    void getPlayer1Case6() {
        String handString1 = "TC JC QC KC AC";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "TD JD QD KD AD";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    
    @Test
    void testTieRoyalFlush() {
        String handString1 = "AH KH QH JH TH";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "AH KH QH JH TH";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testTieStraightFlush() {
        String handString1 = "9S 8S 7S 6S 5S";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "9S 8S 7S 6S 5S";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testTieFourOfAKind() {
        String handString1 = "2H 2D 2C 2S 3H";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2H 2D 2C 2S 3H";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testFullHouse() {
        String handString1 = "KH KD KC 2H 2C";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "KD KC KH 3H 3C";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testTieFlush() {
        String handString1 = "AH KH QH JH 9H";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "AD KD QD JD 9D";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testStraight() {
        String handString1 = "6C 5H 4S 3D 2H";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "6S 5D 4C 3H 2S";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testThreeOfAKindTie() {
        String handString1 = "2H 2D 2C 4D 7S";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 2S 2D 4S 7C";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testTwoPairsTie() {
        String handString1 = "2H 2D 4C 4D 7S";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 2S 4D 4S 7C";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testOnePairTie() {
        String handString1 = "2H 2D 4C 5D 7S";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 2S 4D 5S 7C";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    @Test
    void testHighCardTie() {
        String handString1 = "2H 3D 4C 5D 7S";
        Hand hand1 = Hand.parseHand(handString1);

        String handString2 = "2C 3H 4D 5S 7C";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(0, HandRankEvaluator.evaluateHands(hand1, hand2));
    }

    
}
