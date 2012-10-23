package tests;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.IntBoard;

public class IntBoardTests {
	private IntBoard board;
	@Before
	public void setup(){
		board = new IntBoard();
		board.calcAdjacencies();
	}
	@Test
	public void testCalcIndex1() {
		int actual =board.calcIndex(0,0);
		Assert.assertEquals(0, actual);
	}
	@Test
	public void testCalcIndex2() {
		int actual =board.calcIndex(1,1);
		Assert.assertEquals(5, actual);
	}
	@Test
	public void testCalcIndex3() {
		int actual =board.calcIndex(3,3);
		Assert.assertEquals(15, actual);
	}
	
	@Test
	public void testAdjecency1(){
		//test the adjacency list for the left top corner
		LinkedList adjList = board.getAdjList(0);
		Assert.assertTrue(adjList.contains(4));
		Assert.assertTrue(adjList.contains(1));
		Assert.assertEquals(2, adjList.size());
	}
	@Test
	public void testAdjecency2(){
		//test the adjacency list for the bottom right corner
		LinkedList adjList = board.getAdjList(15);
		Assert.assertTrue(adjList.contains(11));
		Assert.assertTrue(adjList.contains(14));
		Assert.assertEquals(2, adjList.size());
	}
	@Test
	public void testAdjecency3(){
		//test the adjacency list for the right edge
		LinkedList adjList = board.getAdjList(7);
		Assert.assertTrue(adjList.contains(3));
		Assert.assertTrue(adjList.contains(11));
		Assert.assertTrue(adjList.contains(6));
		Assert.assertEquals(3, adjList.size());
	}
	
	@Test
	public void testAdjacency4(){
		//test the adjacency list for the right edge
		LinkedList adjList = board.getAdjList(8);
		Assert.assertTrue(adjList.contains(4));
		Assert.assertTrue(adjList.contains(9));
		Assert.assertTrue(adjList.contains(12));
		Assert.assertEquals(3, adjList.size());
	}
	
	@Test
	public void testAdjacency5() {
		//test the adjacency list for the right edge
		LinkedList adjList = board.getAdjList(5);
		Assert.assertTrue(adjList.contains(1));
		Assert.assertTrue(adjList.contains(4));
		Assert.assertTrue(adjList.contains(6));
		Assert.assertTrue(adjList.contains(9));
		Assert.assertEquals(4, adjList.size());
	}
	
	@Test
	public void testAdjacency6() {
		//test the adjacency list for the right edge
		LinkedList adjList = board.getAdjList(10);
		Assert.assertTrue(adjList.contains(6));
		Assert.assertTrue(adjList.contains(9));
		Assert.assertTrue(adjList.contains(11));
		Assert.assertTrue(adjList.contains(14));
		Assert.assertEquals(4, adjList.size());
	}
	
	@Test
	public void testPath1() {
		board.calcTargets(0, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	
	@Test
	public void testPath2() {
		board.calcTargets(15, 3);
		Set<Integer> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(14));
	}
	
	@Test
	public void testPath3() {
		board.calcTargets(3, 3);
		Set<Integer> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(7));
	}
	
	@Test
	public void testPath4() {
		board.calcTargets(12, 3);
		Set targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(5));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(13));
	}
	
	@Test
	public void testPath5() {
		board.calcTargets(5, 2);
		Set targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(5));
	}
	
	@Test
	public void testPath6() {
		board.calcTargets(7, 1);
		Set targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(11));
	}
	
}
