package strategy_puzzles;

public class King extends Character{
	public King() {
		weapon = new SwordBehavior();
	}
	
	@Override
	public void display() {
		System.out.println("King이다.");
	}
}
