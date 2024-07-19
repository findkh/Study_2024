package hometheater;

public class Projector {
	
	String description;
	
	public Projector(String description, StreamingPlayer player) {
		this.description = description;
	}

	public void on() {
		System.out.println(description +"을 켭니다.");
	}

	public void off() {
		System.out.println(description +"을 끕니다.");
	}
	
	public String toString() {
		return description;
	}

	public void wideScreenMode() {
		System.out.println(description + "화면 비율을 와이드 모드로 설정합니다.");
		
	}

}
