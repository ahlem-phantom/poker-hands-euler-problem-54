package com.poker.common;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsTest {

    @Test
    public void testGetFileReader() {
        // Test file that exists in the resources folder
        String existingFilePath = "0054_poker.txt";
        // Test file that does not exist in the resources folder
        String nonExistingFilePath = "non_existing.txt";

        // Test getting a reader for an existing file
        try (BufferedReader reader = FileUtils.getFileResource(existingFilePath)) {
            assertNotNull(reader, "Reader for existing file should not be null");
        } catch (IOException e) {
            fail("IOException should not be thrown for existing file");
        } catch (IllegalArgumentException e) {
            fail("IllegalArgumentException should not be thrown for existing file");
        }

        // Test getting a reader for a non-existing file
        assertThrows(IllegalArgumentException.class, () -> FileUtils.getFileResource(nonExistingFilePath),
                "Getting reader for non-existing file should throw IllegalArgumentException");
    }
}
