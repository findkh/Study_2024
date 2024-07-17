package command.remoteControl;

public class Hottub {
	boolean on;
	int temperature;

	public Hottub() {
	}

	public void on() {
		on = true;
	}

	public void off() {
		on = false;
	}

	public void circulate() {
		if (on) {
			System.out.println("욕조 순환 모드");
		}
	}

	public void jetsOn() {
		if (on) {
			System.out.println("욕조 제트 모드");
		}
	}

	public void jetsOff() {
		if (on) {
			System.out.println("욕조가 꺼졌습니다.");
		}
	}

	public void setTemperature(int temperature) {
		if (temperature > this.temperature) {
			System.out.println("욕조물의 온도를 " + temperature + " 도로 올렸습니다.");
		}
		else {
			System.out.println("욕조물의 온도를 " + temperature + " 도로 낮췄습니다.");
		}
		this.temperature = temperature;
	}
}
