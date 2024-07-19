package hometheater;

public class PopcornPopper {
	
	String description;
	
	public PopcornPopper(String description) {
		this.description = description;
	}

	public void on() {
		System.out.println(description + "가 켜졌습니다.");
	}
	
	public void off() {
		System.out.println(description + "가 꺼졌습니다.");
	}
	
	public void pop() {
		System.out.println(description + "가 팝콘을 튀깁니다.");
	}
	
	public String toString() {
		return description;
	}
}
