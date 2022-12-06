import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRead {
    /**
     * Reads in strings from a file {@code pathToFile} that match {@code pattern}.
     *
     * @param pathToFile the path to a file to return strings from
     * @param pattern    a regex to match strings on
     * @return returns a list of strings that match the given regex from the given file
     */
    public static List<String> parseValidWordsFromFile(String pathToFile, String pattern) {
        Pattern allowedWords = Pattern.compile(pattern);
        List<String> cleanWords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {//valid line checking
                if (!line.isEmpty()) {
                    String[] rawWords = line.split("\\s");//raw
                    // words in current line
                    for (String word : rawWords) {
                        Matcher matcher = allowedWords.matcher(word);
                        if (!matcher.matches()) {
                            //replace non regex chars with empty string
                            String potentialWord = word.replaceAll(allowedWords.pattern(), "");
                            if(!potentialWord.isEmpty()) {
                                cleanWords.add(potentialWord);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cleanWords;
    }
}
