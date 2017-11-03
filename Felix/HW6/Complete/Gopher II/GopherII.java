import java.util.*;
public class GopherII {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		while (io.hasMoreTokens()) {
			int numOfGophers = io.getInt();
			int numOfHoles = io.getInt();
			int timelimit = io.getInt();
			int veolicty = io.getInt();

			Point[] gophers = new Point[numOfGophers];
			for (int i = 0; i < gophers.length; i++) {
				gophers[i] = new Point(io.getDouble(), io.getDouble());
			}

			Point[] holes = new Point[numOfHoles];
			for (int i = 0; i < holes.length; i++) {
				holes[i] = new Point(io.getDouble(), io.getDouble());
			}

			int survivors = calculateSurvivors(numOfGophers, numOfHoles, veolicty, timelimit, gophers, holes);

  		io.println(numOfGophers-survivors);

		}
		io.close();
	}

	public static int calculateSurvivors(int numOfGophers, int numOfHoles, int veolicty, int timelimit, Point[] gophers, Point[] holes){
		int nodes = numOfGophers+numOfHoles+2;
		int source = numOfGophers+numOfHoles;
		int tap = numOfGophers+numOfHoles+1;
		int[][] graph = new int[nodes][nodes];
		for (int i = 0; i < gophers.length; i++) {
			graph[source][i] = 1;
			for (int j = 0; j < holes.length; j++) {
				if (gophers[i].getDistanceBetween(holes[j])/veolicty < timelimit) {
					graph[i][numOfGophers+j] = 1;
				}
			}
		}

		for (int i = 0; i < holes.length; i++) {
			graph[numOfGophers+i][tap] = 1;
		}

		return EdmundKarp.maxFlow(graph, source, tap).getMaxFlow();
	}
}
