package composite;

import java.util.Iterator;

public abstract class MenuComponent {
	
	abstract public Iterator<MenuComponent> createIterator();
	
	//MenuComponent를 추가, 제거 및 검색하는 메서드
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
	
	//MenuItem에서 사용 할 메서드
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}
	
	//Menu, MenuItem에서 사용 할 메서드
	public void print() {
		throw new UnsupportedOperationException();
	}
	
}
