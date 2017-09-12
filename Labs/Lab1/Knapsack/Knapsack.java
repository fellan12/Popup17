import java.io.*;
import java.util.*;

public class Knapsack {

	static boolean debug = true;

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		while(io.hasMoreTokens()){
			double capasity = io.getDouble();
			int n = io.getInt();
			//System.out.println("CAPASITY: " + capasity);
			//System.out.println("NUM: " + n);

			ArrayList<Item> items = new ArrayList<Item>();
			for (int i = 0; i < n; i++) {
				items.add(new Item(i, io.getInt(), io.getInt()));
			}

			//System.out.println(items);
			ArrayList<Integer> solution = knapsack(capasity, items);

			//Print no solution
			if (solution == null) {
				io.println("impossible");
				continue;
			}

			//Print number of indexes for the solution
			io.println(solution.size());

			//Print the indexes of the solution
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < solution.size(); i++) {
				sb.append(solution.get(i) + " ");
			}
			io.println(sb.toString().trim());
		}

		io.close();
	}

	public static ArrayList<Integer> knapsack(double capasity, ArrayList<Item> items){
		Collections.sort(items);
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int n = items.size();
		int matrix[][] = new int[n+1][(int)capasity+1];

		//System.out.println(items);

		//Calculate the best value we can get with the capasity
		for (int i = 0; i < matrix.length; i++) {         //0 -> Items.size()+1
			for (int j = 0; j < matrix[0].length; j++) {    //0 -> Sum(Items.get(i).getWeight())
				//Base case: zero weight or zero value
				if (i == 0 || j == 0){
					matrix[i][j] = 0;

				} else if (j - items.get(i-1).getWeight() >= 0) {
					matrix[i][j] = Math.max(items.get(i-1).getValue() + matrix[i-1][j-items.get(i-1).getWeight()],  matrix[i-1][j]);

				} else {
					matrix[i][j] = matrix[i-1][j];
				}
				printMatrix(matrix);

			}
		}

		//printMatrix(matrix);
		// System.out.println("MAX VALUE: =" + matrix[n][(int)capasity] );

		//Backtrack to get the items that gives the best value
		int j = matrix[0].length-1;
		for (int i = matrix.length-1; i >= 0; i--) {
			//If the current max value is not based on the row above max value
			//it's an item we take
			if(matrix[i][j] != matrix[i-1][j]){
				j -= items.get(i-1).getWeight();
				indexes.add(items.get(i-1).getIndex());
				// System.out.println("Added: " + items.get(i-1).getIndex());
			}

			//When we reach 0 value
			if(j == 0){
				break;
			}
		}
		return indexes;
	}

	/**
	 * Print a matrix
	 **/
	private static void printMatrix(int[][] matrix){
		if(debug){

			for(int i = 0; i < matrix.length; i++){
				for(int j = 0; j < matrix[0].length; j++){
					System.out.print(matrix[i][j] + " ");
				}
				System.out.println();
			}

			System.out.println("Press \"ENTER\" to continue...");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
