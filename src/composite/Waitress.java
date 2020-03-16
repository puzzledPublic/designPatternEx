package composite;

import java.util.Iterator;

public class Waitress {
	
	private MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void printMenu() {
		this.allMenus.print();
	}
	
	public void printVegetarianMenu() {
		Iterator<MenuComponent> iterator = this.allMenus.createIterator();
		System.out.println("\nVEGETARIAN MENU\n----");
		while(iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			
			try {
				if(menuComponent.isVegetarian()) {
					menuComponent.print();
				}
			}catch(UnsupportedOperationException e) {
				System.out.println("MenuItem(개별객체)이 아닌 Menu(복합객체)가 호출 됨. -- " + menuComponent.getName() + ", " + menuComponent.getDescription());
			}
		}
	}
}
