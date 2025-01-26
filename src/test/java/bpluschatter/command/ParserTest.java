package bpluschatter.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bpluschatter.ui.Ui;

import bpluschatter.task.TaskList;

public class ParserTest {
    @Test
    public void testParseToDo_success() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read", testTaskLists, ui);

        assertEquals(1, testTaskLists.size(), "Check size is correct after command");
        assertEquals("[T][ ] read", testTaskLists.get(0).toString(),
                "Check task list is correct after command");
    }

    @Test
    public void testParseToDo_failure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo", testTaskLists, ui);
        testTaskLists.list();

        assertEquals(0, testTaskLists.size(), "Check size is correct after error");
    }

    @Test
    public void testParseDeadline_success() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("deadline read /by 2020-01-01 1600", testTaskLists, ui);

        assertEquals(1, testTaskLists.size(), "Check size is correct after command");
        assertEquals("[D][ ] read (by: Jan 1 2020 04:00 pm)", testTaskLists.get(0).toString(),
                "Check task list is correct after command");
    }

    @Test
    public void testParseDeadline_failure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("deadline read /by 2020-01-01", testTaskLists, ui);
        testTaskLists = parser.parseCommand("deadline read", testTaskLists, ui);

        assertEquals(0, testTaskLists.size(), "Check size is correct after error");
    }

    @Test
    public void testParseEvent_success() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("event read /from 2020-01-01 1600 /to 2020-01-01 1800",
                testTaskLists, ui);

        assertEquals(1, testTaskLists.size(), "Check size is correct after command");
        assertEquals("[E][ ] read (from: Jan 1 2020 04:00 pm to: Jan 1 2020 06:00 pm)",
                testTaskLists.get(0).toString(), "Check task list is correct after command");
    }

    @Test
    public void testParseEvent_failure() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("event read from 2020-01-01 1600 to 2020-01-01 1800",
                testTaskLists, ui);
        testTaskLists = parser.parseCommand("event read /from 2020-01-01 /to 2020-01-1 1800",
                testTaskLists, ui);

        assertEquals(0, testTaskLists.size(), "Check size is correct after error");
    }

    @Test
    public void testParseDelete_success() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo eat", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo exercise", testTaskLists, ui);
        testTaskLists = parser.parseCommand("delete 2", testTaskLists, ui);

        assertEquals(2, testTaskLists.size(), "Check size is correct after delete");
        assertEquals("[T][ ] read", testTaskLists.get(0).toString(),
                "Check tasks are correct after delete");
        assertEquals("[T][ ] exercise", testTaskLists.get(1).toString(),
                "Check tasks are correct after delete");
    }

    @Test
    public void testParseDelete_fail() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo read", testTaskLists, ui);
        testTaskLists = parser.parseCommand("delete 2", testTaskLists, ui);

        assertEquals(1, testTaskLists.size(), "Check size is correct after error");
        assertEquals("[T][ ] read", testTaskLists.get(0).toString(),
                "Check tasks are correct after error");
    }

    @Test
    public void testParseMark() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 1", testTaskLists, ui);

        assertEquals("[T][X] Read", testTaskLists.get(0).toString(),
                "Check that correct task is marked");
        assertEquals("[T][ ] Eat", testTaskLists.get(1).toString(),
                "Check that correct task is marked");
    }

    @Test
    public void testParseUnmark() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList testTaskLists = new TaskList();

        testTaskLists = parser.parseCommand("todo Read", testTaskLists, ui);
        testTaskLists = parser.parseCommand("todo Eat", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 1", testTaskLists, ui);
        testTaskLists = parser.parseCommand("mark 2", testTaskLists, ui);
        testTaskLists = parser.parseCommand("unmark 2", testTaskLists, ui);

        assertEquals("[T][X] Read", testTaskLists.get(0).toString(),
                "Check that correct task is unmarked");
        assertEquals("[T][ ] Eat", testTaskLists.get(1).toString(),
                "Check that correct task is unmarked");
    }
}
