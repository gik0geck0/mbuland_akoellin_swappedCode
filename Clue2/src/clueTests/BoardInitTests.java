package clueTests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ClueGame.Board;
import ClueGame.BoardCell;
import ClueGame.RoomCell;
import ClueGame.RoomCell.DoorDirection;

public class BoardInitTests {
	
	Board gameBoard;

	@Before
	public void setup() {
		gameBoard = new Board();
		gameBoard.loadConfigFiles();
		//gameBoard.loadLegend();
		//gameBoard.loadBoard();
	}
	
	@Test
	public void testRoomCount() {
		Assert.assertEquals(gameBoard.getRooms().size(), 9);
	}
	
	@Test
	public void testCharacterMapping() {
		//Assert.assertEquals(gameBoard.getRooms().get('W'), "Walkway");
		Assert.assertEquals(gameBoard.getRooms().get('1'), "Conservatory");
		Assert.assertEquals(gameBoard.getRooms().get('2'), "Indoor Pool");
		Assert.assertEquals(gameBoard.getRooms().get('3'), "Kitchen");
		Assert.assertEquals(gameBoard.getRooms().get('4'), "Study");
		Assert.assertEquals(gameBoard.getRooms().get('5'), "Dining Room");
		Assert.assertEquals(gameBoard.getRooms().get('6'), "Living Room");
		Assert.assertEquals(gameBoard.getRooms().get('7'), "Entryway");
		Assert.assertEquals(gameBoard.getRooms().get('8'), "Library");
		Assert.assertEquals(gameBoard.getRooms().get('9'), "Tower");
	}
	
	@Test
	public void testNumRows() {
		Assert.assertEquals(gameBoard.getNumRows(), 16);
	}
	
	@Test
	public void testNumCols() {
		Assert.assertEquals(gameBoard.getNumColumns(), 21);
	}
	
	@Test
	public void testDoors() {
		Assert.assertEquals(gameBoard.getRoomCellAt(3, 2).isDoorway(), true);
		Assert.assertEquals(gameBoard.getRoomCellAt(3, 2).getDoorDirection(), DoorDirection.DOWN);
		Assert.assertEquals(gameBoard.getRoomCellAt(2, 2).isDoorway(), false);
	}
	
	@Test
	public void testNumberRecognizedRooms() {
		Set<RoomCell> rooms = new HashSet<RoomCell>();
		List<BoardCell> board = gameBoard.getCells();
		for(BoardCell bc : board) {
			if(bc.isRoom()) {
				rooms.add((RoomCell) bc);
			}
		}
		Assert.assertEquals(rooms.size(), 210);
	}
	
	@Test
	public void testRoomInitials() {
		Assert.assertEquals(gameBoard.getRoomCellAt(2, 2).getRoomInitial(), '1');
		Assert.assertEquals(gameBoard.getRoomCellAt(2, 9).getRoomInitial(), '2');
		Assert.assertEquals(gameBoard.getRoomCellAt(0, 18).getRoomInitial(), '3');
		Assert.assertEquals(gameBoard.getRoomCellAt(8, 6).getRoomInitial(), '4');
		Assert.assertEquals(gameBoard.getRoomCellAt(7, 20).getRoomInitial(), '5');
		Assert.assertEquals(gameBoard.getRoomCellAt(13, 7).getRoomInitial(), '6');
		Assert.assertEquals(gameBoard.getRoomCellAt(14, 12).getRoomInitial(), '7');
		Assert.assertEquals(gameBoard.getRoomCellAt(15, 20).getRoomInitial(), '8');
		Assert.assertEquals(gameBoard.getRoomCellAt(8, 10).getRoomInitial(), '9');
	}
	
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(gameBoard.calcIndex(0, 0), 0);
		Assert.assertEquals(gameBoard.calcIndex(1, 0), gameBoard.getNumColumns());
	}

}
