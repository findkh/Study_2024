package command.remoteControl;

public class HottubOnCommand implements Command {
	Hottub hottub;

	public HottubOnCommand(Hottub hottub) {
		this.hottub = hottub;
	}
	
	@Override
	public void execute() {
		hottub.on();
		hottub.setTemperature(40);
		hottub.circulate();
	}
	
	@Override
	public void undo() {
		hottub.off();
	}
}
