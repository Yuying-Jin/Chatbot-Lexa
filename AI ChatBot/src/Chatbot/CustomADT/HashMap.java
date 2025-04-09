package Chatbot.CustomADT;
import java.util.LinkedList;

import application.Configure;
import application.Recipe;

public class HashMap<K, V> implements Map<K, V> {
	
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
	
	protected LinkedList<Entry>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;
	
    
	public HashMap() {
		this(DEFAULT_CAPACITY);
	}
	
	public HashMap(int capacity) {
		this.table = new LinkedList[capacity];
		for (int i = 0; i < capacity; i++) {
			table[i] = new LinkedList<>();
		}
		this.size = 0;
	}
	
	protected int hash(K key) {
		return key == null ? 0 : Math.abs(key.hashCode()) % table.length;
    }
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && !table[i].isEmpty()) {
				return false; // Found a non-empty linked list
			}
		}
		return true;
	}

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

	@Override
	public boolean containsValue(V value) {
		
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				for (Entry entry : table[i]) {
					if (entry.value.equals(value)) {
						return true; // Value found
					}
				}
			}
		}
		
		return false;
	}

	@Override
	public V get(K key) {
		
		int index = hash(key);
		if (table[index] == null) {
			return null; // No entries at this index
		}
		// Traverse the linked list at this index
		for (Entry entry : table[index]) {
			if (entry.key.equals(key)) {
				return (V) entry.value; // Key found, return value
			}
		}
		// Key not found in the linked list
		return null;
	}

	@Override
	public void put(K key, V value) {
		int index = hash(key);
        LinkedList<Entry> bucket = table[index];
        
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
	}

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
				table[index].remove(entry); // Remove entry from linked list
				size--;
				return true;
			}
		}
		// Key not found in the linked list
		return false;
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < table.length; i++) {
			table[i].clear();
		}
		size = 0;
	}

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


	private void resize() {
		int newCapacity = table.length * 2;
		LinkedList<Entry>[] newTable = new LinkedList[newCapacity];
		for (int i = 0; i < newCapacity; i++) {
			newTable[i] = new LinkedList<>();
		}

		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				for (Entry entry : table[i]) {
					int newIndex = entry.key.hashCode() % newCapacity;
					newTable[newIndex].add(entry);
				}
			}
		}

		table = newTable;
	}
	
	public double loadFactor() {
		return (double) size / table.length;
	}
	
}
