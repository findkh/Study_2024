package decorate;

public class Decaf extends Beverage {
	public Decaf() {
		description = "디카페인 사이즈: " + getSize();
	}
	
	public double cost() {
		double baseCost = 1.05;
		
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
