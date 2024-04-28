package com.poker.domains;

public class Card {
    private final int number;
    private final int suit;

    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public int getSuit() {
        return suit;
    }

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
        if (number >= '2' && number <= '9') {
            return number - '0'; // ASCII values for '0' to '9' are consecutive
        } else {
            switch (number) {
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

        int number = mapNumberToValue(numberSymbol);
        int suit = mapSuitToNumber(suitSymbol);

        return new Card(number, suit);
    }

    @Override
    public String toString() {
        return "" + number + suit + "";
    }
}
