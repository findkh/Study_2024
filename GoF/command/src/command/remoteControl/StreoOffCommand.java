package command.remoteControl;

public class StreoOffCommand implements Command {
	Stereo stereo;
 
	public StreoOffCommand(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		stereo.off();
	}

	public void undo() {
		stereo.on();
	}
}
