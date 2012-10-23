package ClueGame;

public class RoomCell extends BoardCell {
	
	public RoomCell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomCell(String roomString) {
		super();
		if(roomString.length()== 1)
			this.roomInitial = roomString.charAt(0);
		else{
			
			this.roomInitial = roomString.charAt(0);
			if(roomString.charAt(1) == 'R')
				setDoorDirection(doorDirection.RIGHT);
			if(roomString.charAt(1) == 'L')
				setDoorDirection(doorDirection.LEFT);
			if(roomString.charAt(1) == 'D')
				setDoorDirection(doorDirection.DOWN);
			if(roomString.charAt(1) == 'U')
				setDoorDirection(doorDirection.UP);
		}
	}

	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE;
	}
	private DoorDirection doorDirection;
	private char roomInitial;
	public boolean isDoorway(){
		if(doorDirection != null )
			return true;
		return false;
	}
	public boolean isRoom(){return true;}
	//private void draw(){};

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}
	
}
