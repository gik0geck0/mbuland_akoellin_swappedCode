package clueTests;

import java.util.LinkedList;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ClueGame.Board;
import ClueGame.BoardCell;

public class BoardPathingTests {

	private Board board;

	@Before
	public void initBoard() {
		board = new Board();
		board.loadConfigFiles();
	}
	
	@Test
	public void testToFunctions() {
		int startLocation = 15;
		int[] rowCol = toRowCol(startLocation);
		int endLocation = toLocation(rowCol[0], rowCol[1]);
		Assert.assertEquals(startLocation, endLocation);
	}
	
	@Test
	public void testAdjacencyWalkway() {
		LinkedList<Integer> cells = board.getAdjList(4, 4);
		Assert.assertEquals(cells.contains(toLocation(3, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(5, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(4, 3)), true);
		Assert.assertEquals(cells.contains(toLocation(4, 5)), true);
	}
	
	@Test
	public void testAdjacencyEdge1() {
		LinkedList<Integer> cells = board.getAdjList(0, 4);
		Assert.assertEquals(cells.contains(toLocation(1, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(0, 5)), true);
	}

	@Test
	public void testAdjacencyEdge2() {
		LinkedList<Integer> cells = board.getAdjList(4, 0);
		Assert.assertEquals(cells.contains(toLocation(5, 0)), true);
		Assert.assertEquals(cells.contains(toLocation(4, 1)), true);
	}
	
	@Test
	public void testAdjacencyRoom1() {
		LinkedList<Integer> cells = board.getAdjList(2, 4);
		Assert.assertEquals(cells.contains(toLocation(1, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(3, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(2, 5)), true);
	}

	@Test
	public void testAdjacencyRoom2() {
		LinkedList<Integer> cells = board.getAdjList(10, 1);
		Assert.assertEquals(cells.contains(toLocation(10, 0)), true);
		Assert.assertEquals(cells.contains(toLocation(10, 2)), true);
		Assert.assertEquals(cells.contains(toLocation(11, 1)), true);
	}
	
	@Test
	public void testAdjacencyDoorway1() {
		LinkedList<Integer> cells = board.getAdjList(4, 2);
		Assert.assertEquals(cells.contains(toLocation(3, 2)), true);
		Assert.assertEquals(cells.contains(toLocation(4, 1)), true);
		Assert.assertEquals(cells.contains(toLocation(4, 3)), true);
		Assert.assertEquals(cells.contains(toLocation(5, 2)), true);
	}
	
	@Test
	public void testAdjacencyDoorway2() {
		LinkedList<Integer> cells = board.getAdjList(11, 4);
		Assert.assertEquals(cells.contains(toLocation(11, 3)), true);
		Assert.assertEquals(cells.contains(toLocation(11, 5)), true);
		Assert.assertEquals(cells.contains(toLocation(10, 4)), true);
		Assert.assertEquals(cells.contains(toLocation(12, 4)), true);
	}
	
	@Test
	public void testWalkwayPath1() {
		board.calcTargets(toLocation(5, 20), 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 20))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 18))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(4, 19))), true);
	}
	
	@Test
	public void testWalkwayPath2() {
		board.calcTargets(toLocation(11, 20), 2);
		Set<BoardCell> targets = board.getTargets();
		
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 20))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 18))), true);
	}
	
	@Test
	public void testWalkwayPath3() {
		board.calcTargets(toLocation(11, 13), 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 11))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 15))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(10, 12))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(12, 14))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(13, 13))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 13))), true);
	}
	
	@Test
	public void testWalkwayPath4() {
		board.calcTargets(toLocation(5, 15), 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 13))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 17))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(4, 14))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(4, 16))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 15))), true);
	}
	
	@Test
	public void testDoorwayPath1() {
		board.calcTargets(toLocation(14, 8), 2);
		Set<BoardCell> targets = board.getTargets();
		System.out.println(board.getCells().get(toLocation(14,8)).isDoorway());
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(13, 7))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(12, 8))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(13, 9))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(15, 9))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(14, 8))), true);
	}
	
	@Test
	public void testDoorwayPath2() {
		board.calcTargets(toLocation(13, 14), 1);
		Set<BoardCell> targets = board.getTargets();
		
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(13, 15))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(13, 13))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(12, 14))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(14, 14))), true);
	}
	
	@Test
	public void testLeavingPath1() {
		board.calcTargets(toLocation(6, 2), 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 1))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(4, 2))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(5, 3))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(6, 2))), true);
	}
	
	@Test
	public void testLeavingPath2() {
		board.calcTargets(toLocation(10, 17), 2);
		Set<BoardCell> targets = board.getTargets();
		System.out.println("10,17 " +board.getCells().get(toLocation(10,17)).isRoom());
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 16))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(11, 18))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(10, 17))), true);
		Assert.assertEquals(targets.contains(board.getCells().get(toLocation(12, 17))), true);
	}
	
	public int toLocation(int row, int col) {
		return row * board.getNumColumns() + col;
	}
	
	public int[] toRowCol(int location) {
		int row = location / board.getNumColumns();
		int col = location - row * board.getNumColumns();
		return new int[] { row, col };
	}
	
}
