import java.util.ArrayList;
import java.util.List;

public class Hash {
    /**
     * Hash function from Levitin.
     *
     * @param str the string to hash
     * @return returns the hash as a long
     */
    public static long hashString(String str) {
        long h = 0;
        final int C = 123; //some constant bigger than max ASCII char code
        final int m = 293; //size of hash table
        for (int i = 0; i < str.length(); i++) {
            h = (h * C + str.charAt(i)) % m;
        }
        return h;
    }

    /**
     * Hashes all strings found in {@code path} matching {@code regex} using {@link #hashString(String)}.
     *
     * @param path  The path containing strings
     * @param regex the pattern to match on
     * @return returns a list of hashes corresponding to each valid word
     */

    public static List<Long> hashStringsFromFile(String path, String regex) {
        List<String> wordsToHash = FileRead.parseValidWordsFromFile(path, regex);
        List<Long> hashes = new ArrayList<>();
        for (String word : wordsToHash) {
            hashes.add(hashString(word));
        }
        return hashes;
    }
}
