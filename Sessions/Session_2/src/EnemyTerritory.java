import java.util.ArrayDeque;

public class EnemyTerritory {

	private static final int FREE = 0;
	private static final int START = 1;
	private static final int GOAL = 2;
	private static final int ENEMY = 3;

	private static Point solve(int[][] map, Point[] enemies, Point start, Point goal) {
		int seperation = 0;
		int routeLength = 0;
		while (true) {
			int route = bfs(map, start, goal);
			if (route == -1)
				break;
			seperation++;
			routeLength = route;
			map = expandEnemy(map, enemies, seperation);
			if (map == null)
				break;
		}
		return new Point(seperation, routeLength);
	}
	
	private static void print(int[][] map) {
		System.out.println("-------------------");

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[j][i]+ " ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
	
	private static int bfs(int[][] map, Point start, Point goal) {
		start.steps = 0;
		ArrayDeque<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[map.length][map[0].length];
		q.push(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point current = q.poll();
			if (current.equals(goal))
				return current.steps;
			int x = current.x;
			int y = current.y;
			if (x - 1 >= 0 && !visited[x-1][y] && map[x-1][y] != ENEMY) {
				Point next = new Point(x-1, y);
				next.steps = current.steps + 1;
				q.push(next);
				visited[x-1][y] = true;
			} if (x + 1 < map.length && !visited[x+1][y] && map[x+1][y] != ENEMY) {
				Point next = new Point(x+1, y);
				next.steps = current.steps + 1;
				q.push(next);
				visited[x+1][y] = true;
			} if (y - 1 >= 0 && !visited[x][y-1] && map[x][y-1] != ENEMY) {
				Point next = new Point(x, y-1);
				next.steps = current.steps + 1;
				q.push(next);
				visited[x][y-1] = true;
			} if (y + 1 < map[0].length && !visited[x][y+1] && map[x][y+1] != ENEMY) {
				Point next = new Point(x, y+1);
				next.steps = current.steps + 1;
				q.push(next);
				visited[x][y+1] = true;
			}
		}
		return -1;
	}

	private static int[][] expandEnemy(int[][] map, Point[] enemies, int seperation) {
		
		for (Point enemy : enemies) {
			int x = enemy.x-seperation;
			
			for (; x < enemy.x+seperation; x++) {
				if (x < 0 || x >= map.length)
					continue;
				if (x == enemy.x - seperation || x == enemy.x + seperation) {
					for (int y = enemy.y-seperation; y <= enemy.y+seperation; y++) {
						if (y < 0 || y >= map[0].length)
							continue;
						if (map[x][y] == START || map[x][y] == GOAL)
							return null;
						map[x][y] = ENEMY;
					}
				} else {
					if (enemy.y-seperation >= 0) {
						if (map[x][enemy.y-seperation] == START || map[x][enemy.y-seperation] == GOAL)
							return null;
						map[x][enemy.y-seperation] = ENEMY;
					}
					if (enemy.y+seperation < map[0].length) {
						if (map[x][enemy.y+seperation] == START || map[x][enemy.y+seperation] == GOAL)
							return null;
						map[x][enemy.y+seperation] = ENEMY;
					}
				}
			}
			
		}
		return map;
	}

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int bases = io.getInt();
		int[][] map = new int[io.getInt()][io.getInt()];
		Point start = new Point(io.getInt(), io.getInt());
		map[start.x][start.y] = START;
		Point goal = new Point(io.getInt(), io.getInt());
		map[goal.x][goal.y] = GOAL; 
		Point[] enemies = new Point[bases];
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Point(io.getInt(), io.getInt());
			map[enemies[i].x][enemies[i].y] = ENEMY;
		}
		Point results = solve(map, enemies, start, goal);
		io.println(results.x+" "+results.y);
		io.close();
	}

	private static int distance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	private static class Point {
		int x, y;
		int steps;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.steps = 0;
		}
		
		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			return (this.x == other.x && this.y == other.y);
		}
	}
}
