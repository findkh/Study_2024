package factoryMethod_pizza;

public abstract class PizzaStore {
	
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	protected abstract Pizza createPizza(String type); //팩토리 메소드를 추상 메소드로 선언해서 서브 클래스가 객체 생성을 하도록 한다.
}
