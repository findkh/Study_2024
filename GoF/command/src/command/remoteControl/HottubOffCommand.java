package command.remoteControl;

public class HottubOffCommand implements Command {
	Hottub hottub;

	public HottubOffCommand(Hottub hottub) {
		this.hottub = hottub;
	}
	
	@Override
	public void execute() {
		hottub.setTemperature(36);
		hottub.off();
	}
	
	@Override
	public void undo() {
		hottub.on();
	}
}
