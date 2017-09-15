
public class MovieCollection extends SumFenwickTree {
	
	int[] indexMap;

	public MovieCollection(int movies, int requests) {
		super(movies+requests);
		indexMap = new int[movies+1];
		for (int i = 1; i < indexMap.length; i++) {
			indexMap[i] = leafIndexes.length-movies+(i-1);
			this.add(indexMap[i], 1);
		}
		indexMap[0] = leafIndexes.length-movies-1;
	}
	
	public int take(int movie) {
		this.add(indexMap[movie], -1);
		int ret = this.sumLeft(indexMap[movie]);
		indexMap[movie] = indexMap[0];
		indexMap[0]--;
		this.add(indexMap[movie], 1);
		return ret;
	}

}
