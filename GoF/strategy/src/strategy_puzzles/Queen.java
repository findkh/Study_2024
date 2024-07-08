package strategy_puzzles;

public class Queen extends Character{
	public Queen() {
		weapon = new BowAndArrowBehavior();
	}
	
	@Override
	public void display() {
		System.out.println("Queen 이다.");
	}
}