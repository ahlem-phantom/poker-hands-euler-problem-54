package com.poker.models;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> card;

    public Hand(ArrayList<Card> card) {
        this.card = card;
    }

    public ArrayList<Card> getCards() {
        return card;
    }

   @Override
    public String toString() {
        return "" + card + "";
    }

}