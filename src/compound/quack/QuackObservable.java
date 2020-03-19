package compound.quack;

public interface QuackObservable {
	
	void registerObserver(Observer observer);
	
	void notifyObservers();
}
