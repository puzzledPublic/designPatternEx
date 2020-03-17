package state;

public class WinnerState implements State {

	private GumballMachine gumballMachine;
	
	public WinnerState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	@Override
	public void insertQuarter() {
		System.out.println("잠깐만 기다려 주세요. 알맹이가 나가고 있습니다.");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("이미 알맹이를 뽑으셨습니다.");
	}

	@Override
	public void turnCrank() {
		System.out.println("손잡이는 한 번만 돌려주세요.");
	}

	@Override
	public void dispense() {
		System.out.println("축하드립니다! 알맹이를 하나 더 받으실 수 있습니다.");
		this.gumballMachine.releaseBall();
		if(this.gumballMachine.getCount() == 0) {
			this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
		}else {
			this.gumballMachine.releaseBall();
			if(this.gumballMachine.getCount() > 0) {
				this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
			}else {
				System.out.println("더 이상 알맹이가 없습니다.");
				this.gumballMachine.setState(this.gumballMachine.getSoldOutState());
			}
		}
	}

}
