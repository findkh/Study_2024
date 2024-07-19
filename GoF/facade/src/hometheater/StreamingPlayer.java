package hometheater;

public class StreamingPlayer {
	
	String description;
	
	public StreamingPlayer(String description, Amplifier amp) {
		this.description = description;
	}

	public void on() {
		System.out.println(description + "을 켭니다.");
		
	}

	public void play(String movie) {
		System.out.println(description + movie +"을/를 플레이합니다.");
		
	}

	public void stop() {
		System.out.println(description + "을 멈춥니다.");
		
	}

	public void off() {
		System.out.println(description + "을 끕니다.");
		
	}
	
	public String toString() {
		return description;
	}

}
