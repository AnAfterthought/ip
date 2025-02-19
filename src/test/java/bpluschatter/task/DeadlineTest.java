package bpluschatter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bpluschatter.enumerations.Priority;

public class DeadlineTest {
    /**
     * Tests that the correct string for saving an unmarked deadline into a file is generated.
     */
    @Test
    public void testToFileFormatUnmarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.HIGH, by);

        assertEquals("D | 0 | Homework | 2025-02-25 2359 | HIGH", deadline.toFileFormat(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for saving a marked deadline into a file is generated.
     */
    @Test
    public void testToFileFormatMarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.LOW, by);
        deadline.setIsDone(true);

        assertEquals("D | 1 | Homework | 2025-02-25 2359 | LOW", deadline.toFileFormat(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for an umarked deadline is generated.
     */
    @Test
    public void testToStringUnmarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.MEDIUM, by);

        assertEquals("[D][ ] Homework <MEDIUM> (by: Feb 25 2025 11:59 pm)", deadline.toString(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for a marked deadline is generated.
     */
    @Test
    public void testToStringMarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.MEDIUM, by);
        deadline.setIsDone(true);

        assertEquals("[D][X] Homework <MEDIUM> (by: Feb 25 2025 11:59 pm)", deadline.toString(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that deadline can identify date that is the same as by.
     */
    @Test
    public void testIsSameDateTrue() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.MEDIUM, by);
        LocalDateTime other = LocalDateTime.parse("2025-02-25 0000", dateTimeFormatter);

        assertTrue(deadline.isSameDate(other));
    }

    /**
     * Tests that deadline can identify date that is not the same as by.
     */
    @Test
    public void testIsSameDateFalse() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse("2025-02-25 2359", dateTimeFormatter);
        Deadline deadline = new Deadline("Homework", Priority.MEDIUM, by);
        LocalDateTime before = LocalDateTime.parse("2025-02-20 0000", dateTimeFormatter);
        LocalDateTime after = LocalDateTime.parse("2025-02-27 0000", dateTimeFormatter);

        assertFalse(deadline.isSameDate(before));
        assertFalse(deadline.isSameDate(after));
    }
}
