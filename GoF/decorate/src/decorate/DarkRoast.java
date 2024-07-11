package decorate;

public class DarkRoast extends Beverage {
	public DarkRoast() {
		description = "다크로스트 사이즈: " + getSize();
	}
	
	public double cost() {
		double baseCost = 0.99;
		
		// 사이즈별 추가 가격 설정
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
