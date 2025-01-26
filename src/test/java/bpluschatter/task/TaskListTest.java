package bpluschatter.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testAdd() {
        ArrayList<Task> tasksOne = new ArrayList<>();
        ArrayList<Task> tasksTwo = new ArrayList<>();
        tasksOne.add(new ToDo("Read"));
        tasksTwo.add(new ToDo("Read"));
        TaskList testTaskList = new TaskList(tasksOne);
        tasksTwo.add(new ToDo("Eat"));
        testTaskList = testTaskList.add(new ToDo("Eat"));

        assertEquals(2, testTaskList.size(), "Check that size is correct after adding task");
        assertEquals(new TaskList(tasksTwo).get(0).toString(), testTaskList.get(0).toString(),
                "Check that TaskList is correct after adding task");
        assertEquals(new TaskList(tasksTwo).get(1).toString(), testTaskList.get(1).toString(),
                "Check that TaskList is correct after adding task");
    }

    @Test
    public void testDelete() {
        TaskList testTaskList = new TaskList();
        testTaskList = testTaskList.add(new ToDo("Read")).add(new ToDo("Eat"));
        testTaskList = testTaskList.remove(0);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Eat"));

        assertEquals(1, testTaskList.size(), "Check that size is correct after deleting task");
        assertEquals(new TaskList(tasks).get(0).toString(), testTaskList.get(0).toString(),
                "Check that TaskList is correct after deleting task");
    }

    @Test
    public void testGet() {
        TaskList testTaskList = new TaskList();
        testTaskList = testTaskList.add(new ToDo("Read")).add(new ToDo("Eat"));
        testTaskList = testTaskList.remove(0);
        testTaskList = testTaskList.add(new ToDo("Exercise")).add(new ToDo("Clean room"));

        assertEquals("Exercise", testTaskList.get(1).toString(),
                "Check that TaskList gets correct task");
    }
}
