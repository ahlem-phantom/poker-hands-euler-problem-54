package com.poker.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private Card card;

    @BeforeEach
    void setUp() {
        // Initialize a card before each test
        card = new Card(2, 1); 
    }

    @Test
    public void testGetNumber() {
        // Test getNumber method
        assertEquals(2, card.getNumber(), "getNumber() should return the card's number");
    }

    @Test
    public void testGetSuit() {
        // Test getSuit method
        assertEquals(1, card.getSuit(), "getSuit() should return the card's suit");
    }

    @Test
    public void testCardToString() {
        // Test toString method
        assertEquals("(2, 1)", card.toString(), "toString() should return the card's representation");
    }
}
