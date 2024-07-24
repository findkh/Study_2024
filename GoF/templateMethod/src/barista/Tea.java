package barista;

public class Tea {
	void preapreRecipe() {
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}

	private void addLemon() {
		System.out.println("레몬을 추가하는 중...");
		
	}

	private void pourInCup() {
		System.out.println("컵에 따르는 중...");
		
	}

	private void steepTeaBag() {
		System.out.println("찻잎을 우려내는 중...");
		
	}

	private void boilWater() {
		System.out.println("물 끓이는 중...");
		
	}
}
