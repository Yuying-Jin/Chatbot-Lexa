package Chatbot.KnowledgeBase;

import Chatbot.CustomADT.HashMap;
import javafx.beans.property.*;


public class KnowledgeBase<T> implements KnowledgeBaseIF<T> {
	
	private HashMap<String, String> graph;
	
	public KnowledgeBase() {
		this.graph = new HashMap<>();
	}
	
	
	/*
	 * This method encodes an entry into a string format.
	 * It uses reflection to access the fields of the entry object.
	 * 
	 * The encoded string format is:
	 * fieldName1:value1; fieldName2:value2; ...
	 *   
	 * @param entry The entry object to encode
	 * @return The encoded string representation of the entry
	 */
	private String encodeEntry(T entry) {
		if (entry == null) {
			return null;
		}
		
		// Use reflection to get the fields and their values
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = entry.getClass();
        java.lang.reflect.Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            java.lang.reflect.Field field = fields[i];
            field.setAccessible(true); // Allow access to private fields
            try {
                Object value = field.get(entry);
                
                if (value == null || value.toString().isEmpty()) {
					continue; // Skip null values
				}
                
//                if (value instanceof SimpleStringProperty) {
//                    sb.append(fields[i].getName()).append(":").append(((SimpleStringProperty) value).get());
//                }
//                // Handle SimpleIntegerProperty
//                else if (value instanceof SimpleIntegerProperty) {
//                    sb.append(fields[i].getName()).append(":").append(((SimpleIntegerProperty) value).get());
//                }
				
                sb.append(field.getName()).append(":").append(value); // Append field name and value
                
                if (i < fields.length - 1) {
                    sb.append("; "); // Separate fields with semicolon
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
		return sb.toString();
	}
	
	/*
	 * This method adds an entry to the knowledge base.
	 * It checks if the entry already exists in the graph.
	 * 
	 * @param entry The entry to add
	 * @return true if the entry was added successfully, false otherwise
	 * 
	 */
	public boolean addEntry(T entry) {
		String entryName = entry.toString();
		String value = encodeEntry(entry);
		
		if (graph.containsKey(entryName) || value == null)
			return false; // Entry already exists or value is null
			
		graph.put(entryName, value);
		return true; // Entry added successfully
	}
	
	/*
	 * This method updates an entry in the knowledge base.
	 * It checks if the entry exists in the graph.
	 * 
	 * @param entry The entry to update
	 * @return true if the entry was updated successfully, false otherwise
	 */
	public boolean updateEntry(T entry) {
		String entryName = entry.toString();
		String value = encodeEntry(entry);
		
		if (!graph.containsKey(entryName))
			return false; // Entry does not exist
		
		graph.put(entryName, value);
		return true; // Entry updated successfully
	}
	
	/* 
	 * 
     * This method removes an entry from the knowledge base.
     * It checks if the entry exists in the graph.
     * 
     * @param entry The entry to remove
     * @return true if the entry was removed successfully, false otherwise
	 */
	public boolean removeEntry(T entry) {
		String entryName = entry.toString();

		if (!graph.containsKey(entryName))
			return false; // Entry does not exist

		graph.remove(entryName);
		return true; // Entry removed successfully
	}
	
	/*
	 * This method retrieves all entries from the knowledge base.
	 * It iterates over the keys and values in the graph.
	 * 
	 * @return A string representation of all entries in the knowledge base
	 */
	public String retrieveAll() {
	    StringBuilder sb = new StringBuilder();

	    // Iterate over the keys and values in the graph
	    Object[] keys = graph.keys();
	    for (Object k : keys) {
	        String key = k.toString();
	        String value = graph.get(key);
	        
//	        System.out.println(key + ": " + value);  
	        
	        // Append the key-value pair to the string builder
	        sb.append("[").append(key).append("] => ").append(value).append("\n");
	    }
	    return sb.toString();
	}
	
	
	/*
	 * This method finds entries in the knowledge base by value. It checks if the
	 * value contains the specified info string.
	 * 
	 * @param info The info string to search for
	 * 
	 * @return A string representation of all matching entries
	 */
	public String findEntriesByValue(String info) {
	    StringBuilder sb = new StringBuilder();
	    Object[] keys = graph.keys();
	    
	    for (Object key : keys) {
	        String value = graph.get((String) key);
	    
	        // Check if the value contains the info string
	        if (value != null && value.contains(info)) {
	            sb.append("[").append(key.toString()).append("] => ").append(value).append("\n");
	        }
	    }
	    return sb.toString(); 
	}

	// Test the KnowledgeBase class
	public static void main(String[] args) {
		
		// |---------------- Insert entries into the knowledge base ---------------------------|
	    // initialize the knowledge base
	    KnowledgeBase<Recipe> kb = new KnowledgeBase<>();

	    // initialize the recipes
	    Configure config = Configure.getInstance();

	    // add entries to the knowledge base
	    for (Recipe recipe : config.getRecipes()) {
	        boolean added = kb.addEntry(recipe);
	        System.out.println((added ? "Added" : "Failed to add") + " entry: " + recipe.getName());
	    }
	    
	    // |-----------------update an entry in the knowledge base---------------|
	    Recipe updated = config.getRecipes().get(0); // Get the first recipe; 
	    updated.setFavorite(1); 
	    boolean updatedResult = kb.updateEntry(updated);
	    System.out.println("Update entry '" + updated.getName() + "': " + updatedResult);
	    
	    // |----------------------Retrieve all entries-------------------------|
	    // print all entries in the knowledge base
	    System.out.println("\n--- All knowledge entries in KnowledgeBase ---");
	    String allKnowledge = kb.retrieveAll();
	    // print all entries
	    System.out.println(allKnowledge);
	    
	    // |------------------Find entries by value-----------------|
	    System.out.println("\n--- Find Entries by Value ---");
	    String result = kb.findEntriesByValue("bacon");
	    System.out.println("Search results for 'bacon':\n" + result);
	    
	    String result2 = kb.findEntriesByValue("chocolate");
	    System.out.println("Search results for 'chocolate':\n" + result2);
	    
	    // |------------------remove an entry from the knowledge base-----------------|
	    System.out.println("\n--- Remove an entry ---");
	    boolean removedExistent = kb.removeEntry(config.getRecipes().get(0)); 
	    System.out.println("Removed non-existent recipe: " + removedExistent); // Should print true
	    boolean removedNonExistent = kb.removeEntry(config.getRecipes().get(0)); // Try removing the same recipe again
	    System.out.println("Removed non-existent recipe: " + removedNonExistent); // Should print false
	}
	
}
