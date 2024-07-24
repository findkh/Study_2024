package barista_abstract;

public class BaristaHookTest {
	public static void main(String[] args) {
		TeaWithHook teaHook = new TeaWithHook();
		CoffeeWithHook coffeeHook = new CoffeeWithHook();
 
		System.out.println("홍차를 만듭니다.");
		teaHook.prepareRecipe();
		
		System.out.println("커피를 만듭니다.");
		coffeeHook.prepareRecipe();
	}
}
