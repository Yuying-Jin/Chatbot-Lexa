package Chatbot.CustomADT;

public interface GraphIF<T> {
	boolean addNode(T node);
    boolean addEdge(T from, T to, String relation);
    boolean removeEdge(T from, T to);
    T[] neighbors(T node);
    String[] relations(T from, T to);
    boolean hasEdge(T from, T to);
}
