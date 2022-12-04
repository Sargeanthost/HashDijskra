import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
        //print points given by doing problem 2
    }

    private static void hashTable() {
        //remove duplicates, preserving order
        Set<String> set = new LinkedHashSet<>(words);
        List<String> uniqueWords = new ArrayList<>(set);
        //create table
        EnglingHashTable hashTable = new EnglingHashTable(293);
        for (String word : uniqueWords) {
            hashTable.insert(word);
        }
        //print table
        System.out.printf("%-32s%-32s%-32s\n", "Hash Address", "Hashed Word", "Hash Value");
        for (int index = 0; index < hashTable.getSize(); index++) {
            System.out.printf("%-32d%-32s%-32d\n", index, hashTable.getIndex(index),
                              hashTable.getIndex(index).equals("0") ? -1 : Hash.hashString(hashTable.getIndex(index)));
        }
        System.out.println(
            "Occupied spaces: " + hashTable.getOccupied() + "\nLoad factor: " + hashTable.getLoadFactor());
        int[] empty = hashTable.getContiguousEmptyArea();
        System.out.println("The longest empty area starts at index: " + empty[0] + " and stops at (not including) " + (
            empty[0] + empty[1]));
        int[] full = hashTable.getContiguousFilledArea();
        System.out.println(
            "The longest non-empty area starts at index: " + full[0] + " and stops at (not including)" + " " + (
                full[0] + full[1]));
        int[] occur = hashTable.getMaxHashOccurrence();
        System.out.println("The most common hash is: " + occur[0] + " with the value being " + occur[1]);
        Map.Entry<String, Integer> farthestInsert = hashTable.getFarthestInsert();
        System.out.println("The farthest insert is: " + farthestInsert.getKey() + " with the value being "
                           + farthestInsert.getValue());


    }

    //    private static void dijkstra() {
    //    }
}
