package decorate;

public class Milk extends CondimentDecorator {
	public Milk(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", 우유";
	}
	
	public double cost() {
		double cost = beverage.cost();
		if(beverage.getSize() == Size.TALL) {
			cost += .10;
		} else if(beverage.getSize() == Size.GRANDE) {
			cost += .15;
		} else {
			cost += .20;
		}
		return cost;
	}
}
