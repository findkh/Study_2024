package factoryMethod_pizza;

public class PizzaTestDrive {
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
		PizzaStore chaliforniaStore = new CaliforniaPizzaStore();
		
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("에단이 주문한 " + pizza.getName() + "\n");
		
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("조엘이 주문한 " + pizza.getName() + "\n");
		
		pizza = chaliforniaStore.orderPizza("pepperoni");
		System.out.println("수달이 주문한 " + pizza.getName() + "\n");
		
	}
}