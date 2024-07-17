package command.remoteControl;

public class RemoteLoader {
	public static void main(String[] args) {
		RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
		
//		Light livingRoomLight = new Light("거실");
//		Light kitchenLight = new Light("부엌");
//		CeilingFan ceilingFan = new CeilingFan("거실");
//		GarageDoor garageDoor = new GarageDoor("차고");
//		Stereo streo = new Stereo("거실");
		
		
		//커맨드 객체 생성
//		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
//		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
//		LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
//		LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
//		CeilingFanOnCommand ceilingFanOn = new CeilingFanOnCommand(ceilingFan);
//		CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
//		CeilingFanLowCommand ceilingFanLow = new CeilingFanLowCommand(ceilingFan);
//		CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
//		CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);
//		GarageDoorOpenCommand grageDoorOpen = new GarageDoorOpenCommand(garageDoor);
//		GarageDoorCloseCommand garageDoorClose = new GarageDoorCloseCommand(garageDoor);
//		StereoOnWithCDCommand streoOnWithCd = new StereoOnWithCDCommand(streo);
//		
		//리모컨 슬롯에 커맨드를 로드
//		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
//		remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
//		remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
//		remoteControl.setCommand(3, grageDoorOpen, garageDoorClose);
//		remoteControl.setCommand(4, streoOnWithCd, streoOff);
		
//		System.out.println(remoteControl);
//		
//		remoteControl.onButtonWasPushed(0);
//		remoteControl.offButtonWasPushed(0);
//		System.out.println(remoteControl);
//		remoteControl.undoButtonWasPushed();
//		remoteControl.offButtonWasPushed(0);
//		remoteControl.onButtonWasPushed(0);
//		System.out.println(remoteControl);
//		remoteControl.undoButtonWasPushed();
//		remoteControl.onButtonWasPushed(1);
//		remoteControl.offButtonWasPushed(1);
//		remoteControl.onButtonWasPushed(2);
//		remoteControl.offButtonWasPushed(2);
//		remoteControl.onButtonWasPushed(3);
//		remoteControl.offButtonWasPushed(3);
		
//		remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff);
//		remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
//		
//		remoteControl.onButtonWasPushed(0);
//		remoteControl.offButtonWasPushed(0);
//		System.out.println(remoteControl);
//		remoteControl.undoButtonWasPushed(); //Medium으로 돌아가야함
//		
//		remoteControl.onButtonWasPushed(1);
//		System.out.println(remoteControl);
//		remoteControl.undoButtonWasPushed(); //Medium으로 돌아가야함
		
		//매크로 커맨드
		Light light = new Light("Living Room");
		TV tv = new TV("Living Room");
		Stereo stereo = new Stereo("Living Room");
		Hottub hottub = new Hottub();
 
		LightOnCommand lightOn = new LightOnCommand(light);
		StereoOnCommand stereoOn = new StereoOnCommand(stereo);
		TVOnCommand tvOn = new TVOnCommand(tv);
		HottubOnCommand hottubOn = new HottubOnCommand(hottub);
		LightOffCommand lightOff = new LightOffCommand(light);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		TVOffCommand tvOff = new TVOffCommand(tv);
		HottubOffCommand hottubOff = new HottubOffCommand(hottub);
		
		Command[] partyOn = { lightOn, stereoOn, tvOn, hottubOn};
		Command[] partyOff = { lightOff, stereoOff, tvOff, hottubOff};
		
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
		
		remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
		
		System.out.println(remoteControl);
		System.out.println("--- Macro On ---");
		remoteControl.onButtonWasPushed(0);
		System.out.println("--- Macro Off ---");
		remoteControl.offButtonWasPushed(0);
		
	}
}
