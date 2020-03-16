package composite;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<MenuComponent>{
	private Stack<Iterator<MenuComponent>> stack = new Stack<>();
	
	public CompositeIterator(Iterator<MenuComponent> iterator) {
		stack.push(iterator);
	}
	
	@Override
	public MenuComponent next() {
		if(this.hasNext()) {
			Iterator<MenuComponent> iterator = stack.peek();
			MenuComponent menuComponent = iterator.next();
			if(menuComponent instanceof Menu) {
				stack.push(menuComponent.createIterator());
			}
			return menuComponent;
		}
		
		return null;
	}
	
	@Override
	public boolean hasNext() {
		if(stack.isEmpty()) {
			return false;
		}
		
		Iterator<MenuComponent> iterator = stack.peek();
		if(!iterator.hasNext()) {
			stack.pop();
			return this.hasNext();
		}
		
		return true;
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
