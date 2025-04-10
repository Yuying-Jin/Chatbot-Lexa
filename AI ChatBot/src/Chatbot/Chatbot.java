package Chatbot;

import Chatbot.ChatHistory.*;
import Chatbot.KnowledgeBase.*;
import Chatbot.LLM.*;

public class Chatbot<T> implements ChatbotChatIF<T>, KnowledgeBaseManagementIF<T> {

	private ChatHistoryIF chatHistory;
	private KnowledgeBaseIF<T> knowledgeBase;
	private LLMApiClientIF llmApiClient;
	
	private static Chatbot instance = null;
	
	private Chatbot() {
		this.chatHistory = new ChatHistory();
		this.knowledgeBase = new KnowledgeBase<>();
		this.llmApiClient = new LLMApiClient();
	}
	
	public static Chatbot getInstance() {
		if (instance == null) {
			instance = new Chatbot();
		}
		return instance;
    }


	@Override
	public T receiveMessage() {
		
		return null;
	}

	@Override
	public String generateResponse(String prompt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    
	
}
