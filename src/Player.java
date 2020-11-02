import java.util.Random;

public class Player {
	private Location currentLocation;
	private int facingDirection;
	private int energy;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	public Player(Location startingLocation, int startingDirection) {
		currentLocation = startingLocation;
		facingDirection = startingDirection;
		energy = 500;
	}

	public void makeRandomMove() {
		// Since this move is made randomly, we need a random number generator
		Random myRandom = new Random();
		// We can move in one of four directions (North, South, East, or West)
		int direction = myRandom.nextInt(4);
		// Get all four neighboring locations and store in an Array of Locations called
		// neighbors
		// Note: neighbors[0] is the location north of our current Location.
		// neighbors[1] is east. neighbors[2] is south. neighbors[3] is west.
		Location[] neighbors = currentLocation.getNeighbors();
		// If we randomly chose an invalid or wall location to move in to, try again.
		while (!Maze.isOpen(neighbors[direction])) {
			direction = myRandom.nextInt(4);
		}
		// take a step in the randomly selected open direction.
		takeStep(neighbors[direction]);
	}

	// Follow the maze strategy of moving along the wall to your right
	// from when you start until you exit. You may also choose to follow
	// the left wall, but you will not be able to verify your output against
	// the output shown in the project description.
	public void makeWallMove() {
		// This is starter code to demonstrate that if you were following
		// a wall on your right, you might be able to move. 
		// Note that this code is missing quite a few parts. You will
		// have to consider all the possible options for how your player
		// should move under various circumstances.
		
		// Note that when the player enters the maze they will always begin
		// facing North. Whenever the player moves, they will face in the direction
		// of their motion (if they move east, they will now face east. The block
		// to the player's right would then be south.).
		if(getRightType() == Maze.WALL) {
			if(Maze.isOpen(forward())) {
				takeStep(forward());
			}
		}
	}

	// Create your own maze strategy!
	public void makeSmartMove() {
	}

	// READ BUT DO NOT EDIT CODE BELOW THIS LINE!
	// ______________________________________________________________________________
	
	
	// Gets the location to the right of where our player currently faces.
	private char getRightType() {
		Location[] neighbors = currentLocation.getNeighbors();
		return Maze.getLocationType(neighbors[(facingDirection + 1) % 4]);
	}

	private Location right() {
		Location[] neighbors = currentLocation.getNeighbors();
		return neighbors[(facingDirection + 1) % 4];
	}
	
	
	// Gets the location to the left of where our player currently faces.
	private char getLeftType() {
		Location[] neighbors = currentLocation.getNeighbors();
		return Maze.getLocationType(neighbors[(facingDirection + 3) % 4]);
	}

	private Location left() {
		Location[] neighbors = currentLocation.getNeighbors();
		return neighbors[(facingDirection + 3) % 4];
	}
	
	
	// Gets the location in front of our player.
	private char getForwardType() {
		Location[] neighbors = currentLocation.getNeighbors();
		return Maze.getLocationType(neighbors[facingDirection]);
	}
	
	private Location forward() {
		Location[] neighbors = currentLocation.getNeighbors();
		return neighbors[facingDirection];
	}
	
	// Gets the location behind our player.
	private char getBackType() {
		Location[] neighbors = currentLocation.getNeighbors();
		return Maze.getLocationType(neighbors[(facingDirection + 2) % 4]);
	}
	private Location back() {
		Location[] neighbors = currentLocation.getNeighbors();
		return neighbors[(facingDirection + 2) % 4];
	}
	
	public void drainEnergy(int amt) {
		energy -= amt;
	}

	public int getEnergy() {
		return energy;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}
	
	private void takeStep(Location newLocation) {
		if(isValidMove(newLocation)) {
			if(newLocation.equals(right())) {
				facingDirection = (facingDirection + 1) % 4;
			}
			else if(newLocation.equals(left())) {
				facingDirection = (facingDirection + 3) % 4;
			}
			else if(newLocation.equals(back())) {
				facingDirection = (facingDirection + 2) % 4;
				
			}
			currentLocation = newLocation;
		}
	}
	
	private boolean isValidMove(Location newLocation) {
		Location[] neighbors = currentLocation.getNeighbors();
		if(newLocation.equals(neighbors[0]) || newLocation.equals(neighbors[1]) || newLocation.equals(neighbors[2]) || newLocation.equals(neighbors[3]) ) {
			if(Maze.getLocationType(newLocation) == Maze.OPEN || Maze.getLocationType(newLocation) == Maze.START ||Maze.getLocationType(newLocation) == Maze.END) {
				return true;
			}
			else {
				System.out.println("Invalid move given: " + newLocation + " is not a valid space to move to.");
				return false;
			}
		}
		else {
			System.out.println("Invalid move given: " + newLocation + " is not next to " + currentLocation + ".");
			return false;
		}
	}
	
	public int getDirection() {
		return facingDirection;
	}
}
