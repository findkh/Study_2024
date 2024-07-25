package mergeShop2;

public class MenuTest {
	public static void main(String[] args) {
		// PancakeHouseMenu와 DinerMenu 인스턴스를 생성
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		DinerMenu dinerMenu = new DinerMenu();
		
		// Waitress 객체 생성 시 Menu 타입으로 전달
		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
		
		// 메뉴 출력
		waitress.printMenu();
	}
}
