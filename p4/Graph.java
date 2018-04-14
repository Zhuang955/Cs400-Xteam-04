import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {
    
    /**
     * Instance variables and constructors
     */
	private HashMap<Vertex, HashSet<Vertex>> adjacencyList; 
    /**
     * {@inheritDoc}
     */
	
	public class Vertex<T> {
		private T data;
		private boolean visited; 
		
		public Vertex() {
			data = null; 
			visited = false; 
		}
		
		public Vertex(T data, boolean visited) {
			this.data = data; 
			this.visited = visited; 
		}
	}
	
	public Graph() {
		this.adjacencyList = new HashMap<Vertex, HashSet<Vertex>>(); 
	}
	
	/**
	 * Add new vertex to the graph
	 * 
	 * Valid argument conditions:
	 * 1. vertex should be non-null
	 * 2. vertex should not already exist in the graph 
	 * 
	 * @param vertex the vertex to be added
	 * @return vertex if vertex added, else return null if vertex can not be added (also if valid conditions are violated)
	 * 
	 * try catch 
	 */
    @Override
    public E addVertex(E vertex) {
    	if(vertex == null) {
    			throw new IllegalArgumentException(); 
    		}
        if(adjacencyList.containsKey(vertex)) {
        		throw new DuplicateVerticeException(); 
        }
        adjacencyList.put((Vertex) vertex, new HashSet<Vertex>());
        return vertex; 
    }
    
	/**
	 * Remove the vertex and associated edge associations from the graph
	 * 
	 * Valid argument conditions:
	 * 1. vertex should be non-null
	 * 2. vertex should exist in the graph 
	 *  
	 * @param vertex the vertex to be removed
	 * @return vertex if vertex removed, else return null if vertex and associated edges can not be removed (also if valid conditions are violated)
	 */
    /**
     * {@inheritDoc}
     */
    @Override
     public E removeVertex(E vertex) {
        if(vertex != null & adjacencyList.containsKey(vertex)) {
       		adjacencyList.remove(vertex);
        	return vertex; 
        } else {
        	return null; 
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        
    }

}
