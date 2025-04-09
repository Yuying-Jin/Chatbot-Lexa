package Chatbot.KnowledgeBase;

public interface KnowledgeBaseIF<T> {
	
	boolean addEntry(T entry); // Adds an entry to the knowledge base
	boolean updateEntry(T entry); // Updates an entry in the knowledge base
	boolean removeEntry(T entry); // Removes an entry from the knowledge base
	String findEntriesByValue(String info); // Finds entries by value
	String retrieveAll(); // Retrieves all entries from the knowledge base
    
}
