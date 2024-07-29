package stateGumball;

public class GumballMachine {
	
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	State winnerState;
	
	State state;
	int count = 0;
	
	public GumballMachine(int numberGumballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		winnerState = new WinnerState(this);
		
		this.count = numberGumballs;
		
		if(numberGumballs > 0) {
			state = noQuarterState;
		} else {
			state = soldOutState;
		}
	}
	
	public void insertQuarter() {
		state.insertQuarter();
	}
	
	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		System.out.println(state);
		state.dispense();
	}
	
	void setState(State state) {
		this.state = state;
	}
	
	void releaseBall() {
		System.out.println("알맹이를 내보내고 있습니다.");
		if(count > 0) {
			count = count -1;
		}
	}
	
	int getCount() {
		return count;
	}
 
	void refill(int count) {
		this.count += count;
		System.out.println("The gumball machine was just refilled; its new count is: " + this.count);
		state.refill();
	}

	public State getState() {
		return state;
	}

	public State getSoldOutState() {
		return soldOutState;
	}
	
	public State getNoQuarterState() {
		return noQuarterState;
	}
	
	public State getHasQuarterState() {
		return hasQuarterState;
	}
	
	public State getSoldState() {
		return soldState;
	}
	
	public State getWinnerState() {
		return winnerState;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append("\n주식회사 왕뽑기");
		result.append("\n자바로 돌아가는 최신형 뽑기 기계\n");
		result.append("남은 개수: " + count + " 개 \n");
		result.append(state + "\n");
		
		return result.toString();
	}
	
}
