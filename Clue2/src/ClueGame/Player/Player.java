package ClueGame.Player;

import java.util.List;
import java.util.Random;
import java.util.Set;

import ClueGame.Board.Board;
import ClueGame.Board.BoardCell;

public class Player {
	private String name;
	private List<Card> hand;
	private int currentPosition;
	private Set<BoardCell> targets;
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	public Card disproveSuggestion(String person, String weapon, String room) {
		return null;
	}
	
	public List<Card> getCards() {
		return hand;
	}
	
	public void initTurn(Board b) {
		int roll = (new Random()).nextInt() % 6;
		b.calcTargets(currentPosition, roll);
		targets = b.getTargets();
	}
}
