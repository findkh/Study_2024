package strategy;

public class MiniDuckSimulator {
	public static void main(String[] args) {
		Duck mallard = new MallardDuck();
		mallard.performQuack();
		mallard.performFly();
		
		System.out.println("==============");
		
		Duck model = new ModelDuck();
		model.performFly();
		model.setFlyBehavior(new FlyRocketPowered()); //동적으로 행동 지정
		model.performFly();
	}
}
