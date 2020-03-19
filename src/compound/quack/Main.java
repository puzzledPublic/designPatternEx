package compound.quack;

public class Main {
	public static void main(String[] args) {
		
		System.out.println("Duck Simulator\n----------start----------");
		
		AbstractDuckFactory duckFactory = new CountingDuckFactory();	//factory method 패턴.
		
		simulate(duckFactory);
		
		System.out.println("----------end----------");
	}
	
	static void simulate(AbstractDuckFactory duckFactory) {
		
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redHeadDuck = duckFactory.createRedheadDuck();
		Quackable duckCall = duckFactory.createDuckCall();
		Quackable rubberDuck = duckFactory.createRubberDuck();
		Quackable gooseDuck = new GooseAdapter(new Goose());	//adapter 패턴.
		
		Flock flockOfDucks = new Flock();	//Composite 패턴.
		flockOfDucks.add(redHeadDuck);
		flockOfDucks.add(duckCall);
		flockOfDucks.add(rubberDuck);
		flockOfDucks.add(gooseDuck);
				
		Quackable mallardOne = duckFactory.createMallardDuck();
		Quackable mallardTwo = duckFactory.createMallardDuck();
		Quackable mallardThree = duckFactory.createMallardDuck();
		Quackable mallardFour = duckFactory.createMallardDuck();
		
		Flock flockOfMallards = new Flock();
		flockOfMallards.add(mallardOne);
		flockOfMallards.add(mallardTwo);
		flockOfMallards.add(mallardThree);
		flockOfMallards.add(mallardFour);
		
		flockOfDucks.add(flockOfMallards);
		
		System.out.println("Duck Simulator: Whole Flock Simulation------------");
		simulate(flockOfDucks);
		
		System.out.println("Duck Simulator: Mallard Flock Simulation------------");
		simulate(flockOfMallards);
		
		System.out.println("Duck Simulator: With Observer-----------");
		Quackologist quackologist = new Quackologist();
		flockOfDucks.registerObserver(quackologist);
		
		simulate(flockOfDucks);
		
		System.out.println("The ducks quacked " + QuackCounter.getQuacks() + " times.");
		
	}
	
	static void simulate(Quackable duck) {
		duck.quack();
	}
}
