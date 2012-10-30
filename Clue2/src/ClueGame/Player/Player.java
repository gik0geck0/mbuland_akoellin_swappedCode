package ClueGame.Player;

import java.util.List;
import java.util.Random;
import java.util.Set;

import ClueGame.Board.Board;
import ClueGame.Board.BoardCell;

public class Player {
	private String name;
	public Player(String name) {
		super();
		this.name = name;
	}
	public Player() {
		super();
	}
	
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
	
	public void dealCards(List<Card> hand) {
		int extraCards = hand.size() % 5; // used to handel other players getting one more card
		int cardsLeft = hand.size();
		int randomNum = Math.abs((new Random()).nextInt() % cardsLeft);
		if(!this.hand.isEmpty()){
			if (extraCards > 0) {
				while(this.hand.size() != 4){
					this.hand.add(hand.get(randomNum));
					hand.remove(randomNum);
					cardsLeft--;
					randomNum = Math.abs((new Random()).nextInt() % cardsLeft);
				}
				extraCards--;
			}

			else {
				while(this.hand.size() != 3){
					this.hand.add(hand.get(randomNum));
					hand.remove(randomNum);
					cardsLeft--;
					randomNum = Math.abs((new Random()).nextInt() % cardsLeft);
				}
			}
		}
		else {
			this.hand.add(hand.get(randomNum));
			hand.remove(randomNum);
		}

	}
}
