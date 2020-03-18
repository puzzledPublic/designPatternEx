package proxy.remote;

import java.rmi.RemoteException;
//서비스 구현 클래스
public class GumballMachine implements GumballMachineRemote {

	private State soldOutState;
	
	private State noQuarterState;
	
	private State hasQuarterState;
	
	private State soldState;
	
	private State winnerState;
	
	private State currentState;
	
	private int count;
	
	private String location;
	
	public GumballMachine(String location, int numberGumballs) {
		this.soldOutState = new SoldOutState(this);
		this.noQuarterState = new NoQuarterState(this);
		this.hasQuarterState = new HasQuarterState(this);
		this.soldState = new SoldState(this);
		this.winnerState = new WinnerState(this);
		this.currentState = this.soldOutState;
		this.count = numberGumballs;
		if(numberGumballs > 0) {
			this.currentState = this.noQuarterState;
		}
		this.location = location;
	}
	
	public void insertQuarter() {
		System.out.print("동전넣기 -- ");
		this.currentState.insertQuarter();
	}
	
	public void ejectQuarter() {
		System.out.print("동전반환 -- ");
		this.currentState.ejectQuarter();
	}
	
	public void turnCrank() {
		System.out.print("손잡이 돌리기 -- ");
		this.currentState.turnCrank();
		this.currentState.dispense();
	}
	
	void setState(State state) {
		this.currentState = state;
	}
	
	void releaseBall() {
		System.out.println("슬롯에서 알맹이가 나왔습니다.");
		if(this.count != 0) {
			this.count--;
		}
	}
	
	void refill(int count) {
		this.count = count;
		this.currentState = this.noQuarterState;
	}

	public State getHasQuarterState() {
		return this.hasQuarterState;
	}

	public State getNoQuarterState() {
		return this.noQuarterState;
	}

	public State getSoldState() {
		return this.soldState;
	}

	public State getSoldOutState() {
		return this.soldOutState;
	}
	
	public State getWinnerState() {
		return winnerState;
	}

	public int getCount() {
		return this.count;
	}
	
	@Override
	public String getLocation() throws RemoteException {
		return this.location;
	}
	
	@Override
	public String toString() {
		return "\n-------\n주식회사 왕뽑기\n자바로 돌아가는 2004년형 뽑기 기계\n남은 개수: " + this.getCount() + "개\n" + this.currentState.toString() +"\n-------\n";
	}

	@Override
	public State getState() throws RemoteException {
		return this.currentState;
	}
}
