package com.poker.models;

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

    @Override
    public String toString() {
        return "(" + number + ", " + suit + ")";
    }
}