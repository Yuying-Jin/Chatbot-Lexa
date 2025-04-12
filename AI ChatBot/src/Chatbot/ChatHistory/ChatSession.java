package Chatbot.ChatHistory;
import Chatbot.CustomADT.ArrayList;
import Chatbot.CustomADT.List;
import java.time.LocalDateTime;

/**
 * Represents a chat session that holds a list of messages.
 * Extends ArrayList<Message> to allow direct list operations.
 */
public class ChatSession extends ArrayList<Message> {
    private String name;
    private LocalDateTime timestamp;
    private int id;
    private int size;

    private static int idCounter = 1;

    /**
     * Default constructor with auto-generated name.
     */
    public ChatSession() {
        this("Unnamed Session");
    }

    /**
     * Constructs a chat session with the specified name.
     * @param name The name of the session.
     */
    public ChatSession(String name) {
        super(); // Initialize the underlying ArrayList
        this.name = name;
        this.timestamp = LocalDateTime.now();
        this.id = idCounter++;
        this.size = 0;
    }

    /**
     * Adds a message to the session and updates count.
     * Overrides add() from ArrayList<Message>
     * @param message The message to add.
     */
    @Override
    public void add(Message message) {
        super.add(message);
        size++;
    }

    /**
     * Returns the name of the session.
     * @return Session name.
     */
    public String getName() {
        return name;
    }
    
	/**
	 * Sets the name of the session.
	 * 
	 * @param name The new name for the session.
	 */
    public void setName(String name) {
    	this.name = name;
    }

    /**
     * Returns the session creation timestamp.
     * @return Session creation time.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the unique session ID.
     * @return Session ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the number of messages in the session.
     * @return Message count.
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the entire list of messages.
     * @return This session as a list of messages.
     */
    public List<Message> getAllMessage() {
        return this; // Since this extends ArrayList<Message>
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", id=" + id +
                ", count=" + size +
                '}';
    }
}
