package clueTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		Player comp =  brd.getPlayers().get(2);
		Card c = comp.disproveSuggestion("Jim", "The Magic Schoolbus", "Indoor Pool");
	}
	
	
}
