package command.remoteControl;

//NoCommand 객체는 Null객체이다. 
//리턴할 객체도 없고 클라이언트가 null처리를 하지 않게 하고 싶을 때 활용한다.
public class NoCommand implements Command {
	@Override
	public void execute() { }
	
	@Override
	public void undo() { }
}