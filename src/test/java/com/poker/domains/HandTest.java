package com.poker.domains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandTest {

    private ArrayList<Card> cards;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>(Arrays.asList(
                new Card(2, 0), 
                new Card(3, 0), 
                new Card(4, 0), 
                new Card(5, 0), 
                new Card(6, 0) 
        ));
    }

    @Test
    public void testGetCards() {
        // Create a hand with the predefined cards
        Hand hand = new Hand(cards);

        // Test getCards() method
        assertEquals(cards, hand.getCards(), "getCards() should return the list of cards in the hand");
    }

    @Test
    public void testHandToString() {
        // Create a hand with the predefined cards
        Hand hand = new Hand(cards);

        // Test toString method
        assertEquals("[(2, 0), (3, 0), (4, 0), (5, 0), (6, 0)]", hand.toString(), "toString() should return the hand's representation");
    }


    @Test
    void testParseHand_1() {
        String handString = "5H 5C 6S 7S KD";
        Hand hand = Hand.parseHand(handString);
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
        Hand hand = Hand.parseHand(handString);

        System.out.println("Actual cards: " + hand.getCards());
        System.out.println("Expected cards: " + cards);

        assertEquals(cards, hand.getCards()); 
    }

    @Test
    public void testParseHand_ValidInput() {
        String validHandString = "2H 3D 5C 9S KD";
        Hand hand = Hand.parseHand(validHandString);
        assertNotNull(hand);
        assertEquals(5, hand.getCards().size());
    }

    @Test
    public void testParseHand_NullInput() {
        String nullHandString = null;
        Hand hand = Hand.parseHand(nullHandString);
        assertNull(hand);
    }

    @Test
    public void testParseHand_EmptyInput() {
        String emptyHandString = "";
        Hand hand = Hand.parseHand(emptyHandString);
        assertNull(hand);
    }


    @Test
    void isSameSuit() {
        String handString1 = "2C 3S 8S 8D TD";
        Hand hand1 = Hand.parseHand(handString1);
        assertFalse(Hand.isSameSuit(hand1));

        String handString2 = "5S 8S 9S JS AS";
        Hand hand2 = Hand.parseHand(handString2);
        assertTrue(Hand.isSameSuit(hand2));

        String handString3 = "8C 7C 9S TC 6C";
        Hand hand3 = Hand.parseHand(handString3);
        assertFalse(Hand.isSameSuit(hand3));
    }

    @Test
    void isConsecutive() {
        String handString1 = "5D 8C 9S JS AC";
        Hand hand1 = Hand.parseHand(handString1);
        assertFalse(Hand.isConsecutive(hand1));

        String handString2 = "2C 3C 4D 5S 6H";
        Hand hand2 = Hand.parseHand(handString2);
        assertTrue(Hand.isConsecutive(hand2));

        String handString3 = "8H 7C 9S TC 6C";
        Hand hand3 = Hand.parseHand(handString3);
        assertTrue(Hand.isConsecutive(hand3));
    }



    @Test
    void getHighCardTest() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = Hand.parseHand(handString1);
        assertEquals(13, Hand.getHighCard(hand1));

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = Hand.parseHand(handString2);
        assertEquals(12, Hand.getHighCard(hand2));
    }

 
    @Test
    void countValuesOccurrencesTest() {
        String handString = "2D 9C AS AH AC";
        Hand hand = Hand.parseHand(handString);
        Map<Integer, Integer> occurrenceValues = Hand.countValuesOccurrences(hand);

        System.out.println("Occurrence values: " + occurrenceValues);

        assertEquals(3, occurrenceValues.get(14));
        assertEquals(1, occurrenceValues.get(2));
        assertEquals(1, occurrenceValues.get(9));
    }


    @Test
    void getPairValueTest() {
        String handString = "2D 9C 5S AH AC";
        Hand hand = Hand.parseHand(handString);
        assertEquals(14, Hand.getPairValue(hand));
    }

    @Test
    void getPairValueSameTest() {
        String handString = "9D 9C 5S AH AC";
        Hand hand = Hand.parseHand(handString);
        assertEquals(14, Hand.getPairValue(hand)); 
    }

    @Test
    void getNumberOfPairsTest() {
        Map<Integer, Integer> occurrenceMap = new HashMap<>();
        occurrenceMap.put(2, 2);
        occurrenceMap.put(3, 1);
        occurrenceMap.put(4, 1);

        assertEquals(1, Hand.getNumberOfPairs(occurrenceMap));
    }


    @Test
    void getHandRankTest() {
        String handString1 = "5H 5C 6S 7S KD";
        Hand hand1 = Hand.parseHand(handString1);
        assertEquals(1, Hand.getHandRank(hand1));

        String handString2 = "2C 5C 7D 8S QH";
        Hand hand2 = Hand.parseHand(handString2);
        assertEquals(0, Hand.getHandRank(hand2));
    }




    @Test
    void testGetNumberOfTwoPairs() {
        Map<Integer, Integer> occurrenceValues = new HashMap<>();
        occurrenceValues.put(2, 2); // Three pairs of value 2
        occurrenceValues.put(3, 1); // One triplet of value 3
        occurrenceValues.put(5, 2); // Two pairs of value 5

        assertEquals(2, Hand.getNumberOfPairs(occurrenceValues));
    }


    @Test
    void testGetNumberOfOnePair() {
        Map<Integer, Integer> occurrenceValues = new HashMap<>();
        occurrenceValues.put(2, 2); // Three pairs of value 2
        occurrenceValues.put(3, 3); // One triplet of value 3
        occurrenceValues.put(5, 1); // Two pairs of value 5

        assertEquals(1, Hand.getNumberOfPairs(occurrenceValues));
    }

    @Test
    void testGetThreeOfAKindValue() {
 
        String handString1 = "3C 3D 3S 9S 9D";
        Hand hand1 = Hand.parseHand(handString1);
        assertEquals(3, Hand.getThreeOfAKindValue(hand1));

        String handString2 = "4D 6S 9H QH QC";
        Hand hand2 = Hand.parseHand(handString2);
        assertEquals(-1, Hand.getThreeOfAKindValue(hand2));
    }

    @Test
    void testGetHighestNonPairCard() {
        String handString1 = "TC QD KS AS AD";
        Hand hand1 = Hand.parseHand(handString1);

        assertEquals(13, Hand.getHighestNonPairCard(hand1));

        String handString2 = "4D 6S 9H QH QC";
        Hand hand2 = Hand.parseHand(handString2);

        assertEquals(9, Hand.getHighestNonPairCard(hand2));
    }

    
}
