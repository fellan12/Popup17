public class GopherII {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);
		while (io.hasMoreTokens()) {
			int numOfGophers = io.getInt();
			int numOfHoles = io.getInt();
			int timelimit = io.getInt();
			int veolicty = io.getInt();

			int index = 0;
			Point[] gophers = new Point[numOfGophers];
			for (int i = 0; i < gophers.length; i++) {
				gophers[i] = new Point(index, io.getDouble(), io.getDouble());
				index++;
			}

			Point[] holes = new Point[numOfHoles];
			for (int i = 0; i < holes.length; i++) {
				holes[i] = new Point(index, io.getDouble(), io.getDouble());
				index++;
			}

  		int[][] graph = new int[numOfGophers+numOfHoles+2][numOfGophers+numOfHoles+2];
  		for (int i = 0; i < gophers.length; i++) {
  			for (int j = 0; j < holes.length; j++) {
  				if (gophers[i].getDistanceBetween(holes[j]) / veolicty < timelimit) {
  					graph[gophers[i].getIndex()][holes[j].getIndex()] = 1;
  				}
  			}
  		}

			int source = index++;
  		int tap = index;
			for (int i = 0; i < gophers.length; i++) {
				graph[source][gophers[i].getIndex()] = 1;
			}
  		for (int i = 0; i < holes.length; i++) {
  			graph[holes[i].getIndex()][tap] = 1;
  		}

  		io.println(numOfGophers-EdmundKarp.maxFlow(graph, source, tap).getMaxFlow());
		}
		io.close();
	}
}
