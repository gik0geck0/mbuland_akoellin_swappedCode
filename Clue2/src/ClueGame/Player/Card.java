package ClueGame.Player;

public class Card {
	public enum Type {
		PERSON,
		WEAPON,
		ROOM
	}
	
	private String name;
	private Type type;
	
	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Card) {
			Card oc = (Card) o;
			return (this.name.equals(oc.getName()) && this.type == oc.getType());
		}
		return false;
	}
}
