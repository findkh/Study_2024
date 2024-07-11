package decorate;

public class Espresso extends Beverage {
	public Espresso() {
		description = "에스프레소 사이즈: " + getSize();
	}
	
	public double cost() {
		double baseCost = 1.99;
		
		switch (getSize()) {
			case TALL:
				return baseCost;
			case GRANDE:
				return baseCost + 0.20;
			case VENTI:
				return baseCost + 0.40;
			default:
				return baseCost;
		}
	}
}
