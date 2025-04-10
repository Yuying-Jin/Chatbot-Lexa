package Chatbot.ChatHistory;

public interface ChatHistoryIF {
    /**
     * Adds a chat session to the history.
     *
     * @param session The chat session to add.
     */
    void addChatSession(ChatSession session);

    /**
     * Retrieves the chat session at the specified index.
     *
     * @param index The index of the chat session to retrieve.
     * @return The chat session at the specified index.
     */
    ChatSession getChatSession(int index);

    /**
     * updates the chat session at the specified index.
     * 
     * @param index The index of the chat session to update.
     * @param session The updated chat session.
     */
    void updateChatSession(int index, ChatSession session);

    /**
     * Removes the chat session at the specified index.
     *
     * @param index The index of the chat session to remove.
     */
    void removeChatSession(int index);


    /**
     * Clears all chat sessions from the history.
     */
    void clearHistory();

    /**
    *saves the chat history to a Database
    * @param db The database to save the chat history to.
    * @throws Exception if an error occurs while saving to the database
     */
    void saveToDatabase() throws Exception;

    /**
     * Returns a string representation of the chat history.
     *
     * @return A string representation of the chat history.
     */
    String toString();
}

