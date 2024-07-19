package hometheater;

public class TheaterLights {
	
	String description;
	
	public TheaterLights(String description) {
		this.description = description;
	}
	
	public void on() {
		System.out.println(description + "를 켭니다.");
	}
	
	public void off() {
		System.out.println(description + "를 끕니다.");
	}
	
	public void dim(int i) {
		System.out.println(description + "밝기를 " + i + "%로 조절합니다.");
		
	}
	
	public String toString() {
		return description;
	}

}
