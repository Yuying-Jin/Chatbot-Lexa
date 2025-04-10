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
	
	/**
	 * Singleton pattern to ensure only one instance of Chatbot exists.
	 * 
	 * @return The single instance of Chatbot.
	 */
	public static Chatbot getInstance() {
		if (instance == null) {
			instance = new Chatbot();
		}
		return instance;
    }


	@Override
	public String generateResponse(String prompt) {
		// TODO Auto-generated method stub
		String context = "Based on the context {context with limited length}and "
				+ "knowledge {knowledge}, answer the user’s question {question}. "
				+ "Answer in English and be concise without mentioning the data source.";
		
		llmApiClient.callLLM(context);
		
		
		return null;
	}

	@Override
	public ChatHistoryIF getChatHistory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    
	
}
