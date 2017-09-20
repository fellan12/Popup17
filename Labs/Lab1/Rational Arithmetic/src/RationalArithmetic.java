/**
 * Author: Felix De Silva
 */

public class RationalArithmetic {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		long numOfOperations = io.getLong();
		for (long i = 0; i < numOfOperations; i++) {
			Rational first = new Rational(io.getLong(), io.getLong());
			String operation = io.getWord();
			Rational second = new Rational(io.getLong(), io.getLong());

			switch(operation){
				case "+": 
					io.println(first.add(second).toString());
					break;
				case "-": 
					io.println(first.sub(second).toString());
					break;
				case "/": 
					io.println(first.div(second).toString());
					break;
				case "*": 
					io.println(first.mul(second).toString());
					break;
				default:  
					throw new RuntimeException("Didn't find a matching for operation '"+operation+"'");
			}
		}
		io.close();
	}
}
