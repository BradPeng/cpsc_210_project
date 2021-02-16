package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveCheckerTest {
    SaveChecker saveChecker;

    @BeforeEach
    public void runBefore() {
        saveChecker = new SaveChecker();
    }

    @Test
    public void testConstructor() {
        assertTrue(saveChecker.getSaveStats());
    }

    @Test
    public void testGetSaveStatus() {
        assertTrue(saveChecker.getSaveStats());
        saveChecker.setSaveStatus(false);
        assertFalse(saveChecker.getSaveStats());
    }

    @Test
    public void testSetSaveStats() {
        assertTrue(saveChecker.getSaveStats());
        saveChecker.setSaveStatus(false);
        assertFalse(saveChecker.getSaveStats());
        saveChecker.setSaveStatus(true);
        assertTrue(saveChecker.getSaveStats());
    }
}
