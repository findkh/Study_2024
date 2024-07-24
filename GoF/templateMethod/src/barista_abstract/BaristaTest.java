package barista_abstract;

public class BaristaTest {
	public static void main(String[] args) {
		Tea tea = new Tea();
		Coffee coffee = new Coffee();
		
		System.out.println("홍차를 만듭니다.");
		tea.prepareRecipe();
		
		System.out.println("커피를 만듭니다.");
		coffee.prepareRecipe();
	}
}
