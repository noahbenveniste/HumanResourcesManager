package edu.ncsu.csc316.hr.adt;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for LinkedQueue
 * @author Noah Benveniste
 */
public class LinkedQueueTest {

    /**
     * Test method for constructor
     */
    @Test
    public void testLinkedQueue() {
        LinkedQueue<String> q = null;
        //Invalid construction using a negative capacity
        try {
            q = new LinkedQueue<String>(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertNull(q);
        }
        
        //Valid construction
        q = new LinkedQueue<String>(10);
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    /**
     * Test method for enqueue()
     */
    @Test
    public void testEnqueue() {
        //Create a queue with space for 5
        LinkedQueue<String> q = new LinkedQueue<String>(5);
        
        String s1 = "apple";
        String s2 = "banana";
        String s3 = "cherry";
        String s4 = "date";
        String s5 = "grape";
        String s6 = "lemon";
        
        q.enqueue(s1);
        assertEquals(1, q.size());
        
        q.enqueue(s2);
        assertEquals(2, q.size());
        
        q.enqueue(s3);
        assertEquals(3, q.size());
        
        q.enqueue(s4);
        assertEquals(4, q.size());
        
        q.enqueue(s5);
        assertEquals(5, q.size());
        
        //Try to add an element when the queue is full
        try {
            q.enqueue(s6);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(5, q.size());
        }
    }

    /**
     * Test method for dequeue()
     */
    @Test
    public void testDequeue() {
        //Create a queue with space for 5
        LinkedQueue<String> q = new LinkedQueue<String>(5);
        
        String s1 = "apple";
        String s2 = "banana";
        String s3 = "cherry";
        String s4 = "date";
        String s5 = "grape";
        
        q.enqueue(s1);
        assertEquals(1, q.size());
        
        q.enqueue(s2);
        assertEquals(2, q.size());
        
        q.enqueue(s3);
        assertEquals(3, q.size());
        
        q.enqueue(s4);
        assertEquals(4, q.size());
        
        q.enqueue(s5);
        assertEquals(5, q.size());
        
        //Test that elements are dequeued in proper order
        assertEquals(s1, q.dequeue());
        assertEquals(4, q.size());
        assertEquals(s2, q.dequeue());
        assertEquals(3, q.size());
        assertEquals(s3, q.dequeue());
        assertEquals(2, q.size());
        assertEquals(s4, q.dequeue());
        assertEquals(1, q.size());
        assertEquals(s5, q.dequeue());
        assertEquals(0, q.size());
    }

    /**
     * Test method for isEmpty()
     */
    @Test
    public void testIsEmpty() {
        //Create a queue with space for 5
        LinkedQueue<String> q = new LinkedQueue<String>(2);
        
        String s1 = "apple";
        String s2 = "banana";
        
        assertTrue(q.isEmpty());
        
        q.enqueue(s1);
        assertEquals(1, q.size());
        
        q.enqueue(s2);
        assertEquals(2, q.size());
        
        assertFalse(q.isEmpty());
        
        //Try removing elements to see if the queue is empty
        q.dequeue();
        assertFalse(q.isEmpty());
        
        q.dequeue();
        assertTrue(q.isEmpty());
    }

    /**
     * Test method for size()
     */
    @Test
    public void testSize() {
        //Create a queue with space for 5
        LinkedQueue<String> q = new LinkedQueue<String>(5);
        
        String s1 = "apple";
        String s2 = "banana";
        String s3 = "cherry";
        String s4 = "date";
        String s5 = "grape";
        
        q.enqueue(s1);
        assertEquals(1, q.size());
        
        q.enqueue(s2);
        assertEquals(2, q.size());
        
        q.enqueue(s3);
        assertEquals(3, q.size());
        
        q.enqueue(s4);
        assertEquals(4, q.size());
        
        q.enqueue(s5);
        assertEquals(5, q.size());
    }

    /**
     * Test method for setCapacity()
     */
    @Test
    public void testSetCapacity() {
      //Create a queue with space for 5
        LinkedQueue<String> q = new LinkedQueue<String>(5);
        
        String s1 = "apple";
        String s2 = "banana";
        String s3 = "cherry";
        String s4 = "date";
        String s5 = "grape";
        String s6 = "lemon";
        
        q.enqueue(s1);
        assertEquals(1, q.size());
        
        q.enqueue(s2);
        assertEquals(2, q.size());
        
        q.enqueue(s3);
        assertEquals(3, q.size());
        
        q.enqueue(s4);
        assertEquals(4, q.size());
        
        q.enqueue(s5);
        assertEquals(5, q.size());
        
        //Try to add an element when the queue is full
        try {
            q.enqueue(s6);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(5, q.size());
        }
        
//        //Increase the capacity and test that another element can be added
//        q.setCapacity(6);
//        try {
//            q.enqueue(s6);
//        } catch (IllegalArgumentException e) {
//            fail();
//        }
//        assertEquals(6, q.size());
//        
//        //Try setting the capacity to a negative value
//        try {
//            q.setCapacity(-1);
//            fail();
//        } catch (IllegalArgumentException e) {
//            assertEquals(6, q.size());
//        }
//        
//        //Try setting the capacity to a value less than size
//        try {
//            q.setCapacity(5);
//            fail();
//        } catch (IllegalArgumentException e) {
//            assertEquals(6, q.size());
//        }
//        
//        //Check that the position of the values in the queue weren't changed
//        assertEquals(s1, q.dequeue());
//        assertEquals(5, q.size());
//        assertEquals(s2, q.dequeue());
//        assertEquals(4, q.size());
//        assertEquals(s3, q.dequeue());
//        assertEquals(3, q.size());
//        assertEquals(s4, q.dequeue());
//        assertEquals(2, q.size());
//        assertEquals(s5, q.dequeue());
//        assertEquals(1, q.size());
//        assertEquals(s6, q.dequeue());
//        assertEquals(0, q.size());
    }

}
