package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class scoreboard {
    ArrayList<score> scores;
    
    // public scoreboard() {
    //     this.scores = getScores();
    // }

    // public static ArrayList<score> getScores() {
    //     return
    // }

    public static void writeToTheFile(String fileName, ArrayList<String> words) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File(fileName));
        for (int i = 0; i < words.size(); i++) {
            output.println(words.get(i));
        }
        output.close();
    }

}