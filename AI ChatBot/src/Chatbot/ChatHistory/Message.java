package Chatbot.ChatHistory;

import java.time.LocalDateTime;

/**
 * Represents a chat message with sender, content, and timestamp.
 */
public class Message {
    private SenderType sender;
    private String content;
    private LocalDateTime timestamp;
    
    public enum SenderType {
        USER, BOT;
    }
    
    /**
     * Constructs a Message object.
     * @param sender The sender of the message.
     * @param content The content of the message.
     * @param timestamp The time the message was sent.
     */

    public Message(SenderType sender, String content, LocalDateTime timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp; 
    }

    /**
     * Gets the sender of the message.
     * @return The sender of the message.
     */
    public SenderType  getSender() {
        return sender;
    }

    /**
     * Gets the content of the message.
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the timestamp of the message.
     * @return The timestamp of the message.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender=" + sender +
                ", timestamp=" + timestamp +
                '}';
    }

}
