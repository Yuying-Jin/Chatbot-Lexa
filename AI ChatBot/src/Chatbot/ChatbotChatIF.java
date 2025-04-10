package Chatbot;

import Chatbot.ChatHistory.*;
import Chatbot.KnowledgeBase.*;
import Chatbot.LLM.*;


public interface ChatbotChatIF<T> {
	public ChatHistoryIF getChatHistory();
	public String generateResponse(String prompt);
}
