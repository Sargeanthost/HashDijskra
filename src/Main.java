import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    //TODO change this to relative path from Main.java
    private static final String edgarPath =
        "C:\\Users\\sarge\\Documents\\School\\CS 2223\\HW5\\EdgarAllanPoeBellsB2022groomed.txt";
    private static final String edgarRegex = "[^'\\-A-Za-z]";
    private static List<Integer> hashes;
    private static List<String> words;

    public static void main(String[] args) {
        //        FileRead.parseValidWordsFromFile(edgarPath, edgarRegex).forEach(System.out::println);
        hash();
        hashTable();
        //Exploring the hashtable:
        // a
        // b
        // c
        // d
        // e
        //        dijkstra();

    }

    public static void hash() {
        //Print out hashes of all 604 words
        hashes = Hash.hashStringsFromFile(edgarPath, edgarRegex);
        words = FileRead.parseValidWordsFromFile(edgarPath, edgarRegex);
        //Print
        //        System.out.println("Hashes from file:");
        //        int perLine = 10;
        //        for (int i = 0; i < hashes.size(); i++) {
        //            if ((i % perLine == 0)) {
        //                System.out.println();
        //            }
        //            System.out.print(hashes.get(i) + " ");
        //        }
    }

    private static void hashTable() {
        //remove duplicates, preserving order
        Set<String> set = new LinkedHashSet<>();
        set.addAll(words);
        List<String> uniqueWords = new ArrayList<>();
        uniqueWords.addAll(set);
        //table
        EnglingHashTable hashTable = new EnglingHashTable(293);
        for (String word : uniqueWords) {
            hashTable.insert(word);
        }
        for (int index = 0; index < hashTable.getSize(); index++) {
            System.out.printf("",index, );
        }
        System.out.println(hashTable.getLoadFactor());
    }

    private static void dijkstra() {
    }
}
