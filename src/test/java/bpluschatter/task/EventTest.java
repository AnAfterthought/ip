package bpluschatter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import bpluschatter.enumerations.Priority;

public class EventTest {
    /**
     * Tests that the correct string for saving an unmarked event into a file is generated.
     */
    @Test
    public void testToFileFormatUnmarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);

        assertEquals("E | 0 | Meeting | 2025-02-25 1400 | 2025-02-25 1600 | HIGH", event.toFileFormat(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for saving a marked event into a file is generated.
     */
    @Test
    public void testToFileFormatMarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);
        event.setIsDone(true);

        assertEquals("E | 1 | Meeting | 2025-02-25 1400 | 2025-02-25 1600 | HIGH", event.toFileFormat(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for an umarked event is generated.
     */
    @Test
    public void testToStringUnmarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);

        assertEquals("[E][ ] Meeting <HIGH> (from: Feb 25 2025 02:00 pm to: Feb 25 2025 04:00 pm)",
                event.toString(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that the correct string for a marked event is generated.
     */
    @Test
    public void testToStringMarked() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);
        event.setIsDone(true);

        assertEquals("[E][X] Meeting <HIGH> (from: Feb 25 2025 02:00 pm to: Feb 25 2025 04:00 pm)",
                event.toString(),
                "Check that format of string for saving is correct.");
    }

    /**
     * Tests that event can identify date that is the same as from.
     */
    @Test
    public void testIsSameDateTrue() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);
        LocalDateTime other = LocalDateTime.parse("2025-02-25 0000", dateTimeFormatter);

        assertTrue(event.isSameDate(other));
    }

    /**
     * Tests that event can identify date that is not the same as by.
     */
    @Test
    public void testIsSameDateFalse() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse("2025-02-25 1400", dateTimeFormatter);
        LocalDateTime to = LocalDateTime.parse("2025-02-25 1600", dateTimeFormatter);
        Event event = new Event("Meeting", Priority.HIGH, from, to);
        LocalDateTime before = LocalDateTime.parse("2025-02-20 0000", dateTimeFormatter);
        LocalDateTime after = LocalDateTime.parse("2025-02-27 0000", dateTimeFormatter);

        assertFalse(event.isSameDate(before));
        assertFalse(event.isSameDate(after));
    }
}
