package ClueGame.Board;

public abstract class BoardCell {
	private int row;
	private int col;
	private char name;
	public boolean isWalkway(){return false;}
	public boolean isRoom(){return false;}
	public boolean isDoorway(){return false;}
	public String toString(){return "row: " +row +". Col: " + col;}
	//abstract void draw(); 
	
	public char getChar() {
		return name;
	}
}
