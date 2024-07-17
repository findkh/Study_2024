package command.remoteControl;

public interface Command {
	public void execute();
	
	public void undo();
}
