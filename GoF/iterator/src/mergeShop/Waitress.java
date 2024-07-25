package mergeShop;

public class Waitress {
	
//	PanckaeHouseMenu pancakeHouseMenu = new PanckaeHouseMenu();
//	ArrayList<MenuItem> breakfastItems = pancakeHouseMenu.getMenuItems();
//	
//	DinerMenu dinerMenu = new DinerMenu();
//	MenuItem[] lunchItems = dinerMenu.getMenuItems();
//	
//	for(int i = 0; i < breakfastItem.size(); i++) {
//		MenuItem menuItem = breakfastItems.get(i);
//		System.out.println(menuItem.getName() + " ");
//		System.out.println(menuItem.getPrice() + " ");
//		System.out.println(menuItem.getDescription());
//	}
//	
//	for(int i = 0; i < lunchItems.length; i++) {
//		MenuItem menuItem = lunchItems[i];
//		System.out.println(menuItem.getName() + " ");
//		System.out.println(menuItem.getPrice() + " ");
//		System.out.println(menuItem.getDescription());
//	}
	
	PancakeHouseMenu pancakeHouseMenu;
	DinerMenu dinerMenu;
	
	public Waitress(PancakeHouseMenu pancakeHouseMenu, DinerMenu dinerMenu) {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	
	public void printMenu() {
		Iterator pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator dinerIterator = dinerMenu.createIterator();
		
		System.out.println("메뉴\n=====\n아침 메뉴");
		printMenu(pancakeIterator);
		
		System.out.println("\n점심 메뉴");
		printMenu(dinerIterator);
	}
	
	public void printMenu(Iterator iterator) {
		while(iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}
