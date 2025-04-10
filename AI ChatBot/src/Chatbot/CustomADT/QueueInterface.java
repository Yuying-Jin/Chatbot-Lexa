package Chatbot.CustomADT;

public interface QueueInterface<T> {

	boolean isEmpty();
	
	void add(T element);
	
	T remove();
	
	void displayQueue();
	
}
