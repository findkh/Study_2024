package stateGumball;

public class SoldOutState implements State {
	
	GumballMachine gumballMachine;
	
	public SoldOutState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	@Override
	public void insertQuarter() {
		System.out.println("동전을 넣을 수 없습니다. 매진되었습니다.");
	}
	
	@Override
	public void ejectQuarter() {
		System.out.println("동전을 반환할 수 없습니다. 동전을 넣지 않았습니다.");
	}
	
	@Override
	public void turnCrank() {
		System.out.println("죄송합니다. 매진되었습니다.");
	}
	
	@Override
	public void dispense() {
		System.out.println("알맹이를 내보낼 수 없습니다.");
	}
	
	@Override
	public void refill() {
		gumballMachine.setState(gumballMachine.getNoQuarterState());
	}
	
	public String toString() {
		return "매진";
	}

}
