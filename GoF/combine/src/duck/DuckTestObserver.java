package duck;

public class DuckTestObserver {
	public static void main(String[] args) {
		DuckTestObserver test = new DuckTestObserver();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		
		test.test(duckFactory);
	}
	
	void test(AbstractDuckFactory duckFactory) {
		Quackable redHeadDuck = duckFactory.createRedheadDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable gooseDuck = new GooseAdapter(new Goose());
		
		System.out.println("오리 시뮬레이션 게임: 옵저버");
		
		Flock flockOfDucks = new Flock();
		
		flockOfDucks.add(redHeadDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(gooseDuck);
		
		Flock flockOfMallards = new Flock();
		
		Quackable mallardOne = duckFactory.createMallardDuck();
		Quackable mallardTwo = duckFactory.createMallardDuck();
		Quackable mallardThree = duckFactory.createMallardDuck();
		Quackable mallardFour  = duckFactory.createMallardDuck();
		
		flockOfMallards.add(mallardOne);
		flockOfMallards.add(mallardTwo);
		flockOfMallards.add(mallardThree);
		flockOfMallards.add(mallardFour);
		
		flockOfDucks.add(flockOfMallards);
		
		Quackologist quackologist = new Quackologist();
		flockOfDucks.registerObserver(quackologist);
		
		System.out.println("오리 시물레이션 게임: 전체무리");
		
		
		test(flockOfDucks);
		
		System.out.println();
		
		System.out.println("오리 시뮬레이션 게임: 물오리 무리");
		test(flockOfMallards);
		
		System.out.println("오리가 소리 낸 횟수: " + QuackCounter.getQuacks());
	}
	
	void test(Quackable duck) {
		duck.quack();
	}
}
