package Chatbot.ChatHistory;
import Chatbot.CustomADT.ArrayList;
/**
 * ChatHistory class that stores multiple ChatSessions.
 * Inherits from ArrayList<ChatSession> and implements ChatHistoryIF.
 */
public class ChatHistory extends ArrayList<ChatSession> implements ChatHistoryIF {
	
	private ChatSession currentSession;
	
    @Override
    public void addChatSession(ChatSession session) {
        this.add(session);
        System.out.println("Added session: " + session.getName());
    }

    @Override
    public ChatSession getChatSession(int index) {
        return this.get(index);
    }

    @Override
    public void updateChatSession(int index, ChatSession session) {
        this.remove(index);
        System.out.println("Updated session at index " + index);
    }

    @Override
    public void removeChatSession(int index) {
        this.remove(index);
        System.out.println("Removed session at index " + index);
    }

    @Override
    public void clearHistory() {
        this.clear();
        System.out.println("Chat history cleared.");
    }

    @Override
    public void saveToDatabase() throws Exception {
        System.out.println("Saving " + this.size() + " chat sessions to the database...");
        // Simulate saving
    }
    
    public void setCurrentSession(ChatSession session) {
        this.currentSession = session;
    }

    public ChatSession getCurrentSession() {
        return currentSession;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat History:\n");
        for (int i = 0; i < this.size(); i++) {
            ChatSession session = this.get(i);
            sb.append("Session ").append(i).append(": ")
              .append(session.toString()).append("\n");
        }
        return sb.toString();
    }


//    /**
//     * Main method for quick testing.
//     */
//    public static void main(String[] args) {
//        ChatHistory history = new ChatHistory();
//
//        ChatSession session1 = new ChatSession("Session A");
//        ChatSession session2 = new ChatSession("Session B");
//        ChatSession session3 = new ChatSession("Session C");
//
//        history.addChatSession(session1);
//        history.addChatSession(session2);
//        history.addChatSession(session3);
//
//        System.out.println("== All Sessions ==");
//        System.out.println(history);
//
//        ChatSession updated = new ChatSession("Updated Session B");
//        history.updateChatSession(1, updated);
//
//        System.out.println("== After Update ==");
//        System.out.println(history);
//
//        history.removeChatSession(0);
//        System.out.println("== After Removal ==");
//        System.out.println(history);
//
//        try {
//            history.saveToDatabase();
//        } catch (Exception e) {
//            System.out.println("DB error: " + e.getMessage());
//        }
//
//        history.clearHistory();
//        System.out.println("== After Clear ==");
//        System.out.println(history);
//    }
}
