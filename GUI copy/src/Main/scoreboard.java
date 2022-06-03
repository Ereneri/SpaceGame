package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class scoreboard {
    ArrayList<score> scores;
//    String in = "GUI copy/src/Main/scores.txt";
     String in = "src/Main/scores.txt";

    
    public scoreboard() {
        this.scores = getScores();
        sortScores();
    }

    public ArrayList<score> getScores() {
        String filename = in;
        ArrayList<score> arr = new ArrayList<score>();
		try {
            File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
			while (data.indexOf(";") != -1) {
                // get data from txt file
                // split line data into name and score
                int index = data.indexOf(":"); // split index
                String name = data.substring(0, index);
				int score = Integer.parseInt(data.substring(index + 1, data.indexOf(";")));

                data = data.substring(data.indexOf(";")+1);
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
            FileWriter myWriter = new FileWriter(in, true);
            myWriter.append(name + ":" + score +";");
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

    public void sortScores() {
        // bubble sort
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 0; j < scores.size() - 1 - i; j++) {
                if (scores.get(j).getScore() < scores.get(j + 1).getScore()) {
                    score temp = scores.get(j);
                    scores.set(j, scores.get(j + 1));
                    scores.set(j + 1, temp);
                }
            }
        }
    }

    public void updateScores() {
        this.scores = getScores();
        sortScores();
    }
}