package Chatbot.CustomADT;
import Chatbot.CustomADT.*;
public class GraphM<T> implements GraphIF<T> {
	
	private int[][] matrix;
	private String[][] relationMatrix;
	private T[] nodesData;
	private int nodeCount;
    private int numEdge;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 2000;
    
	public GraphM() {
		this(DEFAULT_CAPACITY);
	}
	
    @SuppressWarnings("unchecked")
    public GraphM(int capacity) {
    	this.capacity = capacity;
        this.matrix = new int[capacity][capacity];
        this.relationMatrix = new String[capacity][capacity];
        this.nodesData = (T[]) new Object[capacity];
        this.nodeCount = 0;
        this.numEdge = 0;
    }
	

	private int getIndex(T data) {
	    for (int i = 0; i < nodeCount; i++) {
	        if (nodesData[i].equals(data)) return i;
	    }
	    return -1;
	}
	
	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * @param data
	 */
	private boolean ensureNode(T data) {
        if (getIndex(data) == -1 && nodeCount < capacity) {
        	nodesData[nodeCount++] = data;
        	return true;
        }
        return false;
    }
	
	
	
	
	private void expandMatrix() {
		int newCapacity = capacity + DEFAULT_CAPACITY;
		// create new matrix with new capacity
		int[][] newMatrix = new int[newCapacity][newCapacity];
		String[][] newRelationMatrix = new String[newCapacity][newCapacity];
		T[] newNodesData = (T[]) new Object[newCapacity];

		for (int i = 0; i < capacity; i++) {
			for (int j = 0; j < capacity; j++) {
				newMatrix[i][j] = matrix[i][j];
				newRelationMatrix[i][j] = relationMatrix[i][j];
			}
			newNodesData[i] = nodesData[i];
		}

		capacity = newCapacity;
		matrix = newMatrix;
		relationMatrix = newRelationMatrix;
		nodesData = newNodesData;
	}
	
	private boolean checkCapacity() {
		if (nodeCount >= capacity) {
			expandMatrix();
			return true;
		}
		return false;
	}
	 
	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * @param data
	 * @return true if the node already exists in the graph, false otherwise
	 */
	@Override
	public boolean addNode(T data) {
		boolean isSuccess =  ensureNode(data);
		return isSuccess;
		
	}

	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * 
	 * @param data
	 * 
	 * @return true if the node already exists in the graph, false otherwise
	 */
	@Override
	public boolean addEdge(T from, T to, String relation) {
		// check if the nodes exist
		boolean isSuccess = true;
		isSuccess &= ensureNode(from);
		isSuccess &= ensureNode(to);
		
		
        int i = getIndex(from);
        int j = getIndex(to);
        
        // check if the edge already exists
        if (matrix[i][j] == 0) numEdge++;
        matrix[i][j] = 1;
        // add the relation
        relationMatrix[i][j] = relation;
        
        return isSuccess;
	}

	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * 
	 * @param data
	 * 
	 * @return true if the node already exists in the graph, false otherwise
	 */
	@Override
	public boolean removeEdge(T from, T to) {
		int i = getIndex(from);
		int j = getIndex(to);
		if (matrix[i][j] == 1) {
			matrix[i][j] = 0;
			numEdge--;
			return true;
		}
		return false;
		
	}
	
	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * 
	 * @param data
	 * 
	 * @return true if the node already exists in the graph, false otherwise
	 */

	@Override
	public T[] neighbors(T node) {
		int i = getIndex(node);
		if (i == -1)
			return null;
		T[] neighbors = (T[]) new Object[nodeCount];
		int count = 0;
		for (int j = 0; j < nodeCount; j++) {
			if (matrix[i][j] == 1) {
				neighbors[count++] = nodesData[j];
			}
		}
		return neighbors;
	}
	
	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * 
	 * @param data
	 * 
	 * @return true if the node already exists in the graph, false otherwise
	 */

	@Override
	public String[] relations(T from, T to) {
		int i = getIndex(from);
		int j = getIndex(to);
		if (i == -1 || j == -1)
			return null;
		String[] relations = new String[nodeCount];
		int count = 0;
		for (int k = 0; k < nodeCount; k++) {
			if (matrix[i][k] == 1 && matrix[k][j] == 1) {
				relations[count++] = relationMatrix[i][k];
			}
		}
		return relations;
	}
	
	/*
	 * check if the node already exists in the graph if not, add it to the graph
	 * 
	 * @param data
	 * 
	 * @return true if the node already exists in the graph, false otherwise
	 */

	@Override
	public boolean hasEdge(T from, T to) {
		int i = getIndex(from);
        int j = getIndex(to);
        if (i == -1 || j == -1)
            return false;
        return matrix[i][j] == 1;
	}
}
