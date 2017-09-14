
public class Command {
	private CommandType type;
	private int a;
	private int b;
	
	public Command(CommandType type, int a, int b) {
		this.type = type;
		this.a = a;
		this.b = b;
	}
	
	public Command(String typeString, int a, int b) {
		this.type = CommandType.typeFromString(typeString);
		this.a = a;
		this.b = b;
	}
		
	public int getA() {
		return a;
	}
	
	public int getB() {
		return b;
	}
	
	public CommandType getType() {
		return type;
	}
}
