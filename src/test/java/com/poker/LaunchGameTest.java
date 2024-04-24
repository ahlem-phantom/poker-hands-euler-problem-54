package com.poker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import com.poker.models.Hand;
import com.poker.services.HandService;
import com.poker.utils.FileUtils;
import com.poker.utils.HandUtils;


public class LaunchGameTest {

    @Test
    public void testPlayer1WinsCount() throws IOException {
  
        BufferedReader reader = FileUtils.getFileResource("0054_poker.txt");

        int expectedNb = 376; // Expected number of times player 1 wins in the test file
        int actualNb = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String hand1String = line.substring(0, 14);
            String hand2String = line.substring(15);

            Hand hand1 = HandUtils.parseHand(hand1String);
            Hand hand2 = HandUtils.parseHand(hand2String);

            actualNb += HandService.evaluateHands(hand1, hand2);
        }

        reader.close();

        assertEquals(expectedNb, actualNb, "Number of Player 1 wins doesn't match");
    }
}