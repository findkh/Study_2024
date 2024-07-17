package command.remoteControl;

public class Stereo {
	String location = "";
	
	public Stereo(String location) {
		this.location = location;
	}
	
	public void on() {
		System.out.println(location + "에 스트레오가 켜졌습니다.");
	}
	
	public void off() {
		System.out.println(location + "에 스트레오가 꺼졌습니다.");
	}
	
	public void setCd() {
		System.out.println("CD가 재생됩니다.");
	}
	
	public void setDvd() {
		System.out.println("DVD가 재생됩니다.");
	}
	
	public void setRadio() {
		System.out.println("라디오가 재생됩니다.");
	}
	
	public void setVolumn(int volume) {
		System.out.println("볼륨을 "+ volume +"으로 조절합니다.");
	}
}
