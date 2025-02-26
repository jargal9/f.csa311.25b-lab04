package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment mQueue = new LinkedIntQueue(); and uncomment mQueue = new ArrayIntQueue();
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        mQueue = new LinkedIntQueue();
    //   mQueue = new ArrayIntQueue();
        testList = new ArrayList<>(List.of(1, 2, 3));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    // Queue хоосон биш үед isEmpty() зөв false буцааж буй эсэх
    @Test
    public void testNotEmpty() {
        // 1. Эхэндээ Queue хоосон байх ёстой
        assertTrue(mQueue.isEmpty());
    
        // 2. Queue-д өгөгдөл нэмэх
        mQueue.enqueue(42);
    
        // 3. Queue одоо хоосон биш байх ёстой
        assertFalse(mQueue.isEmpty());
    
        // 4. Queue-д дахин өгөгдөл нэмэх
        mQueue.enqueue(99);
        assertFalse(mQueue.isEmpty());
    
        // 5. Бүх элементийг гаргаж queue-г хоослох
        mQueue.dequeue();
        assertFalse(mQueue.isEmpty()); // Нэг элемент үлдсэн
    
        mQueue.dequeue();
        assertTrue(mQueue.isEmpty()); // Одоо бүхэлдээ хоосон болсон
    }
    

    @Test
    public void testPeekEmptyQueue() {
        mQueue.peek();
    }

    @Test
    public void testClear() {
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        mQueue.clear();
        assertTrue(mQueue.isEmpty());
        assertEquals(0, mQueue.size());
        //assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // Queue-д өгөгдөл нэмэх
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        mQueue.enqueue(30);
    
        // peek() нь эхний элемент (10)-г буцаах ёстой
        assertEquals(Integer.valueOf(10), mQueue.peek());
    
        // peek() нь queue-ийн хэмжээнд нөлөөлөх ёсгүй
        assertEquals(3, mQueue.size());
    
        // Дахин peek() хийхэд үр дүн өөрчлөгдөх ёсгүй
        assertEquals(Integer.valueOf(10), mQueue.peek());
    }    

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeue() {
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
        }
    
        //FIFO зарчмаар зөв хасагдаж буй эсэхийг шалгах
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), mQueue.dequeue()); // Оруулсан дарааллаараа гарах ёстой
    
            // Хэрэв queue-д элемент үлдсэн бол peek() дараагийн элемент рүү заах ёстой
            if (i < testList.size() - 1) {
                assertEquals(testList.get(i + 1), mQueue.peek());
            }
        }
    
        //Queue хоосон үед dequeue() нь null буцаах ёстой
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testEnsureCapacityExpansion() {
            ArrayIntQueue queue = new ArrayIntQueue();
            
            // Эхний багтаамж 10 тул түүнээс их элемент нэмнэ
            for (int i = 0; i < 11; i++) {
                queue.enqueue(i);
            }

            // 11 дэх элемент нэмэгдсэн тул багтаамж өссөн эсэхийг шалгах
            assertEquals(11, queue.size());
            assertEquals(Integer.valueOf(0), queue.peek()); // 🛠 Эхний элемент зөв үлдсэн эсэх
    }
} 