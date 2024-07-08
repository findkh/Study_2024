package strategy_puzzles;

public class Troll extends Character{
	public Troll() {
		weapon = new AxeBehavior();
	}
	
	@Override
	public void display() {
		System.out.println("Troll 이다.");
	}
}