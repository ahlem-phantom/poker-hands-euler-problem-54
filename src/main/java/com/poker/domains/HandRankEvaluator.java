package com.poker.domains;

import java.util.Comparator;
import java.util.function.Function;


public class HandRankEvaluator {
    
       /**
     * Determines the winner between two hands based on a specified comparison function.
     *
     * @param hand1         The hand of player 1.
     * @param hand2         The hand of player 2.
     * @param comparison    The comparison function to determine the winner.
     * @return 1 if player 1 wins, 0 otherwise.
     * 
     */
    public static int compareHands(Hand hand1, Hand hand2, Comparator<Hand> comparison) {
        return comparison.compare(hand1, hand2) > 0 ? 1 : 0;
    }

    /**
     * Determines the winner between two hands based on a specified card value
     * function.
     * 
     * Works for most of hand ranks (not all of them though)
     * 
     * @param hand1             The hand of player 1.
     * @param hand2             The hand of player 2.
     * @param cardValueFunction The function to determine the card value.
     * @return 1 if player 1 wins, 0 otherwise.
     */
    public static int compareCardValues(Hand hand1, Hand hand2, Function<Hand, Integer> cardValue) {
        int cardValue1 = cardValue.apply(hand1);
        int cardValue2 = cardValue.apply(hand2);

        if (cardValue1 > cardValue2) {
            return 1;
        } else if (cardValue1 == cardValue2) {
            // Compare based on the highest non-pair card if card values are equal
            Comparator<Hand> comparator = Comparator.comparingInt(Hand::getHighestNonPairCard);
            return compareHands(hand1, hand2, comparator);
        }
        return 0;
    }

    /**
     * Compares the full house hands and determines the winner based on triplet
     * values and pair values.
     *
     * @param hand1 The hand of player 1.
     * @param hand2 The hand of player 2.
     * @return 1 if player 1 wins, 0 otherwise.
     * 
     */
    public static int compareFullHouse(Hand hand1, Hand hand2) {
        Comparator<Hand> comparator = Comparator.comparingInt(Hand::getThreeOfAKindValue)
                .thenComparingInt(Hand::getPairValue);
        return compareHands(hand1, hand2, comparator);
    }

    /**
     * Determines the winner between two hands based on their ranks and card values.
     *
     * @param hand1 The hand of player 1.
     * @param hand2 The hand of player 2.
     * @return 1 if player 1 wins, 0 otherwise.
     */
   /* */ public static int evaluateHands(Hand hand1, Hand hand2) {
        int rank1 = Hand.getHandRank(hand1);
        int rank2 = Hand.getHandRank(hand2);

        if (rank1 > rank2) {
            return 1; // Player 1 wins with a higher rank
        } else if (rank1 == rank2) {
            // If both players have the same rank
            switch (rank1) {
                case 1: // One pair
                    return compareCardValues(hand1, hand2, Hand::getPairValue);
                case 6: // Full house
                    
                    return compareFullHouse(hand1, hand2);
                default: // Other ranks
                    Comparator<Hand> comparator = Comparator.comparingInt(Hand::getHighestNonPairCard);
                    return compareHands(hand1, hand2, comparator);
            }
        }

        return 0; // Player 2 wins or ranks are not equal
    }

}
