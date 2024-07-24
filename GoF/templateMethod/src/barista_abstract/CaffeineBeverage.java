package barista_abstract;

public abstract class CaffeineBeverage {
	
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		if(customerWantsCondiments()) {
			addCondiments();
		}
	}
	
	boolean customerWantsCondiments() {
		return true;
	}

	void boilWater() {
		System.out.println("물을 끓입니다.");
	}
	
	void pourInCup() {
		System.out.println("컵에 따르는 중...");
	}
	
	//커피와 홍치 클래스에서 서로 다른 방식으로 처리하므로 추상 메소드로 선언.
	abstract void addCondiments();
	
	abstract void brew();
}
