package decorate;

public class HouseBlend extends Beverage {
	public HouseBlend() {
		description = "하우스블렌드 사이즈: " + getSize();
	}
	
	public double cost() {
		double baseCost = 0.89;
		
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
