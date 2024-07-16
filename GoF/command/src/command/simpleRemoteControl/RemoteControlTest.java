package command.simpleRemoteControl;

public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();
		
		Light light = new Light();
		GarageDoor garageDoor = new GarageDoor();
		
		LightOnCommand lightOn = new LightOnCommand(light);
		LightOffCommand lightOff = new LightOffCommand(light);
		
		GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);
		GarageDoorCloseCommand garageClose = new GarageDoorCloseCommand(garageDoor);
		
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		remote.setCommand(lightOff);
		remote.buttonWasPressed();
		
		remote.setCommand(garageOpen);
		remote.buttonWasPressed();
		remote.setCommand(garageClose);
		remote.buttonWasPressed();
	}
}
