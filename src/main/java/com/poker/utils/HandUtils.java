package com.poker.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        List<Card> hand = Arrays.stream(handString.split(" "))
                .map(CardUtils::parseCard)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return hand.size() == handString.split(" ").length ? new Hand(hand) : null;
    }

    /**
     * Checks if all cards in the hand have the same suit.
     *
     * @param hand The hand to check.
     * @return True if all cards have the same suit, false otherwise.
     */
    public static boolean isSameSuit(Hand hand) {
        return hand.getCards().stream()
                .map(Card::getSuit)
                .distinct()
                .count() == 1;
    }

    /**
     * Checks if all cards in the hand have consecutive numbers.
     *
     * @param hand The hand to check.
     * @return True if all cards have consecutive numbers, false otherwise.
     */
    public static boolean isConsecutive(Hand hand) {
        List<Card> cards = new ArrayList<>(hand.getCards());
        cards.sort(Comparator.comparingInt(Card::getNumber));

        return IntStream.range(1, cards.size())
                .allMatch(i -> cards.get(i).getNumber() == cards.get(i - 1).getNumber() + 1);
    }

    /**
     * Gets the highest card value in the hand.
     *
     * @param hand The hand to analyze.
     * @return The value of the highest card in the hand.
     */
    public static int getHighCard(Hand hand) {
        return hand.getCards().stream()
                .mapToInt(Card::getNumber)
                .max()
                .orElse(0);
    }

  /**
     * Calculates the occurrence of each card rank in the hand.
     *
     * @param hand The hand to analyze.
     * @return A map containing the occurrence count of each card rank in the hand.
     * @apiNote "3S 3C 3S 9H 9H" => {3=3, 9=2}
     */
    public static Map<Integer, Integer> countValuesOccurrences(Hand hand) {
        return hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getNumber, Collectors.summingInt(e -> 1)));
    }


    /**
     * Gets the value of the pair in the hand, if present.
     *
     * @param hand The hand to search for pairs.
     * @return The value of the pair if found, or -1 if no pair is present.
     *
     */
    public static int getPairValue(Hand hand) {
        Map<Integer, Integer> occurrenceValues = countValuesOccurrences(hand);
        List<Integer> pairValues = occurrenceValues.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (pairValues.size() == 2) {
            return Collections.max(pairValues);
        } else if (pairValues.size() == 1) {
            return pairValues.get(0);
        } else {
            return -1;
        }
    }
 /**
     * Counts the number of pairs in the hand.
     *
     * @param occurrenceValues A map representing the frequency of card values in
     *                         the hand.
     *                         The keys are card values, and the values are the
     *                         number of occurrences.
     * 
     * @return The number of pairs in the hand 1 for OnePair, 2 for TwoPairs
     *         otherwise 0
     * @apiNote "3C 3D 3S 9S 9D" ==> 1 (9S 9D) "3C 3D 5S 9S 9D" ==> 2 (3C 3D and 9S
     *          9D)
     */
    public static int getNumberOfPairs(Map<Integer, Integer> occurrenceValues) {
        return (int) occurrenceValues.values().stream().filter(count -> count == 2).count();
    }


   
    /**
     *
     * Determines the rank of the hand in a poker game.
     *
     * @param hand The hand to evaluate.
     * @return The rank of the hand.
     *
     */
    public static int getHandRank(Hand hand) {
        Map<Integer, Integer> occurrenceValues = countValuesOccurrences(hand);
        if (isConsecutive(hand) && isSameSuit(hand) && getHighCard(hand) == 14)
            return 9;
        else if (isConsecutive(hand) && isSameSuit(hand))
            return 8;
        else if (occurrenceValues.containsValue(4))
            return 7;
        else if (occurrenceValues.containsValue(3) && occurrenceValues.containsValue(2))
            return 6;
        else if (isSameSuit(hand))
            return 5;
        else if (isConsecutive(hand))
            return 4;
        else if (occurrenceValues.containsValue(3))
            return 3;
        else if (getNumberOfPairs(occurrenceValues) == 2)
            return 2;
        else if (getNumberOfPairs(occurrenceValues) == 1)
            return 1;
        else
            return 0;
    }

    /**
     * Determines if player 1 wins based on the comparison of their hands.
     *
     * @param hand1 The hand of player 1.
     * @param hand2 The hand of player 2.
     * @return 1 if player 1 wins, 0 otherwise.
     * 
     */
    public static int getPlayer1Wins(Hand hand1, Hand hand2) {
        int rank1 = getHandRank(hand1);
        int rank2 = getHandRank(hand2);

        if (rank1 > rank2) {
            // Player 1 wins with a higher rank
            return 1;
        } else if (rank1 == rank2) {
            // If both players have the same rank
            if (rank1 == 1) {
                // Both players have one pair, compare their pair values
                int pairValue1 = getPairValue(hand1);
                int pairValue2 = getPairValue(hand2);

                if (pairValue1 > pairValue2) {
                    // Player 1 wins with a higher pair value
                    return 1;
                } else if (pairValue1 == pairValue2) {
                    // If both players have the same pair value, compare their high cards
                    int highCard1 = getHighCard(hand1);
                    int highCard2 = getHighCard(hand2);

                    if (highCard1 > highCard2) {
                        // Player 1 wins with a higher high card
                        return 1;
                    }
                }
            } else {
                // Both players have different ranks, compare their high cards
                int highCard1 = getHighCard(hand1);
                int highCard2 = getHighCard(hand2);

                if (highCard1 > highCard2) {
                    // Player 1 wins with a higher high card
                    return 1;
                }
            }
        }
        return 0;
    }

}
