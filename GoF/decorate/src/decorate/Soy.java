package decorate;

public class Soy extends CondimentDecorator {
	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}
	
	public String getDescription() {
		return beverage.getDescription() + ", 두유";
	}
	
	public double cost() {
		double cost = beverage.cost();
		if(beverage.getSize() == Size.TALL) {
			cost += .15;
		} else if(beverage.getSize() == Size.GRANDE) {
			cost += .20;
		} else {
			cost += .25;
		}
		return cost;
	}
}
