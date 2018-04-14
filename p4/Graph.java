import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.*; 


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
	private HashMap<E, HashSet<E>> graph; 
    /**
     * {@inheritDoc}
     */
	
	/*public class Vertex<E> {
		private E data;
		private boolean visited; 
		
		public Vertex() {
			data = null; 
			visited = false; 
		}
		
		public Vertex(E data, boolean visited) {
			this.data = data; 
			this.visited = visited; 
		}
	}*/ 
	
	public Graph() {
		this.graph = new HashMap<E, HashSet<E>>(); 
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
        if(graph.containsKey(vertex)) {
        		throw new DuplicateVerticeException(); 
        }
        graph.put(vertex, new HashSet<E>());
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
        if(vertex != null & graph.containsKey(vertex)) {
        		graph.remove(vertex);
        		return vertex; 
        } else {
        		return null; 
        }
    }

       /**
     /**
     * Add an edge between two vertices (edge is undirected and unweighted)
     *
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if edge added, else return false if edge can not be added (also if valid conditions are violated)
     */
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        if (graph.containsKey(vertex1) && graph.containsKey(vertex2)){
            return false;
        }
        else if(vertex1.equals(vertex2)==true){//potential bug existed
            return false;
        }
        else{
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
            return true;
        }
    }

    /**
     * Remove the edge between two vertices (edge is undirected and unweighted)
     *
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if edge removed, else return false if edge can not be removed (also if valid conditions are violated)
     */
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if (graph.containsKey(vertex1) && graph.containsKey(vertex2)){
            return false;
        }
        else if(vertex1.equals(vertex2)==true){//potential bug existed
            return false;
        }
        else{
            graph.get(vertex1).remove(vertex2);
            graph.get(vertex2).remove(vertex1);
            return true;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        
    }
	/**
	 * Get all the neighbor vertices of a vertex
	 * 
	 * Valid argument conditions:
	 * 1. vertex is not null
	 * 2. vertex exists
	 * 
	 * @param vertex the vertex
	 * @return an iterable for all the immediate connected neighbor vertices
	 */
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        if(vertex != null & graph.containsKey(vertex)) {
        		Iterator iterator = graph.get(vertex).iterator(); 
        		while(iterator.hasNext()) {
        			
        		}
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        
    }

}
