/**
 * Authors: Martin Engelin & Felix De Silva
 */
public enum CommandType {
	UNION, PRINT;
	
	public static CommandType typeFromString(String s) {
		if (s.equals("=")) {
			return CommandType.UNION;
		} else if (s.equals("?")) {
			return CommandType.PRINT;
		} else {
			return null;
		}
	}
}
