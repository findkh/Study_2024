package strategy;

public class ModelDuck extends Duck {
	public ModelDuck() {
		flyBehavior = new FlyNoWay();
		quackBehavior = new Quack();
	}
	
	public void display() {
		System.out.println("오리 모형입니다.");
	}
}
