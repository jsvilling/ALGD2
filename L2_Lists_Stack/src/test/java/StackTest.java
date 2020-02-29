import ch.fhnw.algd2.l2.stack.Stack;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackTest {

    @Test
    public void testPushElement() {
        // Given
        Stack<String> stack = new Stack<>();

        // When
        stack.push("data");

        // Then
        Assert.assertEquals("data", stack.top());
        Assert.assertFalse(stack.isEmpty());
    }

    @Test
    public void testPopElement() {
        // Given
        Stack<String> stack = new Stack<>();
        stack.push("data");

        // When
        String element = stack.pop();

        // Then
        Assert.assertEquals("data", element);
        Assert.assertTrue(stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        // Given
        Stack<String> stack = new Stack<>();

        // When
        stack.pop();
    }

    @Test
    public void testIsEmpty() {
        // Given
        Stack<String> stack = new Stack<>();

        // Then
        Assert.assertTrue(stack.isEmpty());
    }

}