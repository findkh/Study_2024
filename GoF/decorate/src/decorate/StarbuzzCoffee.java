package decorate;

import decorate.Beverage.Size;

public class StarbuzzCoffee {
	public static void main(String args[]) {
		//아무것도 넣지 않은 에스프레소 그란데 사이즈
		Beverage beverage = new Espresso();
		beverage.setSize(Size.GRANDE);
		System.out.println(beverage.getDescription() + " $" + beverage.cost());
		
		System.out.println("===========================");
		
		//다크로스트 + 모카 2개 + 휘핑크림 + 벤티 사이즈
		Beverage beverage2 = new DarkRoast();
		beverage2.setSize(Size.TALL);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
		
		System.out.println("===========================");
		
		//하우스 블렌드 + 두유 + 모카 + 휘핑크림
		Beverage beverage3 = new HouseBlend();
		beverage3 = new Mocha(beverage3);
		beverage3 = new Soy(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println(beverage3.getDescription() + " $" + beverage3.cost());
		
	}
}
