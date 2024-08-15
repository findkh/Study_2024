package duck;

public class DuckTest {
	public static void main(String[] args) {
		DuckTest test = new DuckTest();
		test.test();
	}
	
	void test() {
		Quackable mallardDuck = new QuackCounter(new MallardDuck());
		Quackable redHeadDuck = new QuackCounter(new RedHeadDuck());
		Quackable duckCall = new QuackCounter(new DuckCall());
		Quackable rubberDuck = new QuackCounter(new RubberDuck());
		Quackable gooseDuck = new GooseAdapter(new Goose());
		
		System.out.println("오리 시뮬레이션 게임");
		
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
