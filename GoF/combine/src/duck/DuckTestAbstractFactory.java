package duck;

public class DuckTestAbstractFactory {
	public static void main(String[] args) {
		DuckTestAbstractFactory test = new DuckTestAbstractFactory();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		
		test.test(duckFactory);
	}
	
	void test(AbstractDuckFactory duckFactory) {
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redHeadDuck = duckFactory.createRedheadDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable gooseDuck = new GooseAdapter(new Goose());
		
		System.out.println("오리 시뮬레이션 게임: 추상팩토리");
		
		test(mallardDuck);
		test(redHeadDuck);
		test(duckCall);
		test(rubberDuck);
		test(gooseDuck);
		
		System.out.println("오리가 소리 낸 횟수: " + QuackCounter.getQuacks());
	}
	
	void test(Quackable duck) {
		duck.quack();
	}
}
