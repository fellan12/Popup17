public class Gopher {

	private static int gopherSolver(int n, int m, int s, int v, DPoint[] gophers, DPoint[] holes) {
		int nodes = n+m+2;
		int gopherIndex = 0;
		int holesIndex = n;
		int source = holesIndex+m;
		int tap = source+1;
		int[][] graph = new int[nodes][nodes];
		for (int i = 0; i < gophers.length; i++) {
			graph[source][gopherIndex+i] = 1;
			for (int j = 0; j < holes.length; j++) {
				double dist = gophers[i].distance(holes[j]);
				if (s*v >= dist) {
					graph[gopherIndex+i][holesIndex+j] = 1;
				}
			}
		}
		for (int i = 0; i < holes.length; i++) {
			graph[holesIndex+i][tap] = 1;
		}
		Result res = EdmundKarp.maxFlow(graph, source, tap);
		return n-res.getMaxFlow();
	}

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		while (io.hasMoreTokens()) {
			int n = io.getInt();
			int m = io.getInt();
			int s = io.getInt();
			int v = io.getInt();
			DPoint[] gophers = new DPoint[n];
			DPoint[] holes = new DPoint[m];
			for (int i = 0; i < gophers.length; i++) {
				gophers[i] = new DPoint(io.getDouble(), io.getDouble());
			}
			for (int i = 0; i < holes.length; i++) {
				holes[i] = new DPoint(io.getDouble(), io.getDouble());
			}
			io.println(gopherSolver(n, m, s, v, gophers, holes));
		}
		io.close();
	}

	private static class DPoint {
		double x, y;

		public DPoint(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double distance(DPoint other) {
			return Math.sqrt(Math.pow(Math.abs(this.x - other.x),2) + Math.pow(Math.abs(this.y - other.y),2));
		}
	}

}
