package ClueGame.Player;

public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Card disproveSuggestion(String person, String weapon, String room) {
		for (Card c : getCards()) {
			if (c.getName().equals(person)|| c.getName().equals(weapon) || c.getName().equals(room)){
				return c;
			}
		}
		return null;
	}

}
