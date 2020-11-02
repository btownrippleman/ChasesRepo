

// DON'T CHANGE THIS CLASS WITHOUT ASKING FIRST

// The only method you need to call from this class
// is getNeighbors, which returns an array of locations
// adjacent to the input location


public class Location implements Comparable<Location> {
	public int row;
	public int col;
	
	public Location(int r, int c) {
		row = r;
		col = c;
	}

	public Location[] getNeighbors() {
		Location[] neighbors = {
			new Location(row - 1, col),
			new Location(row, col + 1),
			new Location(row + 1, col),
			new Location(row, col - 1),
		};
		return neighbors;
	}
	
	@Override
	public int compareTo(Location loc) {
		int diff = row - loc.row;
		if (diff != 0) {
			return diff;
		}
		return col - loc.col;
	}
	
	@Override
	public boolean equals(Object obj) {
		Location loc = (Location)(obj);
		return row == loc.row && col == loc.col;
	}
	
	@Override
	public String toString() {
		return "Loc[r=" + row + ",c=" + col + "]";
	}
}
