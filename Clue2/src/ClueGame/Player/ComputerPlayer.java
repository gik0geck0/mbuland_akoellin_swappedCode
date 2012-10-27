package ClueGame.Player;

import java.util.List;

import ClueGame.Board.BoardCell;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public BoardCell pickMove(int roll) {	
		return null;
	}
	
	public void createSuggestion() {
		
	}
	
	public void updateSeen(Card seen) {
		
	}

	public char getLastRoom() {
		return lastRoomVisited;
	}
}
