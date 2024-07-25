package mergeCafe;

import java.util.Arrays;

public class MenuTest {
	public static void main(String[] args) {
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		DinerMenu dinerMenu = new DinerMenu();
		CafeMenu cafeMenu = new CafeMenu();
		
		Waitress waitress = new Waitress(Arrays.asList(pancakeHouseMenu, dinerMenu, cafeMenu));
		
		waitress.printMenu();
	}
}
