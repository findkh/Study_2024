package hometheater;

public class Screen {
	
	String description;
	
	public Screen(String description) {
		this.description = description;
	}

	public void down() {
		System.out.println(description + "을 내립니다.");
		
	}

	public void up() {
		System.out.println(description + "을 올립니다.");
		
	}
	
	public String toString() {
		return description;
	}

}
