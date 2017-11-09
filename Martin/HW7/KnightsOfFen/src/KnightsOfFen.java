import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Scanner;

public class KnightsOfFen {

	/**
	kof (board):
		int minMoves = minimumMovesToGoal(board)
		if minMoves > 10:
			return -1
		while not findway(board, minMoves):
			minMoves++
			if minMoves > 10
				return -1
		return minMoves
	 */
	private static int solve(Board b) {
		int minMoves = Board.minimumMovesToGoal(b);
		if (minMoves > 10)
			return -1;
		while (!findWay(b, minMoves)) {
			minMoves++;
			if (minMoves > 10)
				return -1;
		}
		return minMoves;
	}

	/**
	findway (board, moves):
		queue <- [board]
		while queue not empty:
			current <- remove first from queue
			if current equals goal
				return true
			if minimumMovesToGoal(current) + depth(current) <= moves:
				add all neighbors to queue
		return false
	 */
	public static boolean findWay(Board start, int moves) {
		ArrayDeque<DepthBoard> togo = new ArrayDeque<>();
		togo.add(new DepthBoard(start, 0));
		while (!togo.isEmpty()) {
			DepthBoard b = togo.remove();
			if (Board.equalsGoal(b))
				return true;
			if (Board.minimumMovesToGoal(b) + b.getDepth() <= moves) {
				for (Board neighbor : Board.getNeighbors(b)) {
					togo.add(new DepthBoard(neighbor, b.getDepth()+1));
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		for (Board b : InputParser.getCases()) {
			int solution = solve(b);
			String s;
			if (solution == -1) {
				s = "Unsolvable in less than 11 move(s).";
			} else {
				s = "Solvable in " + solution  + " move(s).";
			}
			System.out.println(s);
		}
		System.out.close();
	}

	private static class InputParser {
		
		private static Scanner in = new Scanner(System.in);
		
		public static Board[] getCases() {
			Board[] boards = new Board[Integer.parseInt(in.nextLine())];
			for (int i = 0; i < boards.length; i++) {
				Square[][] b = new Square[5][5];
				Point free = null;
				for (int j = 0; j < b.length; j++) {
					String[] line = in.nextLine().split("");
					for (int j2 = 0; j2 < b.length; j2++) {
						Square s = nextSquare(line[j2]);
						b[j][j2] = s;
						if (s.equals(Square.FREE))
							free = new Point(j,  j2);
					}
				}
				boards[i] = new Board(b, free);
			}
			return boards;
		}

		private static Square nextSquare(String s) {
			switch (s) {
			case "0":
				return Square.WHITE;
			case "1":
				return Square.BLACK;
			case " ": 
				return Square.FREE;
			default:
				throw new RuntimeException();
			}
		}
	}

	private static class DepthBoard extends Board {

		private final int depth;

		public DepthBoard(Board b, int depth) {
			super(b.getBoard(), b.getFree());
			this.depth = depth;
		}

		public int getDepth() {
			return depth;
		}

	}

}
