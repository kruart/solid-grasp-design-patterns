package app;

import static org.junit.Assert.*;
import org.junit.Test;

public class NodeTest {

    /**
     * Only the first level of objects after shallow copy will point out on different objects in memory,
     * deeper levels will have the same references
     */
    @Test
    public void testShallowCopy() {
        Node node = testData();
        Node copy = node.shallowCopy(node);

        assertNotSame(node, copy);
        assertEquals(node.val, copy.val);

        assertSame(node.next, copy.next);
        assertEquals(node.next.val, copy.next.val);

        assertSame(node.next.next, copy.next.next);
        assertEquals(node.next.next.val, copy.next.next.val);

        // also change val for copy.next object since reference node.next == copy.next
        node.next.val = -1;
        assertEquals(node.next.val, copy.next.val);
    }

    /**
     * All level of objects after deep copy should point out on different objects in memory
     */
    @Test
    public void testDeepCopy() {
        Node node = testData();
        Node copy = node.deepCopy(node);

        assertNotSame(node, copy);
        assertEquals(node.val, copy.val);

        assertNotSame(node.next, copy.next);
        assertEquals(node.next.val, copy.next.val);

        assertNotSame(node.next.next, copy.next.next);
        assertEquals(node.next.next.val, copy.next.next.val);

        // only change val for node.next object since references node.next != copy.next
        node.next.val = -1;
        assertNotEquals(node.next.val, copy.next.val);
    }

    private Node testData() {
        return new Node(new Node(new Node(null, 3), 2), 1);   // 1 -> 2 -> 3
    }
}