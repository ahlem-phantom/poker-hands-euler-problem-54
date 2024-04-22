package com.poker.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

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
    
}
