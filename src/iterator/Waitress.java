package iterator;

import java.util.Iterator;

public class Waitress {
	
	private Menu pancakeHouseMenu;
	private Menu dinerMenu;
	private Menu cafeMenu;
	
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
		this.cafeMenu = cafeMenu;
	}
	
	public void printMenu() {
		System.out.println("메뉴\n----\n");

		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		System.out.println("--아침 메뉴--");
		printMenu(pancakeIterator);
		
		System.out.println();
		
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
		System.out.println("--점심 메뉴--");
		printMenu(dinerIterator);
		
		System.out.println();
		
		Iterator<MenuItem> cafeIterator = cafeMenu.createIterator();
		System.out.println("--저녁 메뉴--");
		printMenu(cafeIterator);
	}
	
	private void printMenu(Iterator<MenuItem> iterator) {
		while(iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
}
