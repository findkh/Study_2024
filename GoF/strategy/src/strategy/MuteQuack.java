package strategy;

public class MuteQuack implements QuackBehavior{
	public void quack() {
		System.out.println("(정적...)");
	}
}
