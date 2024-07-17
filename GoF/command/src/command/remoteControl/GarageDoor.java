package command.remoteControl;

public class GarageDoor {
	String location = "";
	
	public GarageDoor(String location){
		this.location = location;
	}
	
	public void open() {
		System.out.println(location + "문이 열렸습니다.");
	}
	
	public void close() {
		System.out.println(location + "문이 닫혔습니다.");
	}
}
