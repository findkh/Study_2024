package singleton.chocolate;

public class ChocolateBoiler {
	/* 멀티스레딩 문제 해결하기
	 * 1.getInstance()의 속도가 중요하지 않다면 그냥 둔다.
	 *	- 메소드를 동기화하면 성능이 100배 정도 저하된다.
	 * 2. 인스턴스가 필요할 때는 생성하지 말고 처음부터 만든다.
	 * 	- Singleton의 인스턴스를 생성하고 계속 사용하거나 인스턴스를 실행중에 수시로 만들고 관리하기 어렵다면 정적 초기화 부분에서 싱글톤의 인스턴스를 생성한다.
	 * 3. DCL(Double-Checked Locking)을 사용
	 * 	- DCL을 사용하면 인스턴스가 생성되어 있는지 확인 한 다음에 생성되어 있지 않을 때만 동기화할 수 있다.
	 * */
	
	private boolean empty;
	private boolean boiled;
	private static ChocolateBoiler uniqueInstance;
	
	private ChocolateBoiler() {
		empty = true;
		boiled = false;
	}
	
	//synchronized를 사용하여 2개의 스레드가 메소드를 동시에 실행하는 일이 일어나지 않도록 막는다.
	public static synchronized ChocolateBoiler getInstance() {
		if(uniqueInstance == null) {
			System.out.println("고유 인스턴스 생성");
			uniqueInstance = new ChocolateBoiler(); 
		}
		System.out.println("고유 인스턴스 반환");
		return uniqueInstance;
	}
	
	 public void fill() {
		if(isEmpty()) {
			empty = false;
			boiled = false;
		}
	}
	
	public void drain() {
		if(!isEmpty() && isBoiled()) {
			empty = true;
		}
	}
	
	public void boil() {
		if(!isEmpty() && !isBoiled()) {
			boiled = true;
		}
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public boolean isBoiled() {
		return boiled;
	}
}
