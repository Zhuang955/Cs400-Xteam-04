/*
p4 Dictionary Graph
Kylie Huang, Lan Hu, Henry Huang, Gloria Hsieh
Email addresses: zhuang95@wisc.edu, lhu49@wisc.edu, hhuang266@wisc.edu, ghsieh@wisc.edu 
Due date: February 5, 2018
Source Credits: Deb Deppeler 
Known bugs: None
*/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GraphProcessorTest {
	
	GraphProcessor gp = null;
	String expected = null;
    String actual = null;
	String filePath = "src/words.txt";
    
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        gp = new GraphProcessor();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    /** tests that populateGraph works and output the correct number of vertices*/
    public void test01_populateGraph() {
        expected = "6";
        actual = "" + gp.populateGraph(filePath);
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestPath returns the shortest path when inputs are different */
    public void test02_getShortestPath_diffWords() {
        expected = "[CAT, HAT, HEAT, WHEAT]";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        List<String> steps = gp.getShortestPath("cat", "wheat");
        actual = "[";
        for (int n = 0; n < steps.size() - 1; n++) {
        	actual = actual.concat(steps.get(n) + ", ");
        }
        actual = actual.concat(steps.get(steps.size() - 1)) + "]";
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestPath returns an empty list when inputs are the same word */
    public void test03_getShortestPath_sameWords() {
        expected = "true";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        List<String> steps = gp.getShortestPath("cat", "cat");
        actual = "" + steps.isEmpty();
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestPath returns an empty list when there is no path */
    public void test04_getShortestPath_noPath() {
        expected = "true";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        List<String> steps = gp.getShortestPath("cat", "kit");
        actual = "" + steps.isEmpty();
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestDistance returns the right number when inputs are different */
    public void test05_getShortestDistance_diffWords() {
    	expected = "3";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        actual = "" + gp.getShortestDistance("cat", "wheat");
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestDistance returns -1 when inputs are same */
    public void test06_getShortestDistance_sameWords() {
        expected = "-1";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        actual = "" + gp.getShortestDistance("cat", "cat");
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
    
    @Test
    /** tests that getShortestDistance returns -1 when there is no path */
    public void test07_getShortestDistance_noPath() {
        expected = "-1";
        gp.populateGraph(filePath);
        gp.shortestPathPrecomputation();
        actual = "" + gp.getShortestDistance("cat", "kit");
        if (! expected.equals(actual))
            fail("expected: "+expected+ " actual: "+actual);
    }
}
