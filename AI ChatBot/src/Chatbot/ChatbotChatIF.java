package Chatbot;

public interface ChatbotChatIF<T> {
	public String generateResponse(String prompt);
	public T receiveMessage();
	
	
//	public void setChatHistory(ChatHistoryIF chatHistory);
//	public ChatHistoryIF getChatHistory();
}
