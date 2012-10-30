package ClueGame.Player;

import java.util.List;
import java.util.Random;
import java.util.Set;

import ClueGame.Board.Board;
import ClueGame.Board.BoardCell;

public abstract class Player {
	private String name;
	public Player(String name) {
		super();
		this.name = name;
	}
	public Player() {
		super();
	}
	
	protected List<Card> hand;
	private int currentPosition;
	private Set<BoardCell> targets;
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public Set<BoardCell> calcTargets(int roll, Board brd) {
		brd.calcTargets(currentPosition, roll);
		targets = brd.getTargets();
		return getTargets();
	}
	
	abstract public Card disproveSuggestion(String person, String weapon, String room);
	
	public List<Card> getCards() {
		return hand;
	}
	
	public void initTurn(Board b) {
		int roll = (new Random()).nextInt() % 6;
		b.calcTargets(currentPosition, roll);
		targets = b.getTargets();
	}
	
	public void giveHand(List<Card> hand) {
		this.hand = hand;
	}
}
