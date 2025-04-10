package Chatbot.CustomADT;
import java.util.Arrays;
import Chatbot.CustomADT.List;

/**
 * A simple implementation of a dynamic array list.
 * @param <T> The type of elements stored in the list.
 */
public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    /**
     * Default constructor with initial capacity.
     */
    public ArrayList() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public void add(T entry) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = entry;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public boolean contains(T entry) {
        return indexOf(entry) >= 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(elements, size, Object[].class);
    }

    @Override
    public int indexOf(T entry) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(entry)) {
                return i;
            }
        }
        return -1;
    }

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

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    @SuppressWarnings("unchecked")
    private T elementAt(int index) {
        return (T) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
    }
}