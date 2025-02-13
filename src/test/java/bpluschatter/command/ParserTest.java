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
    public void testParseToDo_success() {
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
    public void testParseToDo_failure() {
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
    public void testParseDeadline_success() {
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
    public void testParseDeadline_failure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("deadline read /by 2020-01-01 high", testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\nDate and time (24-hour) format: YYYY-MM-DD HHmm",
                ui.toString(),
                "Check correct error message is printed.");

        testTaskLists = parser.parseCommand("deadline read high", testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\n" + "Format: deadline <task> /by <date> <time> <priority>",
                ui.toString(),
                "Check correct error message is printed.");

        assertEquals(0, testTaskLists.getSize(), "Check size is correct after error");
    }

    /**
     * Tests for successful event command.
     */
    @Test
    public void testParseEvent_success() {
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
    public void testParseEvent_failure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("event read from 2020-01-01 1600 to 2020-01-01 1800",
                testTaskLists, ui);
        assertEquals("WRONG FORMAT :(\nFormat: event <task> /from <date> <time> /to <date> <time> <priority>",
                ui.toString(),
                "Check correct error message is printed.");

        testTaskLists = parser.parseCommand("event read /from 2020-01-01 /to 2020-01-1 1800",
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
    public void testParseDelete_success() {
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
    public void testParseDelete_fail() {
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
     * Tests for successful mark command.
     */
    @Test
    public void testParseMark() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 1", testTaskLists, ui);

        assertEquals("[T][X] Read <HIGH>", testTaskLists.get(0).toString(),
                "Check that correct task is marked");
        assertEquals("[T][ ] Eat <LOW>", testTaskLists.get(1).toString(),
                "Check that correct task is marked");
    }

    /**
     * Tests for successful unmark command.
     */
    @Test
    public void testParseUnmark() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read high", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat low", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 1", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 2", testTaskLists, ui);

        assertEquals("[T][X] Read <HIGH>", testTaskLists.get(0).toString(),
                "Check that correct task is unmarked");
        assertEquals("[T][ ] Eat <LOW>", testTaskLists.get(1).toString(),
                "Check that correct task is unmarked");
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
        assertEquals("WRONG PRIORITY :(\nThe priority levels are HIGH, MEDIUM or LOW\n",
                ui.toString(),
                "Check correct error message is printed.");
    }
}
