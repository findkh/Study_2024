package decorate;

public class Mocha extends CondimentDecorator {
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", 모카";
	}
	
	public double cost() {
		double cost = beverage.cost();
		if(beverage.getSize() == Size.TALL) {
			cost += .20;
		} else if(beverage.getSize() == Size.GRANDE) {
			cost += .25;
		} else {
			cost += .30;
		}
		return cost;
	}
}
