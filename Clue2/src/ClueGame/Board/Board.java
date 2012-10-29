package ClueGame.Board;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import ClueGame.Player.Card;
import ClueGame.Player.Player;

public class Board {

	private ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private Map<Character, String> rooms = new HashMap<Character, String>();
	private int numRows;
	private int numColumns;

	private static int GRID_PIECES;
	private Map<Integer, LinkedList<Integer>> adjMtx = new HashMap<Integer, LinkedList<Integer>>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
	private List<Card> deck = new ArrayList<Card>();
	private List<Player> players = new ArrayList<Player>();
	private int whoseTurn;
	
	public void setPlayers(List<Player> playList) {
		players = playList;
	}

	public void loadConfigFiles() {
		loadLegend();
		loadBoard();
		loadPlayers();
		loadDeck();
	}
	
	private void loadPlayers() {
		try {
			FileReader read = new FileReader("people.txt");
			Scanner scan = new Scanner(read);
			String person;
			while(scan.hasNextLine()) {
				person = scan.nextLine();
				players.add(new Player(person));
			}
		} catch(FileNotFoundException e){
			e.getStackTrace();
		}
	}
	
	private void loadDeck() {
		try {
			//load player cards
			FileReader read = new FileReader("people.txt");
			Scanner scan = new Scanner(read);
			int i = 0;
			while(scan.hasNextLine()) {
				deck.add(i, new Card(scan.nextLine(), Card.Type.PERSON));
				++i;
			}
			
			//load weapon cards
			read = new FileReader("weapons.txt");
			scan = new Scanner(read);
			while(scan.hasNextLine()) {
				deck.add(i, new Card(scan.nextLine(), Card.Type.WEAPON));
				++i;
			}
			
			//load rooms
			read = new FileReader("legenBoard.txt");
			scan = new Scanner(read);
			String in = "";
			String name = "";
			while (scan.hasNextLine()) {
				in = scan.nextLine();
				String[] vars = in.split(", ");
				name = vars[1].substring(0);
				char n = vars[0].charAt(0);
				if (n != 'W')
					deck.add(i, new Card(name, Card.Type.ROOM));
					++i;
			}
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
		
	}

	private void loadLegend() {
		try {

			FileReader read = new FileReader("legendBoard.txt");
			Scanner scan = new Scanner(read);
			String in = "";
			String name = "";
			int i = 0;
			while (scan.hasNextLine()) {
				i++;
				in = scan.nextLine();
				String[] vars = in.split(", ");
				name = vars[1].substring(0);
				char n = vars[0].charAt(0);
				if (n != 'W')
					rooms.put(n, name);
			}
			scan.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void loadBoard() {
		try {
			int row = 0;
			int col = 0;
			FileReader reader = new FileReader("Board.txt");
			Scanner scan = new Scanner(reader);
			String part = "";
			while (scan.hasNextLine()) {
				row++;
				part = scan.nextLine();
				String[] vars = part.split(",");
				for (String str : vars) {
					if (row <= 1)
						col++;
					if (str.equals("W")) {
						WalkwayCell c = new WalkwayCell();
						cells.add(c);
					} else {
						RoomCell c = new RoomCell(str);
						cells.add(c);
					}
				}
				if (row <= 1)
					setNumColumns(col);
			}
			setNumRows(row);
			scan.close();
			GRID_PIECES = numRows * numColumns;
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public int calcIndex(int row, int col) {
		return numColumns * row + col;
	}

	public RoomCell getRoomCellAt(int row, int col) {
		RoomCell roomCell = new RoomCell();
		roomCell = (RoomCell) getCells().get(calcIndex(row, col));
		return roomCell;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<BoardCell> cells) {
		this.cells = cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Character, String> rooms) {
		this.rooms = rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public LinkedList<Integer> getAdjList(int row, int col) {
		return getAdjList(calcIndex(row, col));
	}

	public void calcAdjacencies() {
		// for loops
		for (int i = 0; i < GRID_PIECES; ++i) {
			adjMtx.put(i, getAdjList(i));
		}
	}

	public void calcTargets(int startLoc, int steps) {
		targets.clear();
		calcTargetsRecursion(startLoc, steps);
	}

	private void calcTargetsRecursion(int startLoc, int steps) {
		if (steps == 0) {
			targets.add(getCells().get(startLoc));
			return;
		}
		LinkedList<Integer> start = getAdjList(startLoc);
		for (int i = 0; i < start.size(); i++) {
			calcTargetsRecursion(start.get(i), steps - 1);
		}
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public LinkedList<Integer> getAdjList(int cell) {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if (cell % numColumns == 0) {
			// left edge
			if (cell == 0) {
				// top
				adjList.add(cell + 1);
				adjList.add(cell + numColumns);
			} else if (cell == GRID_PIECES - numColumns) {
				// bottom
				adjList.add(cell + 1);
				adjList.add(cell - numColumns);
			} else {
				// inbetween
				adjList.add(cell + 1);
				adjList.add(cell + numColumns);
				adjList.add(cell - numColumns);
			}
		} else if ((cell + 1) % numColumns == GRID_PIECES % numColumns) {
			// right edge
			if (cell == numColumns - 1) {
				// top
				adjList.add(cell - 1);
				adjList.add(cell + numColumns);
			} else if (cell == GRID_PIECES - 1) {
				// bottom
				adjList.add(cell - 1);
				adjList.add(cell - numColumns);
			} else {
				// inbetween
				adjList.add(cell - 1);
				adjList.add(cell + numColumns);
				adjList.add(cell - numColumns);
			}
		} else if (cell < numColumns) {
			// on top
			adjList.add(cell + 1);
			adjList.add(cell - 1);
			adjList.add(cell + numColumns);
		} else if (cell > (GRID_PIECES - numColumns)) {
			// on bottom
			adjList.add(cell + 1);
			adjList.add(cell - 1);
			adjList.add(cell - numColumns);
		} else {
			// inbetween
			adjList.add(cell + 1);
			adjList.add(cell - 1);
			adjList.add(cell + numColumns);
			adjList.add(cell - numColumns);
		}
		return adjList;
	}
	
	public void selectAnswer() {
		
	}
	
	public void deal(List<Card> deck) {
		
	}
	
	public void deal() {
		deal(deck);
	}
	
	public boolean checkAccusation(String person, String weapon, String room) {
		return false;
	}
	
	public boolean handleSuggestion(String person, String weapon, String room) {
		return false;
	}
	
	public List<Card> getDeck() {
		return deck;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public boolean solutionContainsCard(Card c) {
		return false;
	}
	
	public Card disproveSuggestion(Player suggester, String person, String weapon, String room) {
		return null;
	}
}
