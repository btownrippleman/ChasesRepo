import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.zip.DataFormatException;

public class SimulateAll {

	public static void main(String[] args) throws Exception {
		File[] files = new File(System.getProperty("user.dir")).listFiles();
		for (File f: files) System.out.println(f.toString());
		System.out.println("Simulation started.");	
		int totalRemainingEnergy = 0;
		PrintWriter pw = new PrintWriter("Results.txt");

		// Catch exceptions when Maze is created
		for (File f : files) {
			if (f.isFile() && f.getAbsolutePath().endsWith(".maze")) {
				System.out.println("file:" + f.getName());
				Maze myMaze;
				
				try {
					myMaze = new Maze(f);
					while (Maze.getLocationType(myMaze.getPlayerLocation()) != Maze.END
							&& myMaze.getPlayerEnergy() > 0) {
						myMaze.update();
					}
					totalRemainingEnergy = totalRemainingEnergy + myMaze.getPlayerEnergy();
					String resultLine = "";
					resultLine = resultLine + ("maze: "+f.getName());
					resultLine = " "+ resultLine + (" | energy remaining: "+myMaze.getPlayerEnergy());
					resultLine = " "+resultLine+"\n";
					pw.write(resultLine);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DataFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

			}
		}
		pw.write("Total Remaining Energy: "+totalRemainingEnergy);
		pw.close();
		System.out.println("Simulation complete.");
	}

}
