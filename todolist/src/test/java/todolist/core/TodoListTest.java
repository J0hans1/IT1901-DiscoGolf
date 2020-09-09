package todolist.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoListTest {

    private TodoList newList;

    @BeforeEach
    public void setUp() {
        newList = new TodoList();
    }

    // tests for getCheckedItems

    @Test
    public void testGetCheckedTodoItems_emptyList() {
        assertTrue(newList.getCheckedTodoItems().isEmpty());
    }

    @Test
    public void testGetCheckedTodoItems_oneUncheckedItem() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        assertTrue(newList.getCheckedTodoItems().isEmpty());
    }

    @Test
    public void testGetCheckedTodoItems_oneCheckedItem() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        item.setChecked(true);
        Collection<TodoItem> items = newList.getCheckedTodoItems();
        assertEquals(1, items.size());
        assertSame(item, items.iterator().next());
    }

    @Test
    public void testGetCheckedTodoItems_oneItemCheckedAfterAdding() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        assertTrue(newList.getCheckedTodoItems().isEmpty());
        item.setChecked(true);
        Collection<TodoItem> items = newList.getCheckedTodoItems();
        assertEquals(1, items.size());
        assertSame(item, items.iterator().next());
    }

    // tests for getUncheckedTodoItems

    @Test
    public void testGetUncheckedTodoItems_emptyList() {
        assertTrue(newList.getUncheckedTodoItems().isEmpty());
    }

    @Test
    public void testGetUncheckedTodoItems_oneUncheckedItem() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        Collection<TodoItem> items = newList.getUncheckedTodoItems();
        assertEquals(1, items.size());
        assertSame(item, items.iterator().next());
    }

    @Test
    public void testGetUncheckedTodoItems_oneCheckedItem() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        item.setChecked(true);
        assertTrue(newList.getUncheckedTodoItems().isEmpty());
    }

    @Test
    public void testGetUncheckedTodoItems_oneItemCheckedAfterAdding() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        Collection<TodoItem> items = newList.getUncheckedTodoItems();
        assertEquals(1, items.size());
        assertEquals(item, items.iterator().next());
        item.setChecked(true);
        assertTrue(newList.getUncheckedTodoItems().isEmpty());
    }

    // tests for getTodoItems

    @Test
    public void testGetTodoItems_emptyList() {
        assertTrue(newList.getTodoItems().isEmpty());
    }

    @Test
    public void testGetTodoItems_oneUncheckedItem() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        Collection<TodoItem> items = newList.getTodoItems();
        assertEquals(1, items.size());
        assertSame(item, items.iterator().next());
    }

    @Test
    public void testGetTodoItems_oneCheckedItem() {
        TodoItem item = new TodoItem();
        item.setChecked(true);
        newList.addTodoItem(item);
        Collection<TodoItem> items = newList.getTodoItems();
        assertEquals(1, items.size());
        assertSame(item, items.iterator().next());
    }

    @Test
    public void testGetTodoItems_oneItemCheckedAfterAdding() {
        TodoItem item = new TodoItem();
        newList.addTodoItem(item);
        Collection<TodoItem> items1 = newList.getTodoItems();
        assertEquals(1, items1.size());
        assertEquals(item, items1.iterator().next());
        item.setChecked(true);
        Collection<TodoItem> items2 = newList.getTodoItems();
        assertEquals(1, items2.size());
        assertSame(item, items2.iterator().next());
    }

    // test iterator

    private void checkIterator(Iterator<TodoItem> it, TodoItem... items) {
        int i = 0;
        while (it.hasNext()) {
            assertTrue(i < items.length);
            assertSame(items[i], it.next());
            i++;
        }
        assertTrue(i == items.length);
    }

    @Test
    public void testIterator_addingAndRemovingItems() {
        checkIterator(newList.iterator());
        TodoItem item1 = new TodoItem(), item2 = new TodoItem(), item3 = new TodoItem();
        newList.addTodoItem(item1);
        checkIterator(newList.iterator(), item1);
        newList.addTodoItem(item2);
        checkIterator(newList.iterator(), item1, item2);
        newList.addTodoItem(item3);
        checkIterator(newList.iterator(), item1, item2, item3);
        newList.removeTodoItem(item2);
        checkIterator(newList.iterator(), item1, item3);
        newList.removeTodoItem(item1);
        checkIterator(newList.iterator(), item3);
        newList.removeTodoItem(item3);
        checkIterator(newList.iterator());
    }
}
