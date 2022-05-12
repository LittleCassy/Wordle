package wordle;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author LittleCassy
 */

public class FileShenanigans implements Serializable
{
    static File myText = new File("palabras.txt");
    public static ArrayList<String> words = new ArrayList<>();

    public static void readFile() throws FileNotFoundException{
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(myText)));
        try {
            String word;
            while ((word=bufferReader.readLine()) != null) {
                words.add(word);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}