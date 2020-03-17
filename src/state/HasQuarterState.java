package state;

import java.util.Random;

public class HasQuarterState implements State {

	private Random randomWinner = new Random(System.currentTimeMillis());
	
	private GumballMachine gumballMachine;
	
	public HasQuarterState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	@Override
	public void insertQuarter() {
		System.out.println("동전은 한 개만 넣어주세요.");
	}

	@Override
	public void ejectQuarter() {
		System.out.println("동전이 반환됩니다.");
		this.gumballMachine.setState(this.gumballMachine.getNoQuarterState());
	}

	@Override
	public void turnCrank() {
		System.out.println("손잡이를 돌리셨습니다.");
		if(isWinner()) {
			this.gumballMachine.setState(this.gumballMachine.getWinnerState());
		}else {
			this.gumballMachine.setState(this.gumballMachine.getSoldState());
		}
	}

	@Override
	public void dispense() {
		System.out.println("알맹이가 나갈 수 없습니다.");
	}

	@Override
	public String toString() {
		return "뽑기 대기 중";
	}
	
	private boolean isWinner() {
		int winner = this.randomWinner.nextInt(10);
		return (winner == 0) && (this.gumballMachine.getCount() > 1);
	}
}
