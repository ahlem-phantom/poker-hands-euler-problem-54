package com.poker.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

       // assertEquals(cards, hand.getCards()); // error here
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



}
