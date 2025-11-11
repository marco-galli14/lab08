package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final int NEGATIVE = -1;
    private static final long SLEEP = 100;
    private static final long LONGSLEEP = 6100;
    private static final String H1 = "Giancarlo";
    private static final String H2 = "Larry";
    private static final String C1 = "karting accident";
    private static final String D1 = "ran for too long";

    private DeathNote deathNote;

    /**
     * Sets up the death note.
     */
    @BeforeEach
    void setUp() {
        this.deathNote = new DeathNoteImpl();
    }

    /**
     * Tests if rule number 0 and negative rules exist in the DeathNote rules (should not).
     */
    @Test //1
    void testNegativeRules() {
        try {
            deathNote.getRule(NEGATIVE);
            fail("The method didn't thrown any IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            deathNote.getRule(0);
            fail("The method didn't thrown any IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
    }

    /**
     * Tests if rules are empty or null in the DeathNote rules (should not).
     */
    @Test //2
    void testNoEmptyRules() {
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotNull(deathNote.getRule(i));
            assertFalse(deathNote.getRule(i).isBlank());
        }
    }

    /**
     * Tests if writing humans works.
     */
    @Test //3
    void testWriteHumans() {
        assertFalse(deathNote.isNameWritten(H1));
        deathNote.writeName(H1);
        assertTrue(deathNote.isNameWritten(H1));
        assertFalse(deathNote.isNameWritten(H2));
        assertFalse(deathNote.isNameWritten(""));
    }

    /**
     * Tests if writing death cause works.
     * 
     * @throws InterruptedException if the thread is waked up while sleeping.
     */
    @Test //4
    void testDeathCause() throws InterruptedException {
        try {
            deathNote.writeDeathCause("chocked");
            fail("The method didn't thrown any IllegalStateException");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(H1);
        assertEquals("heart attack", deathNote.getDeathCause(H1));
        deathNote.writeName(H2);
        assertTrue(deathNote.writeDeathCause(C1));
        assertEquals(C1, deathNote.getDeathCause(H2));
        Thread.sleep(SLEEP);
        deathNote.writeDeathCause("murdered");
        assertEquals(C1, deathNote.getDeathCause(H2));
    }

    /**
     * Tests if writing details about death works.
     * 
     * @throws InterruptedException if the thread is waked up while sleeping.
     */
    @Test //5
    void testDeathDetails() throws InterruptedException {
        try {
            deathNote.writeDetails("killed with a gunshot in the back");
            fail("The method didn't thrown any IllegalStateException");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(H1);
        assertTrue(deathNote.getDeathDetails(H1).isEmpty());
        assertTrue(deathNote.writeDetails(D1));
        assertEquals(D1, deathNote.getDeathDetails(H1));
        deathNote.writeName(H2);
        Thread.sleep(LONGSLEEP);
        deathNote.writeDetails("neck hurted too much");
        assertEquals(D1, deathNote.getDeathDetails(H1));
    }
}
