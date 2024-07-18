package adapter.dock;

public class TurkeyTestDrive {
	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		Turkey turkey = new WildTurkey();
		Turkey duckAdapter = new DuckAdapter(duck);
		
		System.out.println("칠면조 어댑터가 말하길..");
		testTurkey(turkey);
		
		System.out.println("오리가 말하길...");
		testTurkey(duckAdapter);
	}
	
	static void testTurkey(Turkey turkey) {
		turkey.gobble();
		turkey.fly();
	}
}
