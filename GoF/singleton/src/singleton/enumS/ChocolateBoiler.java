package singleton.enumS;

public enum ChocolateBoiler {
	
	/* enum을 사용한 Singleton
	 * 
	 * 1. 간결성: 별도의 private static 변수를 선언하고, synchronized 메서드를 만들 필요가 없다.
	 * 2. 직렬화 보장: Enum 타입은 자바에서 기본적으로 직렬화 가능하므로, 별도의 구현 없이도 직렬화 과정에서 동일한 인스턴스를 유지한다.
	 * 3. 스레드 안전: Enum 타입의 인스턴스는 자바 런타임에 의해 스레드 안전하게 초기화된다.
	 * 4. 리플렉션 공격 방어: Enum 타입은 리플렉션을 사용해 인스턴스를 여러 개 생성할 수 없도록 설계되어 있다.
	 * */
	
	INSTANCE;

	private boolean empty;
	private boolean boiled;

	ChocolateBoiler() {
		empty = true;
		boiled = false;
	}

	public static ChocolateBoiler getInstance() {
		return INSTANCE;
	}

	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
		}
	}

	public void drain() {
		if (!isEmpty() && isBoiled()) {
			empty = true;
		}
	}

	public void boil() {
		if (!isEmpty() && !isBoiled()) {
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