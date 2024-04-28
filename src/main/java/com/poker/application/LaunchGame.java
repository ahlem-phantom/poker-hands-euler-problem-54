package com.poker.application;

import java.io.BufferedReader;
import java.io.IOException;

import com.poker.common.FileResource;
import com.poker.domains.Hand;
import com.poker.domains.HandRankEvaluator;


/* 
 * Solution to Project Euler problem 54
 * 
 * 
 * This program implements a solution to problem 54 from Project Euler, which involves
 * determining the number of poker hands in a text file that player one wins. Each line
 * in the file represents a poker hand for two players, and the program calculates the
 * number of hands where player one wins.
 * 
 * 
 * Project Euler problem 54: https://projecteuler.net/problem=54
 * GitHub repository: https://github.com/ahlem-phantom/poker-hands-euler-problem-54
 * 
 */
public class LaunchGame {

    public static void main(String[] args) {

        // Step 1: Read file from resources folder using FileUtils
        BufferedReader reader = FileResource.getFileResource("0054_poker.txt");

        // Record start time
        long startTime = System.currentTimeMillis();

        // Step 2: Read the lines and split them to 2 hands
        try {
            String line;
            int nb = 0;

            while ((line = reader.readLine()) != null) {

                // Split the line into two hands
                String hand1String = line.substring(0, 14); // First 14 characters represent the first hand
                String hand2String = line.substring(15); // Remaining characters represent the second hand

                // Parse cards
                Hand hand1 = Hand.parseHand(hand1String);
                Hand hand2 = Hand.parseHand(hand2String);

                // Evaluate both hands and gets the winner 
                nb += HandRankEvaluator.evaluateHands(hand1, hand2);
            }

            // Record end time
            long endTime = System.currentTimeMillis();

            // Calculate runtime
            long runtime = endTime - startTime;

            // Step 3: Display of number of times player 1 wins ==> It should be 376
            System.out.println("Player 1 wins: " + nb);
            // Display runtime
            System.out.println("Total runtime: " + runtime + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}