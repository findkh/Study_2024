package gumballmachine;

public class WinnerState implements State {
	GumballMachine gumballMachine;
	
	public WinnerState(GumballMachine gumballMachine) {
		this.gumballMachine = gumballMachine;
	}
	
	public void insertQuarter() {
		System.out.println("잠시만 기다려주세요, 이미 알맹이을 드리고 있습니다.");
	}
 
	public void ejectQuarter() {
		System.out.println("잠시만 기다려주세요, 이미 알맹이을 드리고 있습니다.");
	}
 
	public void turnCrank() {
		System.out.println("알맹이가 없습니다.");
	}
 
	public void dispense() {
		gumballMachine.releaseBall();
		if (gumballMachine.getCount() == 0) {
			gumballMachine.setState(gumballMachine.getSoldOutState());
		} else {
			gumballMachine.releaseBall();
			System.out.println("축하합니다. 알맹이를 하나 더 받으실 수 있습니다.");
			if (gumballMachine.getCount() > 0) {
				gumballMachine.setState(gumballMachine.getNoQuarterState());
			} else {
				System.out.println("매진되었습니다.");
				gumballMachine.setState(gumballMachine.getSoldOutState());
			}
		}
	}
	
	public void refill() { }
	
	public String toString() {
		return "축하합니다. 알맹이를 하나 더 받으실 수 있습니다.";
	}

}
