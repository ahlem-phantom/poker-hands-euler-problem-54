package com.poker.utils;

import com.poker.models.Card;

public class CardUtils {

    /**
     * Maps a suit symbol to a corresponding number.
     * 
     * @param symbol The suit symbol to be mapped.
     * @return The mapped number corresponding to the suit symbol.
     * @throws IllegalArgumentException if the suit symbol is invalid.
     */
    public static int mapSuitToNumber(char symbol) {
        switch (symbol) {
            case 'H':
                return 0;
            case 'C':
                return 1;
            case 'S':
                return 2;
            case 'D':
                return 3;
            default:
                throw new IllegalArgumentException("Invalid suit symbol: " + symbol);
        }
    }

    /**
     * Maps a character symbol to its corresponding integer value.
     * 
     * @param number The number to be mapped.
     * @return The integer value corresponding to the symbol.
     * @throws IllegalArgumentException If the symbol is invalid.
     */
    public static int mapNumberToValue(char number) {
        switch (number) {
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                throw new IllegalArgumentException("Invalid number symbol: " + number);
        }
    }

    /**
     * Method to parse a card from its string representation
     * 
     * @param cardString the string representation of the card
     * @return the corresponding Card object
     * @throws IllegalArgumentException if the cardString is invalid
     */
    public static Card parseCard(String cardString) {
        if (cardString.length() != 2) {
            throw new IllegalArgumentException("Invalid card string: " + cardString);
        }
        char numberSymbol = cardString.charAt(0);
        char suitSymbol = cardString.charAt(1);

        int number = CardUtils.mapNumberToValue(numberSymbol);
        int suit = CardUtils.mapSuitToNumber(suitSymbol);

        return new Card(number, suit);
    }

}
