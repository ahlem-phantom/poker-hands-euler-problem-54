package com.poker.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.poker.models.Card;
import com.poker.models.Hand;

public class HandUtilsTest {
    
    @Test
    void testParseHand_1() {
        String handString = "5H 5C 6S 7S KD";
        Hand hand = HandUtils.parseHand(handString);
        assertNotNull(hand);
        assertEquals(5, hand.getCards().size());
    }

    @Test
    public void testParseHand_2() {
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(
                new Card(2, 0), // 2 of Hearts
                new Card(3, 0), // 3 of Hearts
                new Card(4, 0), // 4 of Hearts
                new Card(5, 0), // 5 of Hearts
                new Card(6, 0) // 6 of Hearts
        ));
        String handString = "2H 3H 4H 5H 6H";
        Hand hand = HandUtils.parseHand(handString);

        System.out.println("Actual cards: " + hand.getCards());
        System.out.println("Expected cards: " + cards);

        assertEquals(cards, hand.getCards()); 
    }

    @Test
    public void testParseHand_ValidInput() {
        String validHandString = "2H 3D 5C 9S KD";
        Hand hand = HandUtils.parseHand(validHandString);
        assertNotNull(hand);
        assertEquals(5, hand.getCards().size());
    }

    @Test
    public void testParseHand_NullInput() {
        String nullHandString = null;
        Hand hand = HandUtils.parseHand(nullHandString);
        assertNull(hand);
    }

    @Test
    public void testParseHand_EmptyInput() {
        String emptyHandString = "";
        Hand hand = HandUtils.parseHand(emptyHandString);
        assertNull(hand);
    }


    @Test
    void isSameSuit() {
        String handString1 = "2C 3S 8S 8D TD";
        Hand hand1 = HandUtils.parseHand(handString1);
        assertFalse(HandUtils.isSameSuit(hand1));

        String handString2 = "5S 8S 9S JS AS";
        Hand hand2 = HandUtils.parseHand(handString2);
        assertTrue(HandUtils.isSameSuit(hand2));

        String handString3 = "8C 7C 9S TC 6C";
        Hand hand3 = HandUtils.parseHand(handString3);
        assertFalse(HandUtils.isSameSuit(hand3));
    }

    @Test
    void isConsecutive() {
        String handString1 = "5D 8C 9S JS AC";
        Hand hand1 = HandUtils.parseHand(handString1);
        assertFalse(HandUtils.isConsecutive(hand1));

        String handString2 = "2C 3C 4D 5S 6H";
        Hand hand2 = HandUtils.parseHand(handString2);
        assertTrue(HandUtils.isConsecutive(hand2));

        String handString3 = "8H 7C 9S TC 6C";
        Hand hand3 = HandUtils.parseHand(handString3);
        assertTrue(HandUtils.isConsecutive(hand3));
    }



    @Test
    void getHighCardTest() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = HandUtils.parseHand(handString1);
        assertEquals(13, HandUtils.getHighCard(hand1));

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = HandUtils.parseHand(handString2);
        assertEquals(12, HandUtils.getHighCard(hand2));
    }

 
    @Test
    void countValuesOccurrencesTest() {
        String handString = "2D 9C AS AH AC";
        Hand hand = HandUtils.parseHand(handString);
        Map<Integer, Integer> occurrenceValues = HandUtils.countValuesOccurrences(hand);

        System.out.println("Occurrence values: " + occurrenceValues);

        assertEquals(3, occurrenceValues.get(14));
        assertEquals(1, occurrenceValues.get(2));
        assertEquals(1, occurrenceValues.get(9));
    }


    @Test
    void getPairValue() {
        String handString = "2D 9C 5S AH AC";
        Hand hand = HandUtils.parseHand(handString);
        assertEquals(14, HandUtils.getPairValue(hand));
    }

    @Test
    void getPairValueSameTest() {
        String handString = "9D 9C 5S AH AC";
        Hand hand = HandUtils.parseHand(handString);
        assertEquals(14, HandUtils.getPairValue(hand)); // prob here in case we have 2 pairs it gets first pair value
    }

    @Test
    void getNumberOfPairsTest() {
        Map<Integer, Integer> occurrenceMap = new HashMap<>();
        occurrenceMap.put(2, 2);
        occurrenceMap.put(3, 1);
        occurrenceMap.put(4, 1);

        assertEquals(1, HandUtils.getNumberOfPairs(occurrenceMap));
    }


    @Test
    void getHandRankTest() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = HandUtils.parseHand(handString1);
        assertEquals(1, HandUtils.getHandRank(hand1));

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = HandUtils.parseHand(handString2);
        assertEquals(0, HandUtils.getHandRank(hand2));
    }



    @Test
    void testProblemExample1() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "2C 3S 8S 8D TD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandUtils.getPlayer1Wins(hand1, hand2)); //failed 
    }

    @Test
    void testProblemExample2() {
        String handString1 = "5D 8C 9S JS AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(1, HandUtils.getPlayer1Wins(hand1, hand2));
    }

    @Test
    void testProblemExample3() {
        String handString1 = "2D 9C AS AH AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "3D 6D 7D TD QD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandUtils.getPlayer1Wins(hand1, hand2));
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

        assertEquals(1, HandUtils.getPlayer1Wins(hand1, hand2)); //failed
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

        assertEquals(1, HandUtils.getPlayer1Wins(hand1, hand2)); //failed
    }

    @Test
    void getPlayer1Case6() {
        String handString1 = "TC JC QC KC AC";
        Hand hand1 = HandUtils.parseHand(handString1);

        String handString2 = "TD JD QD KD AD";
        Hand hand2 = HandUtils.parseHand(handString2);

        assertEquals(0, HandUtils.getPlayer1Wins(hand1, hand2));
    }



}
