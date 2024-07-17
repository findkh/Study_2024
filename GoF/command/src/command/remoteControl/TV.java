package command.remoteControl;

public class TV {
	String location;
	int channel;
	
	public TV(String location) {
		this.location = location;
	}

	public void on() {
		System.out.println(location + "에 TV가 켜졌습니다.");
	}

	public void off() {
		System.out.println(location + "에 TV가 꺼졌습니다. ");
	}
	
	public void setInputChannel() {
		this.channel = 3;
		System.out.println(location + "에 TV를 맞춥니다.");
	}
}
