package Chatbot.CustomADT;

public interface Map<K, V> {
	int size();
	boolean isEmpty();
	boolean containsKey(K key);
	boolean containsValue(V value);
	V get(K key);
	void put(K key, V value);
	boolean remove(K key);
	void clear();
	K[] keys();
	V[] values();
	
}
