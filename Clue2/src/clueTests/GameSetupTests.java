package clueTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ClueGame.Board.Board;
import ClueGame.Board.BoardCell;
import ClueGame.Player.Card;
import ClueGame.Player.ComputerPlayer;
import ClueGame.Player.Player;

public class GameSetupTests {
	Board brd;
	
	@Before
	public void init() {
		brd = new Board();
		brd.deal();
	}
	
	@Test
	public void deckPreDeal() {
		List<Card> testList = brd.getDeck();
		assertEquals(20, testList.size());
		int rooms = 0;
		int weapons = 0;
		int people = 0;
		
		boolean weaponNamesWork = false;
		boolean roomNamesWork = false;
		boolean personNamesWork = false;
		
		for (Card c : testList) {
			switch (c.getType()) {
			case PERSON:
				if (c.getName() == "Dr. Nefarious")
					personNamesWork = true;
				people++; break;
			case ROOM:
				if (c.getName() == "Tower")
					roomNamesWork = true;
				rooms++; break;
			case WEAPON:
				if (c.getName() == "Flying Spaghetti Monster")
					weaponNamesWork = true;
				weapons++; break;
			}
		}
		
		assertTrue(weaponNamesWork && roomNamesWork && personNamesWork);
		
		assertEquals(9, rooms);
		assertEquals(5, people);
		assertEquals(6, weapons);
	}
	
	@Test
	public void postDeal() {
		boolean someoneHasSolutionCards = false;
		boolean everyOneHasSameNUMBERCards = true;
		for (Player p : brd.getPlayers()) {
			for (Card c : p.getCards()) {
			someoneHasSolutionCards = someoneHasSolutionCards
				&& brd.solutionContainsCard(c);
			}
			everyOneHasSameNUMBERCards = everyOneHasSameNUMBERCards &&
					p.getCards().size() == 3;
		}
		assertFalse(someoneHasSolutionCards);
		assertTrue(everyOneHasSameNUMBERCards);
	}
	
	@Test
	public void testAccusation() {
		assertTrue(brd.checkAccusation("Dr. Nefarious", "Flying Spaghetti Monster", "Tower"));
		
		assertFalse(brd.checkAccusation("Jim", "Flying Spaghetti Monster", "Tower"));
		assertFalse(brd.checkAccusation("Dr. Nefarious", "M1A1 Abrahms Tank", "Tower"));
		assertFalse(brd.checkAccusation("Dr. Nefarious", "Flying Spaghetti Monster", "Kitchen"));
	}
	
	@Test 
	public void testTargetSelction() {
		ComputerPlayer comp = new ComputerPlayer();
		Set<BoardCell> rolls = new HashSet<BoardCell>();
		
		for (int i=0; i < 1000; i++) {
			int roll = Math.abs((new Random()).nextInt() % 6);
			rolls.add(comp.pickMove(roll));
		}
		boolean containsEach = true;
		for (BoardCell bc : comp.getTargets()) {
			if (!(bc.getChar() == comp.getLastRoom()))
				containsEach = containsEach && rolls.contains(bc);
		}
	}
	
	@Test
	public void testDisproveSuggestion() {
		List<Player> comp1 = new ArrayList<Player>();
		comp1.add(new ComputerPlayer());
		brd.setPlayers(comp1);
		Player comp = brd.getPlayers().get(0);
		
		List<Card> hand = new ArrayList<Card>();
		hand.add(new Card("Jim", Card.Type.PERSON));
		hand.add(new Card("Kitchen", Card.Type.ROOM));
		hand.add(new Card("M1A1 Abrahms Tank", Card.Type.WEAPON));
		comp.dealCards(hand);
		Card c = brd.disproveSuggestion(new ComputerPlayer(), "Jim", "The Magic Schoolbus", "Indoor Pool");
		assertTrue(c.equals(hand.get(0)));
		
		hand = new ArrayList<Card>();
		hand.add(new Card("Jim", Card.Type.PERSON));
		hand.add(new Card("Kitchen", Card.Type.ROOM));
		hand.add(new Card("The Magic Schoolbus", Card.Type.WEAPON));
		comp.dealCards(hand);
		c = brd.disproveSuggestion(new ComputerPlayer(), "Jim", "The Magic Schoolbus", "Indoor Pool");
		assertTrue(c.equals(hand.get(0)) || c.equals(hand.get(2)));
		
		hand = new ArrayList<Card>();
		hand.add(new Card("Dr. Nefarious", Card.Type.PERSON));
		hand.add(new Card("Kitchen", Card.Type.ROOM));
		hand.add(new Card("Cotton Balls", Card.Type.WEAPON));
		comp.dealCards(hand);
		c = brd.disproveSuggestion(new ComputerPlayer(), "Jim", "The Magic Schoolbus", "Indoor Pool");
		assertTrue(c == null);
		
		comp1.add(new ComputerPlayer());
		
		c = brd.disproveSuggestion(new ComputerPlayer(), "Jim", "The Magic Schoolbus", "Indoor Pool");
		
		List<Player> hum1 = new ArrayList<Player>();
		hum1.add(new ComputerPlayer());
		brd.setPlayers(hum1);
		
		Player hum =  brd.getPlayers().get(0);
		hand = new ArrayList<Card>();
		hand.add(new Card("Dr. Nefarious", Card.Type.PERSON));
		hand.add(new Card("Indoor Pool", Card.Type.ROOM));
		hand.add(new Card("M1A1 Abrahms Tank", Card.Type.WEAPON));
		hum.dealCards(hand);
		c = brd.disproveSuggestion(new ComputerPlayer(), "Jim", "The Magic Schoolbus", "Indoor Pool");
		assertTrue(c.equals(hand.get(0)));
		
		hum.disproveSuggestion("Dr. Nefarious", "M1A1 Abrahms Tank", "Indoor Pool");
	}
	
	
}
