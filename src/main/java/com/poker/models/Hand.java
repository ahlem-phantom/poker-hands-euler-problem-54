package com.poker.models;

import java.util.List;

public class Hand {

    private List<Card> card;

    public Hand(List<Card> hand) {
        this.card = hand;
    }

    public List<Card> getCards() {
        return card;
    }

   @Override
    public String toString() {
        return "" + card + "";
    }

}