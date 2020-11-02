import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Maze {

	private static char[][] maze;
	private Player player;

	public static final char OPEN = ' ';
	public static final char WALL = 'W';
	public static final char START = 'S';
	public static final char END = 'E';
	public static final char INVALID = 0;

	private Location start;
	private Location end;

	// RYAN: Studs throw FileNotFound up to main class
	// Studs throw NumberFormatException parseInt exceptions
	// catch
	// ensure width remains the same (num cols) every loop
	// ensure height matches when done (num lines == rows
	// throw exception
	// ensure a start location is given somewhere and only 1 is given
	// ensure an end location is given somewhere and only 1 is given
	// throw exception
	// ensure all values given are either START, END, WALL, or OPEN in the maze
	// throw exception
	// player always starts facing NORTH.
	
	public Maze(File mazeFile) throws FileNotFoundException, DataFormatException, ParseException{
		Scanner in;
		in = new Scanner(mazeFile);
		// first line: num rows
		// second line: num columns
		int rows = 0;
		int cols = 0;
		try {
			rows = Integer.parseInt(in.nextLine());
			cols = Integer.parseInt(in.nextLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("A valid file would start out with two integers");
		}
		Location start = new Location(0, 0);

		// floor plan
		ArrayList<char[]> lines = new ArrayList<char[]>();
		int numberOfStarts = 0;
		int numberOfEnds = 0;
		while (in.hasNextLine()) {
			char[] line = in.nextLine().toCharArray();
			if (line.length!= cols) { 
				in.close();
				throw new DataFormatException("number of columns read does not match what's declared initially");
			}
			for (int i = 0; i < line.length; i++) {
				if (line[i] == START) numberOfStarts++;
				if (line[i] == END) numberOfEnds++;
				if (!isValidCharacter(line[i])) {
					in.close();
					throw new ParseException("character at ("+i+","+lines.size()+") in maze must be walls,open,start and end", 0);
				}
			}			
			lines.add(line);
		}
		if (lines.size()!= rows) { 
			in.close();
			throw new DataFormatException("number of rows read does not match what's declared initially");
		}
		if( numberOfStarts > 1){
			in.close();
			throw new ParseException("More than one start makes for an invalid maze, please enter a  valid naze",0);
		}
		if( numberOfEnds > 1){
			in.close();
			throw new ParseException("More than one end makes for an invalid maze, please enter a  valid naze",0);
		}
				
		in.close();

		maze = new char[rows][cols];
		lines.toArray(maze);

		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[0].length; c++) {
				if (maze[r][c] == START) {
					start.row = r;
					start.col = c;
				}
			}
		}
		player = new Player(start, Player.NORTH);
	}
	
	
	//added methods
	///
	public boolean isValidCharacter(char c) {
		char[] characters = {OPEN,WALL,START,END};
		for (char ch:characters) {
			if (ch==c) return true;
		}
		return false;
	}
	//added methods
	///
	
	public void update() {
		player.drainEnergy(1);
		player.makeRandomMove();
		// player.makeWallMove();
		// player.makeSmartMove();
	}

	// YOU DO NOT NEED TO EDIT ANY CODE BELOW HERE.
	// PLEASE READ THROUGH AND USE THE METHODS BELOW AS NEEDED.
	public void printMaze() {
		System.out.println();
		for (int r = 0; r < maze.length; r++) {
			for (int c = 0; c < maze[0].length; c++) {
				if (player.getCurrentLocation().compareTo(new Location(r, c)) != 0) {
					System.out.print(maze[r][c]);
				} else {
					System.out.print('P');
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public int getNumRows() {
		return maze.length;
	}

	public int getNumCols() {
		return maze[0].length;
	}

	public static char getLocationType(Location loc) {
		if (isValid(loc)) {
			return maze[loc.row][loc.col];
		}
		return INVALID;
	}

	public static boolean isValid(Location loc) {
		return (loc.row >= 0 && loc.row < maze.length && loc.col >= 0 && loc.col < maze[loc.row].length);
	}

	public static boolean isOpen(Location loc) {
		return isValid(loc)
				&& (getLocationType(loc) == OPEN || getLocationType(loc) == START || getLocationType(loc) == END);
	}

	public static char[][] getMazeCopy() {
		char[][] copy = new char[maze.length][maze[0].length];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy[0].length; j++) {
				copy[i][j] = maze[i][j];
			}
		}
		return copy;
	}

	public Location getPlayerLocation() {
		return player.getCurrentLocation();
	}

	public int getPlayerEnergy() {
		return player.getEnergy();
	}

	public String getPlayerDirection() {
		if (player.getDirection() == Player.NORTH) {
			return "The player is facing North.";
		}
		if (player.getDirection() == Player.SOUTH) {
			return "The player is facing South.";
		}
		if (player.getDirection() == Player.EAST) {
			return "The player is facing East.";
		}
		if (player.getDirection() == Player.WEST) {
			return "The player is facing West.";
		}
		return "Unknown player direction.";
	}

}
