package decorate;

public class Whip extends CondimentDecorator {
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", 휘핑크림";
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
