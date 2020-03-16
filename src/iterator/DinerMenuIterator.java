package iterator;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {

	private MenuItem[] menuItems;
	
	private int position;
	
	public DinerMenuIterator(MenuItem[] menuItems) {
		this.menuItems = menuItems;
		this.position = 0;
	}
	
	@Override
	public boolean hasNext() {
		if(position < menuItems.length && menuItems[position] != null) {
			return true;
		}
		return false;
	}

	@Override
	public MenuItem next() {
		return menuItems[position++];
	}

}
