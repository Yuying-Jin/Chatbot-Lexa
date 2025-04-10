package Chatbot;

import Chatbot.KnowledgeBase.KnowledgeBaseIF;

public interface KnowledgeBaseManagementIF<T> {
	KnowledgeBaseIF<T> getKnowledgeBase(); // Returns the knowledge base
	
	
}
