/*
p4 Dictionary Graph
Kylie Huang, Lan Hu, Henry Huang, Gloria Hsieh
Email addresses: zhuang95@wisc.edu, lhu49@wisc.edu, hhuang266@wisc.edu, ghsieh@wisc.edu 
Due date: February 5, 2018
Source Credits: Deb Deppeler 
Known bugs: None
*/

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
    private HashMap<E, HashSet<E>> graph;

    /**
     * Initialize two HashMaps, one is for visit record and the other is for acutal graph
     */

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
            return null;
        }
        if(graph.containsKey(vertex)) {
            return null;
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

    @Override
    public E removeVertex(E vertex) {
        if(vertex != null & graph.containsKey(vertex)) {
            if(!graph.get(vertex).isEmpty()) { 
                for(E adjacent: graph.get(vertex)) {
                    graph.get(adjacent).remove(vertex);
                }
            }    
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

    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        if (!graph.containsKey(vertex1) || !graph.containsKey(vertex2)){
            return false;
        } else if(vertex1.equals(vertex2)) {
            return false;
        } else {
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

    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if (!graph.containsKey(vertex1) || !graph.containsKey(vertex2)){
            return false;
        } else if(vertex1.equals(vertex2)==true){//potential bug existed
            return false;
        } else {
            graph.get(vertex1).remove(vertex2);
            graph.get(vertex2).remove(vertex1);
            return true;
        }
    }

    /**
     * Check whether the two vertices are adjacent
     *
     * Valid argument conditions:
     * 1. both the vertices should exist in the graph
     * 2. vertex1 should not equal vertex2
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @return true if both the vertices have an edge with each other, else return false if vertex1 and vertex2 are not connected (also if valid conditions are violated)
     */

    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        if (!graph.containsKey(vertex1) || !graph.containsKey(vertex2)){
            return false;
        } else if(vertex1.equals(vertex2)==true){//potential bug existed
            return false;
        } else {
            if(graph.get(vertex1).contains(vertex2) && graph.get(vertex2).contains(vertex1)){
                return true;
            } else {
                return false;
            }
        }
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

    @Override
    public Iterable<E> getNeighbors(E vertex) {
        return graph.get(vertex);
    }

    /**
     * Get all the vertices in the graph
     *
     * @return an iterable for all the vertices
     */

    @Override
    public Iterable<E> getAllVertices() {
    		return graph.keySet();
    }
}
