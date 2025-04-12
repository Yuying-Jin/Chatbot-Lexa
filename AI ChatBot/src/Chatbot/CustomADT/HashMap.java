package Chatbot.CustomADT;

import application.Configure;
import application.Recipe;

public class HashMap<K, V> implements Map<K, V> {
	
	// Entry class to represent key-value pairs in the hash table.
	protected class Entry<K, V> {
        K key;
        V value;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
    }
	
	// Hash table represented as an array of linked lists (ArrayList).
	protected ArrayList<Entry>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
	
    
	public HashMap() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap(int capacity) {
		this.table = new ArrayList[capacity];
		for (int i = 0; i < capacity; i++) {
			table[i] = new ArrayList<>();
		}
		this.size = 0;
	}
	
	/*
	 * * Hash function to compute the index for a given key.
	 * 
	 * @param key The key to hash.
	 * 
	 * @return The index in the hash table.
	 */
	protected int hash(K key) {
		return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }
	
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the hash table is empty.
	 * 
	 * @return true if the hash table is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isEmpty()) {
				return false; // Found a non-empty linked list
			}
		}
		return true;
	}
	
	/**
	 * Checks if the hash table contains the specified key.
	 * 
	 * @param key The key to check for.
	 * 
	 * @return true if the key is found, false otherwise.
	 */
	@Override
	public boolean containsKey(K key) {
		int index = hash(key);
		if (table[index] == null) {
			return false; // No entries at this index
		}

		// Traverse the linked list at this index
		for (Entry entry : table[index]) {
			if (entry.key.equals(key)) {
				return true; // Key found
			}
		}

		// Key not found in the linked list
		return false;
	}
	
	/*
	 * Checks if the hash table contains the specified value.
	 * 
	 * @param value The value to check for.
	 * 
	 * @return true if the value is found, false otherwise.
	 */
	@Override
	public boolean containsValue(V value) {
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				// Traverse the linked list at this index
				for (Entry entry : table[i]) {
					if (entry.value.equals(value)) {
						return true; // Value found
					}
				}
			}
		}
		
		return false;
	}

	/*
	 * Returns the value associated with the specified key in the hash table.
	 * 
	 * @param key The key whose associated value is to be returned.
	 * 
	 * @return The value associated with the specified key, or null if the key is
	 * not found.
	 */
	@Override
	public V get(K key) {
		
		int index = hash(key);
		if (table[index] == null) {
			return null; // No entries at this index
		}
		// Traverse the list at this index
		for (Entry entry : table[index]) {
			if (entry.key.equals(key)) {
				return (V) entry.value; // Key found, return value
			}
		}
		// Key not found in the linked list
		return null;
	}

	/*
	 * Inserts a new entry with the specified key and value into the hash table.
	 * 
	 * @param key The key of the entry to insert.
	 * 
	 * @param value The value of the entry to insert.
	 */
	@Override
	public void put(K key, V value) {
		int index = hash(key);
        ArrayList<Entry> bucket = table[index];
        
        // Check if the key already exists
		for (Entry entry : bucket) {
			if (entry.key.equals(key)) {
				entry.value = value; // Update existing value
				return;
			}
		}
		// Key does not exist, add a new entry
		Entry<K, V> newEntry = new Entry<>(key, value);
		bucket.add(newEntry);
		size++;
		
		if (size > LOAD_FACTOR_THRESHOLD * table.length) {
			resize(); // Resize if load factor exceeds threshold
		}
	}

	/*
	 * Removes the entry with the specified key from the hash table.
	 * 
	 * @param key The key of the entry to remove.
	 * 
	 * @return true if the entry was removed, false otherwise.
	 */
	@Override
	public boolean remove(K key) {
		// TODO Auto-generated method stub
		int index = hash(key);
		if (table[index] == null) {
			return false; // No entries at this index
		}
		// Traverse the linked list at this index
		for (Entry entry : table[index]) {
			if (entry.key.equals(key)) {
				V value = (V) entry.value; // Key found, get value
				table[index].remove(entry); // Remove entry from list
				size--;
				return true;
			}
		}
		// Key not found in the linked list
		return false;
	}

	/*
	 * * Removes all entries from the hash table.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.length; i++) {
			table[i].clear();
		}
		size = 0;
	}

	/*
	 * Returns an array of all keys in the hash table.
	 * 
	 * @return An array of all keys in the hash table.
	 */
	@Override
	public K[] keys() {
		// TODO Auto-generated method stub
		K[] keys = (K[]) new Object[size];
		int index = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				for (Entry entry : table[i]) {
					keys[index++] = (K) entry.key;
				}
			}
		}
		return keys;
	}
	
	/*
	 * * Returns an array of all values in the hash table.
	 * 
	 * @return An array of all values in the hash table.
	 */
	@Override
	public V[] values() {
		// TODO Auto-generated method stub
		V[] values = (V[]) new Object[size];
		int index = 0;
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				for (Entry entry : table[i]) {
					values[index++] = (V) entry.value;
				}
			}
		}
		return values;
	}

	
	/*
	 * Resizes the hash table to double its current capacity and rehashes all entries.
	 */
	private void resize() {
	    int newCapacity = table.length * 2;
	    ArrayList<Entry>[] newTable = new ArrayList[newCapacity];
	    for (int i = 0; i < newCapacity; i++) {
	        newTable[i] = new ArrayList<>();
	    }

	    for (int i = 0; i < table.length; i++) {
	        if (table[i] != null) {
	            for (Entry entry : table[i]) {
	                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
	                newTable[newIndex].add(entry);
	            }
	        }
	    }

	    table = newTable;
	}

	/**
	 * Returns the load factor of the hash table.
	 * 
	 * @return The load factor of the hash table.
	 */
	public double loadFactor() {
		return (double) size / table.length;
	}
	
}
