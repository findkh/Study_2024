package factoryMethod_pizza;

public class CaliforniaStylePepperoniPizza extends Pizza{
	public CaliforniaStylePepperoniPizza() {
		name = "캘리포니아 스타일 소스와 페페로니 피자";
		dough = "아주 두꺼운 크러스트 도우";
		sauce = "플럼토마토 소스";
		
		toppings.add("잘게 썬 모짜렐라 치즈");
	}
	
	void cut() {
		System.out.println("그냥 자르기");
	}
}
