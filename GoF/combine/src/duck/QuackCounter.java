package duck;

//데코레이터
public class QuackCounter implements Quackable {
	Quackable duck;
	static int numOfQuacks;
	
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}
	
	@Override
	public void quack() {
		duck.quack();
		numOfQuacks++;
	}
	
	public static int getQuacks() {
		return numOfQuacks;
	}
	
	public void registerObserver(Observer observer) {
		duck.registerObserver(observer);
	}
	
	public void notifyObservers() {
		duck.notifyObservers();
	}
}
