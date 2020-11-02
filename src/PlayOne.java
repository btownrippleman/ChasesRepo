import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class PlayOne {

	public static void main(String[] args) throws Exception{
		System.out.println("Chase Hudson");
		
		Maze myMaze; // Do not edit this line!
		
		// Complete tasks 1b and 1c here:
		
		Scanner keyboard  = new Scanner(System.in);

		myMaze= PlayOne.askValidFileName(keyboard);
		
		
		
		
		
		
		System.out.println("please choose a direction you want to go in the maze");
		String userIn = keyboard.nextLine();

		
		while(Maze.getLocationType(myMaze.getPlayerLocation()) != Maze.END && myMaze.getPlayerEnergy() > 0 && !userIn.equals("q")) {
			myMaze.update();
			myMaze.printMaze();
			// System.out.println("myMaze.getPlayerDirection());
			System.out.println("Continue? (q to quit): ");
			userIn = keyboard.nextLine();
		}
		if(Maze.getLocationType(myMaze.getPlayerLocation()) == Maze.END) {
			System.out.println("You escaped!");
		}
		else {
			System.out.println("You ran out of energy :(");
		}
		keyboard.close();
	}
	public static Maze askValidFileName(Scanner sc) throws Exception {
		Maze myMaze = null;
		try {
			System.out.println("Please enter a valid file name for a maze");
			String filename = sc.nextLine();
			File file = new File(filename);
			myMaze = new Maze(file);
			return myMaze;}
		catch(Exception e){
			e.printStackTrace();
			System.out.println();
			return askValidFileName(sc);
		}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			System.out.println("please enter a file that exists");
//	
//		}
//		catch (DataFormatException e) {
//			e.printStackTrace();
//		System.out.println("file entered is not formatted correctly");
//		}
//		catch(ParseException e) {
//			e.printStackTrace();
//			System.out.println("file has invalid characters");
//			
//		}
//		finally {
//			if (myMaze== null) {
//				return askValidFileName(sc);
//			}
//		}
//		return myMaze;

}
}