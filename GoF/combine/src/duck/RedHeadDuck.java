package duck;

public class RedHeadDuck implements Quackable {
	Observable observable;
	
	public RedHeadDuck() {
		observable = new Observable(this);
	}
	
	@Override
	public void quack() {
		System.out.println("꽥꽥");
		notifyObservers();
	}
	
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}
	
	public void notifyObservers() {
		observable.notifyObservers();
	}
	
	public String toString() {
		return "붉은머리오리";
	}
}
