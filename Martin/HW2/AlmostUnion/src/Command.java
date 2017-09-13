
public class Command {
	private CommandType type;
	private int p;
	private int q;
	
	public Command(int typeValue) {
		switch (typeValue) {
		case 1:
			this.type = CommandType.UNION;
			break;
		case 2:
			this.type = CommandType.MOVE;
			break;
		case 3:
			this.type = CommandType.RETURN;
			break;
		default:
			break;
		}
	}
	
	public Command(CommandType type) {
		this.type = type;
	}
	
	public void setP(int p) {
		this.p = p;
	}
	
	public void setQ(int q) {
		this.q = q;
	}
	
	public int getP() {
		return p;
	}
	
	public int getQ() {
		return q;
	}
	
	public CommandType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		String s = type + " " + p;
		if (type != CommandType.RETURN)
			s += " " + q;
		return s;
	}
}
