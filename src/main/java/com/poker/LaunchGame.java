package com.poker;

import java.io.BufferedReader;
import java.io.IOException;

import com.poker.models.Hand;
import com.poker.utils.FileUtils;
import com.poker.utils.HandUtils;

/* 
 * Solution to Project Euler problem #54
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
public class LaunchGame 
{
    public static void main( String[] args )
    {
           // step 1 : read file from resources folder using FileUtils
           BufferedReader reader = FileUtils.getFileResource("0054_poker.txt");

   
           // step 2 : read the lines and split them to 2 hands
           try {
               String line;
               int n=0;
               while ((line = reader.readLine()) != null) {
   
                   // split the line into two hands
                   String hand1String = line.substring(0, 14); // First 14 characters represent the first hand == First 5 cards of player 1
                   String hand2String = line.substring(15); // Remaining characters represent the second hand == Remaining 5 cards of player 2
             
                   System.out.println("Round "+n+":\n"+line);

                   // parse cards of each player
                   Hand hand1 = HandUtils.parseHand(hand1String);
                   System.out.println("Hand of Player 1: "+hand1);
                   Hand hand2 = HandUtils.parseHand(hand2String);
                   System.out.println("Hand of Player 2: "+hand2);
                   System.out.println("*************************");
                   n++;
               }
   
   
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
}
