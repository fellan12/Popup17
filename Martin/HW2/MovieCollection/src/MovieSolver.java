
public class MovieSolver {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in);
		int cases = io.getInt();
		for (int i = 0; i < cases; i++) {
			int movies = io.getInt();
			int requests = io.getInt();
			MovieCollection collection = new MovieCollection(movies, requests);
			for (int j = 0; j < requests; j++) {
				int req = io.getInt();
				io.print(collection.take(req));
				io.print(" ");
			}
			io.println();
		}
		io.close();
	}

}
