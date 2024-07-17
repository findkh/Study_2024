package command.remoteControl;

public class CeilingFan {
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	String location;
	int speed;
	
	public CeilingFan(String location) {
		this.location = location;
		speed = OFF;
	}
	
	public void high() {
		speed = HIGH;
		System.out.println(location + "에 실링팬의 속도가 HIGH로 설정되었습니다.");
	}
	
	public void medium() {
		speed = MEDIUM;
		System.out.println(location + "에 실링팬의 속도가 MEDIUM로 설정되었습니다.");
	}
	
	public void low() {
		speed = LOW;
		System.out.println(location + "에 실링팬의 속도가 LOW로 설정되었습니다.");
	}
	
	public void off() {
		System.out.println(location + "에 실링팬을 끕니다.");
	}
	
	public int getSpeed() {
		return speed;
	}
}
