package command.remoteControl;

public class CeilingFanOnCommand implements Command {
	CeilingFan ceilingFan;

	public CeilingFanOnCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	
	@Override
	public void execute() {
		ceilingFan.high();
	}
	
	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
}