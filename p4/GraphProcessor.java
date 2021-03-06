/*
p4 Dictionary Graph
Kylie Huang, Lan Hu, Henry Huang, Gloria Hsieh
Email addresses: zhuang95@wisc.edu, lhu49@wisc.edu, hhuang266@wisc.edu, ghsieh@wisc.edu 
Due date: February 5, 2018
Source Credits: Deb Deppeler 
Known bugs: None
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {

    /**
     * Graph which stores the dictionary words and their associated connections
     */
    private GraphADT<String> graph;
    //includes results of bfs
    private ArrayList<ArrayList<String>> bfsPath; 
    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     */
    public Integer populateGraph(String filepath) {
    	Integer wordNum = 0;         //number of vertices added
    	WordProcessor wordPros = new WordProcessor();
    	List<String> wordLines = null;
    	try {
			Stream <String> wordStream = wordPros.getWordStream(filepath);
			wordLines = wordStream.collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error: Failed to read file from " + filepath);
			return -1;
		}
    	//add all vertices
    	for(int i = 0; i < wordLines.size(); i++){
    		graph.addVertex(wordLines.get(i));
    		wordNum++;
    	}
    	//add all edges
    	for(int i = 0; i < wordLines.size() - 1; i++){
    		for(int j = i + 1; j < wordLines.size(); j++){
    			if(wordPros.isAdjacent(wordLines.get(i), wordLines.get(j))){
    				graph.addEdge(wordLines.get(i), wordLines.get(j));
    			}
    		}
    	}
        return wordNum;
    }

    
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     * 
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
    public List<String> getShortestPath(String word1, String word2) {
    	List<String> path = new ArrayList<String>();
    	//find result of bfs that is related to word1
    	int relatedPath = -1;
    	for(int i = 0; i < bfsPath.size(); i++){
    		if(bfsPath.get(i).get(0).equals(word1.toUpperCase())){
    			relatedPath = i;
    			break;
    		}
    	}
    	
    	String currentWord = word2.toUpperCase();
    	//the index where currentWord is found
    	int indexFound = bfsPath.get(relatedPath).indexOf(currentWord);
    	//if the word is not found in the bsf search result or if the two input words
    	//are same, output empty path
    	if(indexFound == -1 || word1.toUpperCase().equals(word2.toUpperCase())){
    		return path;
    	}
    	//read through the output of bfs search to find the shortest path
    	while(!currentWord.equals(word1.toUpperCase())){
    		path.add(0, currentWord);
    		for(String successor : graph.getNeighbors(currentWord)){
    			if(bfsPath.get(relatedPath).indexOf(successor) < indexFound){
    				indexFound = bfsPath.get(relatedPath).indexOf(successor);
    			}
    		}
    		currentWord = bfsPath.get(relatedPath).get(indexFound);
    	}
    	//add the starting word into the path
    	path.add(0, word1.toUpperCase());
    	for(int i = 0; i < path.size(); i++){
    		System.out.println(path.get(i));
    	}
    	return path;
    }
    
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     *   = 3 (the number of edges in the shortest path)
     * 
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance, Distance = -1 if no path found between words or if word1=word2
     */
    public Integer getShortestDistance(String word1, String word2) {
        return getShortestPath(word1, word2).size() - 1;
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {
    	bfsPath = new ArrayList<ArrayList<String>>();
    	for(String vertex : graph.getAllVertices()){
    		BFS(vertex);
    	}
    }
    /**
     * run breadth first search starting from the given parameter
     * @param name
     */
    private void BFS(String name){
    	//stores the result of bfs
    	ArrayList<String> pathFromName = new ArrayList<String>();
    	//store all the vertexes into arraylist
    	ArrayList<String> allVertex = new ArrayList<String>();
    	for(String vertex : graph.getAllVertices()){
    		allVertex.add(vertex);
    	}
    	//find the position of the start vertex
    	int curIndex = allVertex.indexOf(name);
    	//mark all vertices as unvisited
    	boolean visited[] = new boolean[allVertex.size()];
    	for(int i = 0; i < visited.length; i++){
    		visited[i] = false;
    	}
    	//create queue
    	Queue <String> q = new LinkedList<String>();
    	String current = "";
    	//Start breadth first search
    	visited[curIndex] = true;
    	q.add(name);
    	while(q.size()!= 0){
    		current = q.poll();
    		pathFromName.add(current);    //record the result
    		for(String successor : graph.getNeighbors(current)){
    			curIndex = allVertex.indexOf(successor); 
    			if(visited[curIndex] == false){
    				visited[curIndex] = true;
    				q.add(successor);
    			}
    		}
    	}
    	//add results to bfsPath
    	bfsPath.add(pathFromName);
    }
}
