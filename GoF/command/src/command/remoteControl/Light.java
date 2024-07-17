package command.remoteControl;

public class Light {
	String location = "";
	
	public Light(String location) {
		this.location = location;
	}
	
	public void on() {
		System.out.println(location + "에 불이 켜졌습니다.");
	}
	
	public void off() {
		System.out.println(location + "에 불이 꺼졌습니다.");
	}
}
