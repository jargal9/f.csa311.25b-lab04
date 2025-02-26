package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 *
 * TODO: This implementation contains three bugs! Use your tests to determine the
 * source of the bugs and correct them!
 *
 * @author Alex Lockwood
 * @author Ye Lu
 */
public class ArrayIntQueue implements IntQueue {

    /**
     * An array holding this queue's data
     */
    private int[] elementData;

    /**
     * Index of the next dequeue-able value
     */
    private int head;

    /**
     * Current size of queue
     */
    private int size;

    /**
     * The initial size for new instances of ArrayQueue
     */
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructs an empty queue with an initial capacity of ten.
     */
    public ArrayIntQueue() {
        elementData = new int[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    /** {@inheritDoc} */
    public void clear() {
        size = 0;
        head = 0;
    }
    

    /** {@inheritDoc} */
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        Integer value = elementData[head];
        head = (head + 1) % elementData.length;
        size--;
        return value;
    }

    /** {@inheritDoc} */
    public boolean enqueue(Integer value) {
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    /** {@inheritDoc} */
    public boolean isEmpty() {
        return size == 0;
    }

    /** {@inheritDoc} */
    public Integer peek() {
        return elementData[head];
    }

    /** {@inheritDoc} */
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int newCapacity = 2 * elementData.length + 1;
            int[] newData = new int[newCapacity];
    
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[(head + i) % elementData.length];
            }
    
            elementData = newData;
            head = 0;
        }
    }   
}


/**
 * ХИЙХ:
 * 1. {@link LinkedIntQueue}-д алдаа байхгүй. Бид танд туршилтын зарим жишээг өгсөн.
 * Техникийн тестийн аргаар IntQueue интерфэйсийг туршиж үзэхийн тулд өөрийн нэгжийн тестийг бичээрэй
 * mQueue ашиглах = шинэ LinkedIntQueue();
 *
 * 2.
 * mQueue = new LinkedIntQueue(); гэж коммент бичиж, mQueue = new ArrayIntQueue(); гэсэн тайлбарыг арилгана уу.
 * ArrayIntQueue-г туршиж, {@link ArrayIntQueue} анги дахь алдааг олохын тулд 1-р хэсгийн туршилтуудыг ашиглана уу.
 * ArrayIntQueue-ийн хэрэгжилтийг бүтцийн туршилтын аргаар шалгахын тулд илүү олон нэгж тест бичнэ үү.
 * ArrayIntQueue-ийн 100% шугамын хамрах хүрээг хангах зорилготой
 *
 * @ Зохиогч Алекс Локвуд, Жорж Гуо, Терри Ли
 */