package bpluschatter.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bpluschatter.task.TaskList;
import bpluschatter.ui.Ui;

/**
 * JUnit tests for parseMark() which is called by parseCommand().
 */
public class MarkTest {
    /**
     * Tests for successful mark command of unmarked task.
     */
    @Test
    public void testMarkUnmarked() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that correct task is marked");
        assertEquals("Well done! This task is done:\n"
                + "[T][X] Eat <LOW>", ui.toString(), "Check that correct message is sent.");
    }

    /**
     * Tests for successful mark command of marked task.
     */
    @Test
    public void testMarkMarked() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that correct task is marked");
        assertEquals("Well done! This task is done:\n"
                + "[T][X] Eat <LOW>", ui.toString(), "Check that correct message is sent.");
    }

    /**
     * Tests for successful unmark command of marked task.
     */
    @Test
    public void testUnmarkMarked() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 2", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that correct task is unmarked");
        assertEquals("Ok, this task is not done yet:\n"
                + "[T][ ] Eat <LOW>", ui.toString(), "Check that correct message is sent.");
    }

    /**
     * Tests for successful unmark command of unmarked task.
     */
    @Test
    public void testUnmarkUnmarked() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 2", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that correct task is unmarked");
        assertEquals("Ok, this task is not done yet:\n"
                + "[T][ ] Eat <LOW>", ui.toString(), "Check that correct message is sent.");
    }

    /**
     * Tests for mark command when index given is bigger than size of task list.
     */
    @Test
    public void testMarkIndexTooBig() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 3", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for unmark command when index given is bigger than size of task list.
     */
    @Test
    public void testUnmarkIndexTooBig() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 3", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for mark command when index is negative.
     */
    @Test
    public void testMarkIndexNegative() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark -1", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for unmark command when index is negative.
     */
    @Test
    public void testUnmarkIndexNegative() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark -1", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for mark command when index is not given.
     */
    @Test
    public void testMarkNoIndex() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for unmark command when index is not given.
     */
    @Test
    public void testUnmarkNoIndex() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for mark command when index given is not a number.
     */
    @Test
    public void testMarkIndexNotNumber() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark a", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][ ] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }

    /**
     * Tests for unmark command when index given is not a number.
     */
    @Test
    public void testUnmarkIndexNotNumber() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read HIGH", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat LOW", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark a", testTaskLists, ui);

        assertEquals("1.[T][ ] Read <HIGH>\n2.[T][X] Eat <LOW>\n", testTaskLists.toString(),
                "Check that list is correct");
        assertEquals("WRONG FORMAT :(\n"
                        + "Format: mark/unmark TASK_NUMBER\n"
                        + "You have 2 task(s)", ui.toString(),
                "Check that correct error message is shown");
    }
}
