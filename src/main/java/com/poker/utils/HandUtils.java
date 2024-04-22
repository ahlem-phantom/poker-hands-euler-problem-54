package com.poker.utils;

import java.util.ArrayList;

import com.poker.models.Card;
import com.poker.models.Hand;

public class HandUtils {

    /**
     * Parses a hand from the given string representation.
     *
     * @param handString The string representation of the hand.
     * @return The parsed Hand object, or null if parsing fails.
     */
    public static Hand parseHand(String handString) {
        if (handString == null || handString.isEmpty()) {
            System.err.println("Error: Hand string is null or empty.");
            return null;
        }

        ArrayList<Card> hand = new ArrayList<>();
        String[] cardStrings = handString.split(" ");
        for (String cardString : cardStrings) {
            Card card = CardUtils.parseCard(cardString);
            if (card == null) {
                System.err.println("Error: Invalid card string - " + cardString);
                return null; // Invalid card string, return null
            }
            hand.add(card);
        }

        return new Hand(hand);
    }

}
