package com.poker.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.poker.models.Card;

public class CardUtilsTest {

    @Test
    public void testMapSuitToNumber() {
        assertEquals(0, CardUtils.mapSuitToNumber('H'));
        assertEquals(1, CardUtils.mapSuitToNumber('C'));
        assertEquals(2, CardUtils.mapSuitToNumber('S'));
        assertEquals(3, CardUtils.mapSuitToNumber('D'));
        assertThrows(IllegalArgumentException.class, () -> CardUtils.mapSuitToNumber('X'));
    }

    @Test
    public void testMapNumberToValue() {
        assertEquals(2, CardUtils.mapNumberToValue('2'));
        assertEquals(14, CardUtils.mapNumberToValue('A'));
        assertThrows(IllegalArgumentException.class, () -> CardUtils.mapNumberToValue('Z'));
    }

    @Test
    public void testParseCard() {
        Card card = CardUtils.parseCard("AS");
        assertEquals(14, card.getNumber());
        assertEquals(2, card.getSuit());

        card = CardUtils.parseCard("2D");
        assertEquals(2, card.getNumber());
        assertEquals(3, card.getSuit());

        assertThrows(IllegalArgumentException.class, () -> CardUtils.parseCard("XH"));
        assertThrows(IllegalArgumentException.class, () -> CardUtils.parseCard("AA"));
    }

}
