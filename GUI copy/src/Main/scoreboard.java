package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class scoreboard {
    ArrayList<score> scores;
    
    public scoreboard() {
        this.scores = getScores();
    }

    public ArrayList<score> getScores() {
        String filename = "GUI copy/src/Main/scores.txt";
        ArrayList<score> arr = new ArrayList<score>();
		try {
            File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
                // get data from txt file
                String data = myReader.nextLine();
                // split line data into name and score
                int index = data.indexOf(":"); // split index
                String name = data.substring(0, index);
				int score = Integer.parseInt(data.substring(index + 1));
                // add the data to an arraylist
                score s = new score(name, score);
                arr.add(s);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("getScores failed");
			e.printStackTrace();
		}
		return arr;
	}

    // writes new score to scores.txt
    public void addScore(String name, int score) {
        System.out.println("addScore is called"); 
        try {
            FileWriter myWriter = new FileWriter("GUI copy/src/Main/scores.txt");
            myWriter.append(name + ":" + score);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("AddScore failed");
            e.printStackTrace();
        }
    }

    public int getScore(score s) {
        return s.getScore();
    }

    public String getName(score s) {
        return s.getName();
    }

}