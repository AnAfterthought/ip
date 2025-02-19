package bpluschatter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bpluschatter.enumerations.Priority;
import bpluschatter.task.TaskList;
import bpluschatter.task.ToDo;
import bpluschatter.ui.Ui;

public class ParserTest {
    /**
     * Tests for successful todo command.
     */
    @Test
    public void testParseToDoSuccess() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read medium", testTaskLists, ui);

        assertEquals(1, testTaskLists.getSize(), "Check size is correct after command");
        assertEquals("[T][ ] read <MEDIUM>", testTaskLists.get(0).toString(),
                "Check task list is correct after command");
    }

    /**
     * Tests for incorrect todo command.
     */
    @Test
    public void testParseToDoFailure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo", testTaskLists, ui);
        testTaskLists.list();

        assertEquals(0, testTaskLists.getSize(), "Check size is correct after error");
    }

    /**
     * Tests for successful deadline command.
     */
    @Test
    public void testParseDeadlineSuccess() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("deadline read /by 2020-01-01 1600 LOW", testTaskLists, ui);

        assertEquals(1, testTaskLists.getSize(), "Check size is correct after command");
        assertEquals("[D][ ] read <LOW> (by: Jan 1 2020 04:00 pm)", testTaskLists.get(0).toString(),
                "Check task list is correct after command");
    }

    /**
     * Tests for incorrect deadline command.
     */
    @Test
    public void testParseDeadlineFailure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("deadline read /by 2020-01-01 high", testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\nDate and time (24-hour) format: YYYY-MM-DD HHmm",
                ui.toString(),
                "Check correct error message is printed.");

        testTaskLists = parser.parseCommand("deadline read high", testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\n" + "Format: deadline TASK /by DATE TIME PRIORITY",
                ui.toString(),
                "Check correct error message is printed.");

        assertEquals(0, testTaskLists.getSize(), "Check size is correct after error");
    }

    /**
     * Tests for successful event command.
     */
    @Test
    public void testParseEventSuccess() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("event read /from 2020-01-01 1600 /to 2020-01-01 1800 HIGH",
                testTaskLists, ui);

        assertEquals(1, testTaskLists.getSize(), "Check size is correct after command");
        assertEquals("[E][ ] read <HIGH> (from: Jan 1 2020 04:00 pm to: Jan 1 2020 06:00 pm)",
                testTaskLists.get(0).toString(), "Check task list is correct after command");
    }

    /**
     * Tests for incorrect event command.
     */
    @Test
    public void testParseEventFailure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("event read from 2020-01-01 1600 to 2020-01-01 1800",
                testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\nFormat: event TASK /from DATE TIME /to DATE TIME PRIORITY",
                ui.toString(),
                "Check correct error message is printed.");

        testTaskLists = parser.parseCommand("event read /from 2020-01-01 /to 2020-01-1 1800 HIGH",
                testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\nDate and time (24-hour) format: YYYY-MM-DD HHmm",
                ui.toString(),
                "Check correct error message is printed.");

        assertEquals(0, testTaskLists.getSize(), "Check size is correct after error");
    }

    /**
     * Tests for successful delete command.
     */
    @Test
    public void testParseDeleteSuccess() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read high", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo eat high", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo exercise low", testTaskLists, ui);
        testTaskLists = parser.parseCommand("delete 2", testTaskLists, ui);

        assertEquals(2, testTaskLists.getSize(), "Check size is correct after delete");
        assertEquals("[T][ ] read <HIGH>", testTaskLists.get(0).toString(),
                "Check tasks are correct after delete");
        assertEquals("[T][ ] exercise <LOW>", testTaskLists.get(1).toString(),
                "Check tasks are correct after delete");
    }

    /**
     * Tests for incorrect delete command.
     */
    @Test
    public void testParseDeleteFail() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read high", testTaskLists, ui);
        testTaskLists = parser.parseCommand("delete 2", testTaskLists, ui);

        assertEquals(1, testTaskLists.getSize(), "Check size is correct after error");
        assertEquals("[T][ ] read <HIGH>", testTaskLists.get(0).toString(),
                "Check tasks are correct after error");
    }

    /**
     * Tests for successful find command using single keyword.
     */
    @Test
    public void testParseFind() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = testTaskLists.add(new ToDo("Read book", Priority.LOW));
        testTaskLists = testTaskLists.add(new ToDo("Eat", Priority.HIGH));
        testTaskLists = testTaskLists.add(new ToDo("Write book", Priority.HIGH));
        parser.parseCommand("find book", testTaskLists, ui);

        assertEquals("Here are the tasks I found:\n1.[T][ ] Write book <HIGH>\n2.[T][ ] Read book <LOW>\n",
                ui.toString(),
                "Check that correct tasks are found");
    }

    /**
     * Tests for successful find command using multiple keywords.
     */
    @Test
    public void testParseFindMultiple() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = testTaskLists.add(new ToDo("Read newspaper", Priority.LOW));
        testTaskLists = testTaskLists.add(new ToDo("Eat", Priority.HIGH));
        testTaskLists = testTaskLists.add(new ToDo("Write book", Priority.HIGH));
        parser.parseCommand("find book,read", testTaskLists, ui);

        assertEquals("Here are the tasks I found:\n1.[T][ ] Write book <HIGH>\n"
                        + "2.[T][ ] Read newspaper <LOW>\n",
                ui.toString(),
                "Check that correct tasks are found");
    }

    @Test
    public void testWrongPriority() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read max", testTaskLists, ui);
        assertEquals("WRONG PRIORITY :(\nRemember to add a priority level at the end of the command.\n"
                        + "The priority levels are HIGH, MEDIUM or LOW\n",
                ui.toString(),
                "Check correct error message is printed.");
    }
}
