package strategy_puzzles;

public class Knight extends Character{
	public Knight() {
		weapon = new KnifeBehavior();
	}
	
	@Override
	public void display() {
		System.out.println("Knight 이다.");
	}
}
