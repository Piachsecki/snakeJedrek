package kusnierz.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ResultReader {

    public static ArrayList<Result> getAllResult() throws FileNotFoundException {
        var results = new ArrayList<Result>();
        BufferedReader reader = new BufferedReader(new FileReader("src/Results/result.txt"));
        try {
            String line = reader.readLine();
            while (line != null) {
                var oneResult = line.split(" ");
                results.add(new Result(Integer.parseInt(oneResult[1]), oneResult[0]));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
