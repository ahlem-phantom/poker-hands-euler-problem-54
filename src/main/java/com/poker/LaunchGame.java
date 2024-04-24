package com.poker;

import java.io.BufferedReader;
import java.io.IOException;

import com.poker.models.Hand;
import com.poker.services.HandService;
import com.poker.utils.FileUtils;
import com.poker.utils.HandUtils;


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

        // Step 1 : Read file from resources folder using FileUtils
        BufferedReader reader = FileUtils.getFileResource("0054_poker.txt");

        // Record start time
        long startTime = System.currentTimeMillis();

        // Step 2 : Read the lines and split them to 2 hands
        try {
            String line;
            int nb = 0;

            while ((line = reader.readLine()) != null) {

                // Split the line into two hands
                String hand1String = line.substring(0, 14); // First 14 characters represent the first hand
                String hand2String = line.substring(15); // Remaining characters represent the second hand

                // Parse cards
                Hand hand1 = HandUtils.parseHand(hand1String);

                Hand hand2 = HandUtils.parseHand(hand2String);

                nb += HandService.evaluateHands(hand1, hand2);
            }

            // Record end time
            long endTime = System.currentTimeMillis();

            // Calculate runtime
            long runtime = endTime - startTime;

            // Display of number of times player 1 wins --- 376
            System.out.println("Player 1 wins: " + nb);
            // Display runtime
            System.out.println("Total runtime: " + runtime + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}