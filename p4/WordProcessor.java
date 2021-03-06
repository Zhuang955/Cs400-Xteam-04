/*
p4 Dictionary Graph
Kylie Huang, Lan Hu, Henry Huang, Gloria Hsieh
Email addresses: zhuang95@wisc.edu, lhu49@wisc.edu, hhuang266@wisc.edu, ghsieh@wisc.edu 
Due date: February 5, 2018
Source Credits: Deb Deppeler 
Known bugs: None
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * This class contains some utility helper methods
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class WordProcessor {
	
	/**
	 * Gets a Stream of words from the filepath.
	 * 
	 * The Stream should only contain trimmed, non-empty and UPPERCASE words.
	 * 
	 * @see <a href="http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html">java8 stream blog</a>
	 * 
	 * @param filepath file path to the dictionary file
	 * @return Stream<String> stream of words read from the filepath
	 * @throws IOException exception resulting from accessing the filepath
	 */
	public static Stream<String> getWordStream(String filepath) throws IOException {
		// read from the filepath, and then do mapping adn filtering to get the wordstream for further use
	        Stream<String> wordStream = Files.lines(Paths.get(filepath));
		
		wordStream = wordStream.map(String::toString);
		wordStream = wordStream.filter(x -> x != null && !x.equals(""));
		wordStream = wordStream.map(String::toUpperCase);

		/**
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">java.nio.file.Files</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Paths.html">java.nio.file.Paths</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Path.html">java.nio.file.Path</a>
		 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">java.util.stream.Stream</a>
		 * 
		 * class Files has a method lines() which accepts an interface Path object and 
		 * produces a Stream<String> object via which one can read all the lines from a file as a Stream.
		 * 
		 * class Paths has a method get() which accepts one or more strings (filepath),  
		 * joins them if required and produces a interface Path object
		 * 
		 * Combining these two methods:
		 *     Files.lines(Paths.get(<string filepath>))
		 *     produces
		 *         a Stream of lines read from the filepath
		 * 
* Once this Stream of lines is available, you can use the powerful operations available for Stream objects to combine 
		 * multiple pre-processing operations of each line in a single statement.
		 * 
		 * Few of these features:
		 * 		1. map( )      [changes a line to the result of the applied function. Mathematically, line = operation(line)]
		 * 			-  trim all the lines
		 * 			-  convert all the lines to UpperCase
		 * 			-  example takes each of the lines one by one and apply the function toString on them as line.toString() 
		 * 			   and returns the Stream:
		 * 			        streamOfLines = streamOfLines.map(String::toString) 
		 * 
		 * 		2. filter( )   [keeps only lines which satisfy the provided condition]  
		 *      	-  can be used to only keep non-empty lines and drop empty lines
		 *      	-  example below removes all the lines from the Stream which do not equal the string "apple" 
		 *                 and returns the Stream:
		 *      			streamOfLines = streamOfLines.filter(x -> x != "apple");
		 *      			 
		 * 		3. collect( )  [collects all the lines into a java.util.List object]
		 * 			-  can be used in the function which will invoke this method to convert Stream<String> of lines to List<String> of lines
		 * 			-  example below collects all the elements of the Stream into a List and returns the List:
		 * 				List<String> listOfLines = streamOfLines.collect(Collectors::toList); 
		 * 
		 * Note: since map and filter return the updated Stream objects, they can chained together as:
		 * 		streamOfLines.map(...).filter(a -> ...).map(...) and so on
		 */
		
		return wordStream;
	}
	
	/**
	 * Adjacency between word1 and word2 is defined by:
	 * if the difference between word1 and word2 is of
	 * 	1 char replacement
	 *  1 char addition
	 *  1 char deletion
	 * then 
	 *  word1 and word2 are adjacent
	 * else
	 *  word1 and word2 are not adjacent
	 *  
	 * Note: if word1 is equal to word2, they are not adjacent
	 * 
	 * @param word1 first word
	 * @param word2 second word
	 * @return true if word1 and word2 are adjacent else false
	 */
	public static boolean isAdjacent(String word1, String word2) {
		// if two strings are equal, return false immediately
		if (word1.equals(word2)) return false;
		
		// if two strings are not equal, then the following will be executed
		// if the lengths of two words are equal
		if (word1.length() == word2.length()) {
			int difference = 0;
			for (int n = 0; n < word1.length(); n++) {
				if (word1.charAt(n) != word2.charAt(n)) difference++;
			}
			if (difference == 1) return true;
		}
		// if of 1 char addition or 1 char deletion 
		else if (Math.abs(word1.length()-word2.length()) == 1) {
			int m = word1.length();
			int n = word2.length();
			
			int i = 0;
			int j = 0;
			int count = 0;
			// loop through every char in the string
			while (i < m && j < n) {
				if (word1.charAt(i) != word2.charAt(j)) {
					count ++;
					if (m>n) i++;
					else j++;
				}
				else {
					i++;
					j++;
				}	
				if (count > 1) return false; 
			}
			
			// if the count is one, then i and j should equal m and n,
			// so that it will be one char deletion or addition
			if (count == 1) {
				if ( i == m && j == n) return true;
				else return false;
			}
			// if count is zero, then either i or j doesn't reach the end of their string
			else if (count == 0) {
				if ( i == m && j == n) return false;
				else if((i == m && j == n-1) || (i == m-1 && j == n)) 
					return true;
			}
			
		}
		
		return false;	
	}
	
}
