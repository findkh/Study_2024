package hometheater;

public class Amplifier {
	
	String description;
	Tuner tuner;
	StreamingPlayer player;
	
	public Amplifier(String description) {
		this.description = description;
	}
	
	
	public void on() {
		System.out.println(description + "전원 켭니다.");
	}
	
	public void off() {
		System.out.println(description + "전원을 끕니다.");
	}
	
	public void setStereoSound() {
		System.out.println(description + "스트레오 모드를 켭니다.");
	}
	
	public void setSurroundSound() {
		System.out.println(description + "스트레오 모드를 켭니다.(5 speakers, 1 subwoofer)");
	}
	
	public void setVolume(int level) {
		System.out.println(description + "의 볼륨을 "+ level + "로 조절합니다.");
	}
	
	public void setTuner(Tuner tuner) {
		System.out.println(description + " setting tuner to " + player);
		this.tuner = tuner;
	}
	
	public void setStreamingPlayer(StreamingPlayer player) {
		System.out.println(description + " 스트리밍 모드로 켭니다." + player);
		this.player = player;
	}
	
	public String toString() {
		return description;
	}
}
