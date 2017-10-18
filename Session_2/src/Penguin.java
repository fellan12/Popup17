import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Penguin {
	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int n = io.getInt();
		double jumpDistance = io.getDouble();
		int totalPenguins = 0;
		IceFloe[] floes = new IceFloe[n];
		for (int i = 0; i < floes.length; i++) {
			Point location = new Point(io.getInt(), io.getInt());
			int penguins = io.getInt();
			totalPenguins += penguins;
			int durability = io.getInt();
			floes[i] = new IceFloe(location, penguins, durability);
		}
		int[][] graph = new int[2*n+1][2*n+1];
		int start = 2*n;
		for (int i = 0; i < floes.length; i++) {
			if (floes[i].penguins > 0) {
				graph[start][2*i+1] = floes[i].penguins;
			}
			for (int j = 0; j < floes.length; j++) {
				double distance = Math.sqrt((floes[i].x-floes[j].x)*(floes[i].x-floes[j].x) + (floes[i].y-floes[j].y)*(floes[i].y-floes[j].y));
				if (distance <= jumpDistance+0.009) {
					if (i != j) {
						graph[2*i][2*j+1] = floes[i].durability;
						graph[2*j][2*i+1] = floes[j].durability;
					} else {
						graph[2*i+1][2*j] = floes[i].durability;
					}
				}
			}
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < floes.length; i++) {
			int res = EdmundKarp.maxFlow(graph, start, 2*i+1).getMaxFlow();
			if (res == totalPenguins)
				result.add(i);
		}
		if (result.isEmpty()) {
			io.println("-1");
		} else {
			for (int r : result) {
				io.print(r+" ");
			}
		}
		io.close();
	}

	private static class IceFloe {
		int x;
		int y;
		int penguins;
		int durability;

		public IceFloe(Point location, int penguins, int durability) {
			this.x = location.x;
			this.y = location.y;
			this.penguins = penguins;
			this.durability = durability;
		}
	}
}
