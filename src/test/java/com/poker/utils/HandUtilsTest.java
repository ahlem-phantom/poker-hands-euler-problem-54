package com.poker.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
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

}
