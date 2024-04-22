package com.poker.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    /**
     * Checks if all cards in the hand have the same suit.
     *
     * @param hand The hand to check.
     * @return True if all cards have the same suit, false otherwise.
     */
    public static boolean isSameSuit(Hand hand) {
        if (hand.getCards().size() < 2) {
            return true;
        }

        int firstSuit = hand.getCards().get(0).getSuit();
        for (Card card : hand.getCards()) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all cards in the hand have consecutive numbers.
     *
     * @param hand The hand to check.
     * @return True if all cards have consecutive numbers, false otherwise.
     */
    public static boolean isConsecutive(Hand hand) {
        List<Card> cards = new ArrayList<>(hand.getCards());
        Collections.sort(cards, Comparator.comparingInt(Card::getNumber));

        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getNumber() != cards.get(i - 1).getNumber() + 1) {
                return false;
            }
        }

        return true;
    }



}
