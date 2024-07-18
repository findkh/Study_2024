package adapter.dock;

public class MallardDuck implements Duck{

	@Override
	public void quack() {
		System.out.println("꽥");
		
	}

	@Override
	public void fly() {
		System.out.println("날고 있다.");
		
	}

}
