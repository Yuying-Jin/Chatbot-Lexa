package Chatbot.CustomADT;


/**
 * Generic list interface for storing a collection of elements.
 * @param <T> The type of elements stored in the list.
 */
public interface List<T> {
    /**
     * Adds an element to the end of the list.
     * @param entry The element to add.
     */
    void add(T entry);

    /**
     * gets the element at the specified index.
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    T get(int index);

    /**
     * the number of elements in the list.
     * @return The number of elements in the list.
     */
    int size();

    /**
     * check if the list is empty.
     * @return true if the list is empty, false otherwise.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    boolean isEmpty();

    /**
     * removes the element at the specified index.
     * @param index The index of the element to remove.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    void remove(int index);

    /**
     * removes the specified element from the list.
     * @param entry The element to remove.
     * @return true if the element was removed, false otherwise.
     */
    boolean remove(T entry);

    /**
     * clears the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    void clear();

    /**
     * checks if the list contains the specified element.
     * @param entry The element to check for.
     * @return true if the list contains the element, false otherwise.
     */
    boolean contains(T entry);

    /*
     * toArray method
     * @return an array containing all elements in the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    T[] toArray();

    /**
     * returns the index of the specified element.
     * @param entry The element to find.
     * @return The index of the element, or -1 if not found.
     */
    int indexOf(T entry);

    /**
     * toString method
     * @return a string representation of the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    String toString();
    
}
