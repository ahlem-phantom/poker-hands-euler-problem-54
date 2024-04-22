package com.poker.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * Gets the highest card value in the hand.
     *
     * @param hand The hand to analyze.
     * @return The value of the highest card in the hand.
     */
    public static int getHighCard(Hand hand) {
        ArrayList<Card> hnd = hand.getCards();
        Collections.sort(hnd, Comparator.comparingInt(Card::getNumber));
        return hnd.get(hnd.size() - 1).getNumber();
    }

    /**
     * Checks if the given occurrence values contain a specific occurrence.
     *
     * @param occurrenceValues The values of occurrences to check.
     * @param occurrence       The occurrence to search for.
     * @return True if the occurrence values contain the specified occurrence,
     *         otherwise false
     * 
     */
    public static boolean hasOccurrence(Map<Integer, Integer> occurrenceValues, int occurrence) {
        for (int o : occurrenceValues.values()) {
            if (o == occurrence) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the occurrence of each card rank in the hand.
     *
     * @param hand The hand to analyze.
     * @return A map containing the occurrence count of each card rank in the hand.
     *
     */
    public static Map<Integer, Integer> getOccurrence(Hand hand) {
        Map<Integer, Integer> occurrenceValues = new HashMap<>();

        for (Card card : hand.getCards()) {
            int number = card.getNumber();
            occurrenceValues.put(number, occurrenceValues.getOrDefault(number, 0) + 1);
        }
        return occurrenceValues;
    }

    /**
     * Gets the value of the pair in the hand, if present.
     *
     * @param hand The hand to search for pairs.
     * @return The value of the pair if found, or -1 if no pair is present.
     *
     */
    public static int getPairValue(Hand hand) {
        Map<Integer, Integer> frequencyMap = getOccurrence(hand);

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == 2) {
                return entry.getKey(); // Return the rank of the pair
            }
        }

        return -1;
    }

    /**
     * Counts the number of pairs in the hand.
     *
     * @param occurrenceMap A map representing the frequency of card values in the
     *                      hand.
     *                      The keys are card values, and the values are the number
     *                      of occurrences.
     * 
     * @return The number of pairs in the hand.
     *
     */
    public static int getPair(Map<Integer, Integer> occurrenceMap) {
        int pairCount = 0;
        for (int occ : occurrenceMap.values()) {
            if (occ == 2) {
                pairCount++;
            }
        }

        return pairCount;
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
        Map<Integer, Integer> occurenceValues = getOccurrence(hand);
        if (isConsecutive(hand) && isSameSuit(hand) && getHighCard(hand) == 14) {
            return 9;
        } else if (isConsecutive(hand) && isSameSuit(hand)) {
            return 8;
        } else if (hasOccurrence(occurenceValues, 4)) {
            return 7;
        } else if (hasOccurrence(occurenceValues, 3) && hasOccurrence(occurenceValues, 2)) {
            return 6;
        } else if (isSameSuit(hand)) {
            return 5;
        } else if (isConsecutive(hand)) {
            return 4;
        } else if (hasOccurrence(occurenceValues, 3)) {
            return 3;
        } else if (getPair(occurenceValues) == 2) {
            return 2;
        } else if (getPair(occurenceValues) == 1) {
            return 1;
        } else
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
