package Chatbot.CustomADT;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.reflect.Array;

/**
 * A simple implementation of a dynamic array list.
 * @param <T> The type of elements stored in the list.
 */
public class ArrayList<T> implements List<T>, Iterable<T> {
    private Object[] elements;
    private int size;

    /**
     * Default constructor with initial capacity.
     */
    @SuppressWarnings("unchecked")
	public ArrayList() {
        elements = new Object[10];
        size = 0;
    }

    /*
     * adds an entry to the list
     * @param entry the entry to add
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(T entry) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = entry;
    }

    /**
     * gets the element at the specified index
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    /**
     * gets the number of elements in the list
     * @return the number of elements in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * checks if the list is empty
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * removes the element at the specified index
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    /*
     * remove entry from the list
     * @param entry the entry to remove
     * @return true if the entry was removed, false otherwise
     */
    @Override
    public boolean remove(T entry) {
        int index = indexOf(entry);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }


    /**
     * clears the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */ 
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    /**
     * checks if the list contains the specified entry
     * @param entry the entry to check for
     * @return true if the list contains the entry, false otherwise
     */
    @Override
    public boolean contains(T entry) {
        return indexOf(entry) >= 0;
    }

    /**
     * converts the list to an array
     * @return an array containing all elements in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
    	 T[] array = (T[]) Array.newInstance((Class<T>) Object.class, size);
         System.arraycopy(elements, 0, array, 0, size);
         return array;
    }

    /**
     * returns the index of the specified entry
     * @param entry the entry to find
     * @return the index of the entry, or -1 if not found
     */
    @Override
    public int indexOf(T entry) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * toString method
     * @return a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // ===== Utility Methods =====

    /**
     * Resizes the internal array to double its current size.
     */

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) elements[index];
    }

    /**
     * Checks if the index is within bounds.
     * @param index The index to check.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }

    
	/**
	 * Returns an iterator over the elements in this list.
	 * 
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	/*
	 * * An iterator for the ArrayList.
	 */
	private class ArrayListIterator implements Iterator<T> {
        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            return elementAt(cursor++);
        }
    }
}