package Chatbot;
import Chatbot.ChatHistory.*;
import Chatbot.KnowledgeBase.*;
import Chatbot.LLM.*;

public class Chatbot<T> implements ChatbotChatIF<T>, KnowledgeBaseManagementIF<T> {

	private ChatHistoryIF chatHistory;
	private KnowledgeBaseIF<T> knowledgeBase;
	private LLMApiClientIF llmApiClient;
	
	private static Chatbot<?> instance = null;
	
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


	/*
	 * * Generates a response to the user's question based on the knowledge base and
	 * context.
	 * 
	 * @param prompt The user's question.
	 * 
	 * @return The generated response.
	 */
	@Override
	public String generateResponse(String prompt) {
		String context = """
				You are an intelligent assistant designed to answer user questions **only based on the provided context and knowledge**. 
				Knowledge: {knowledge} 
				User Question: {question}
				Instructions:
				- You should analyze knowledge and questions to Recognize Intent and provide the best answer.
				- Your tone should be friendly and helpful.
				- If context and knowledge not contain any information to answer, respond with: I'm sorry, I don't have enough information to answer that.
				- Be concise and clear.
				- when question is not clear, clarify the question.
				- favorite count means the number of people who like the recipe.
				""";
		String knowledge = knowledgeBase.retrieveAll();
//		System.out.println("Knowledge Base: " + knowledge);
		
		String newPrompt = context
				.replace("{knowledge}", knowledge)
				.replace("{question}", prompt);

		String response = llmApiClient.callLLM(newPrompt);
		
		System.out.println("------User: ------\n" + prompt + "\n-------------------");
//		System.out.println("------Context: ------\n" + newPrompt + "\n-------------------");
		System.out.println("Chatbot: \n" + response);
		
		return response;
	}

	@Override
	public ChatHistoryIF getChatHistory() {
		return chatHistory;
	}

	@Override
	public KnowledgeBaseIF<T> getKnowledgeBase() {
		return knowledgeBase;
	}
	
//	public static void main(String[] args) {
//		Chatbot<application.Recipe> chatbot = Chatbot.getInstance();
//	    // initialize the recipes
//	    application.Configure config = application.Configure.getInstance();
//
//	    // add entries to the knowledge base
//	    for (application.Recipe recipe : config.getRecipes()) {
//	        boolean added = chatbot.getKnowledgeBase().addEntry(recipe);
//	        System.out.println((added ? "Added" : "Failed to add") + " entry: " + recipe.getName());
//	    }
//
//	    
//	    // test questions
//	    String[] testQuestions = {
//	            "I want some chocolate desserts, what do you recommend?",
//	            "What is a popular Italian dessert?",
//	            "Can you suggest a fruit-based dessert?",
//	            "What is the favorite dish in the system?",
//	            "How do I make a traditional pasta dish?"
//	        };
//	    
//	    // ask questions
//	    for (String question : testQuestions) {
//	        String response = chatbot.generateResponse(question);
//	        
//	        try {
//	            Thread.sleep(100);  // Sleep for 100 milliseconds
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
	
}
