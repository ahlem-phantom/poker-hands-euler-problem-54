package com.poker.domains;

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

    @Test
    public void testMapSuitToNumber() {
        assertEquals(0, Card.mapSuitToNumber('H'));
        assertEquals(1, Card.mapSuitToNumber('C'));
        assertEquals(2, Card.mapSuitToNumber('S'));
        assertEquals(3, Card.mapSuitToNumber('D'));
        assertThrows(IllegalArgumentException.class, () -> Card.mapSuitToNumber('X'));
    }

    @Test
    public void testMapNumberToValue() {
        assertEquals(2, Card.mapNumberToValue('2'));
        assertEquals(14, Card.mapNumberToValue('A'));
        assertThrows(IllegalArgumentException.class, () -> Card.mapNumberToValue('Z'));
    }

    @Test
    public void testParseCard() {
        Card card = Card.parseCard("AS");
        assertEquals(14, card.getNumber());
        assertEquals(2, card.getSuit());

        card = Card.parseCard("2D");
        assertEquals(2, card.getNumber());
        assertEquals(3, card.getSuit());

        assertThrows(IllegalArgumentException.class, () -> Card.parseCard("XH"));
        assertThrows(IllegalArgumentException.class, () -> Card.parseCard("AA"));
    }
}
