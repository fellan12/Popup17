import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
	
	private static final int BOARD_SIZE = 5;
	private static final Square[][] GOAL = new Square[][] {
		{Square.BLACK, Square.BLACK, Square.BLACK, Square.BLACK, Square.BLACK},
		{Square.WHITE, Square.BLACK, Square.BLACK, Square.BLACK, Square.BLACK},
		{Square.WHITE, Square.WHITE, Square.FREE, Square.BLACK, Square.BLACK},
		{Square.WHITE, Square.WHITE, Square.WHITE, Square.WHITE, Square.BLACK},
		{Square.WHITE, Square.WHITE, Square.WHITE, Square.WHITE, Square.WHITE}
	};
	
	private final Point free;
	private final Square[][] board;
	
	public Board(Square[][] board, Point free) {
		this.board = board;
		this.free = free;
	}
	
	public Square get(int x, int y) {
		return board[x][y];
	}
	
	public Square[][] getBoard() {
		return board;
	}
	
	public Point getFree() {
		return free;
	}
	
	public static boolean equalsGoal(Board b) {
		for (int i = 0; i < GOAL.length; i++) {
			if (!Arrays.equals(GOAL[i], b.board[i]))
				return false;
		}
		return true;
	}
	
	/**
	 minimumMovesToGoal(board):
	 	res <- 0
	 	for all squares s in board
	 		if s != goal(s)
	 			res += 1
	 			if specialcase
	 			 res += 1
	 	return res
	 */
	public static int minimumMovesToGoal(Board board) {
		int res = 0;
		Point[] specialCases = new Point[] {
				new Point(0,4),
				new Point(4,0),
				new Point(0,3),
				new Point(1,4),
				new Point(3,0),
				new Point(4,1)
		};
		for (int i = 0; i < specialCases.length; i++) {
			Point sc = specialCases[i];
			if (board.get(sc.x, sc.y) != GOAL[sc.x][sc.y]) { 
				res++;
				if (i > 1 && board.get(sc.x, sc.y) == Square.FREE) {
					res--;
				}
			}
		}
		for (int x = 0; x < GOAL.length; x++) {
			for (int y = 0; y < GOAL[0].length; y++) {
				if (board.get(x, y) != GOAL[x][y])
					res++;
			}
		}
		return res == 0 ? 0 : res-1;
	}
	
	private static Board moveHorse(final Board board, final Point horse) {
		Square[][] oldBoard = board.board;
		Square[][] newBoard = deepCopy(oldBoard);
		newBoard[board.free.x][board.free.y] = newBoard[horse.x][horse.y];
		newBoard[horse.x][horse.y] = Square.FREE;
		return new Board(newBoard, horse);
	}
	
	private static Square[][] deepCopy(Square[][] squareMatrix) {
		Square[][] res = new Square[squareMatrix.length][squareMatrix[0].length];
		for (int i = 0; i < res.length; i++) {
			res[i] = Arrays.copyOf(squareMatrix[i], squareMatrix[i].length);
		}
		return res;
	}
	
	private static List<Point> getPossibleMoves(final Board board) {
		List<Point> points = new ArrayList<>();
		Point f = board.free;
		Point[] all = new Point[] {
				new Point(f.x+2, f.y+1),
				new Point(f.x+2, f.y-1),
				new Point(f.x-2, f.y+1),
				new Point(f.x-2, f.y-1),
				new Point(f.x+1, f.y+2),
				new Point(f.x-1, f.y+2),
				new Point(f.x+1, f.y-2),
				new Point(f.x-1, f.y-2)
		};
		for (Point p : all) {
			if ((p.x >= BOARD_SIZE || p.x < 0) || ((p.y >= BOARD_SIZE || p.y < 0)))
				continue;
			points.add(p);
		}
		return points;
	}
	
	public static List<Board> getNeighbors(final Board board) {
		List<Board> neighbors = new ArrayList<>();
		List<Point> moveableHorses = Board.getPossibleMoves(board);
		for (Point horse : moveableHorses) {
			neighbors.add(moveHorse(board, horse));
		}
		return neighbors;
	}
}
