package bpluschatter.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bpluschatter.enumerations.Priority;

public class TaskListTest {
    /**
     * Tests for successful addition of tasks.
     */
    @Test
    public void testAddTask() {
        ArrayList<Task> tasksOne = new ArrayList<>();
        ArrayList<Task> tasksTwo = new ArrayList<>();
        tasksOne.add(new ToDo("Read", Priority.HIGH));
        tasksTwo.add(new ToDo("Read", Priority.HIGH));
        TaskList testTaskList = new TaskList(tasksOne);
        tasksTwo.add(new ToDo("Eat", Priority.HIGH));
        testTaskList = testTaskList.add(new ToDo("Eat", Priority.HIGH));

        assertEquals(2, testTaskList.getSize(), "Check that size is correct after adding task");
        assertEquals(new TaskList(tasksTwo).get(0).toString(), testTaskList.get(0).toString(),
                "Check that TaskList is correct after adding task");
        assertEquals(new TaskList(tasksTwo).get(1).toString(), testTaskList.get(1).toString(),
                "Check that TaskList is correct after adding task");
    }

    /**
     * Tests for successful removal of task.
     */
    @Test
    public void testRemoveTask() {
        TaskList testTaskList = new TaskList();
        testTaskList = testTaskList.add(new ToDo("Read", Priority.HIGH))
                .add(new ToDo("Eat", Priority.HIGH));
        testTaskList = testTaskList.remove(0);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Eat", Priority.HIGH));

        assertEquals(1, testTaskList.getSize(), "Check that size is correct after deleting task");
        assertEquals(new TaskList(tasks).get(0).toString(), testTaskList.get(0).toString(),
                "Check that TaskList is correct after deleting task");
    }

    /**
     * Tests for successful retrieval of task.
     */
    @Test
    public void testGet() {
        TaskList testTaskList = new TaskList();
        testTaskList = testTaskList.add(new ToDo("Read", Priority.HIGH))
                .add(new ToDo("Eat", Priority.HIGH));
        testTaskList = testTaskList.remove(0);
        testTaskList = testTaskList.add(new ToDo("Exercise", Priority.HIGH))
                .add(new ToDo("Clean room", Priority.HIGH));

        assertEquals("[T][ ] Exercise <HIGH>", testTaskList.get(1).toString(),
                "Check that TaskList gets correct task");
    }

    /**
     * Tests that tasks are listed in descending order of priority.
     */
    @Test
    public void testToString() {
        TaskList testTaskList = new TaskList();
        testTaskList = testTaskList.add(new ToDo("Read", Priority.MEDIUM))
                .add(new ToDo("Eat", Priority.LOW))
                .add(new ToDo("Exercise", Priority.HIGH));

        assertEquals("1.[T][ ] Exercise <HIGH>\n"
                + "2.[T][ ] Read <MEDIUM>\n"
                + "3.[T][ ] Eat <LOW>\n", testTaskList.toString(), "Check that tasks output is correct.");
    }
}
