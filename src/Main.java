import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    //TODO change this to relative path from Main.java
    private static final String edgarPath =
        "C:\\Users\\sarge\\Documents\\School\\CS 2223\\HW5\\EdgarAllanPoeBellsB2022groomed.txt";
    private static final String edgarRegex = "[^'\\-A-Za-z]";
    private static List<String> words;

    public static void main(String[] args) {
        hash();
        hashTable();
        dijkstra();
    }

    public static void hash() {
        words = FileRead.parseValidWordsFromFile(edgarPath, edgarRegex);
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
        System.out.println("The longest empty area starts at index " + empty[0] + " and stops at (not including) " + (
            empty[0] + empty[1]));
        int[] full = hashTable.getContiguousFilledArea();
        System.out.println(
            "The longest non-empty area starts at index " + full[0] + " and stops at (not including)" + " " + (
                full[0] + full[1]));
        int[] occur = hashTable.getMaxHashOccurrence();
        System.out.println("The most common hash is " + occur[0] + " with the number of occurrences being " + occur[1]);
        Map.Entry<String, Integer> farthestInsert = hashTable.getFarthestInsert();
        System.out.println(
            "The farthest insert is \"" + farthestInsert.getKey() + "\" with " + farthestInsert.getValue() + " shifts");
    }

    private static void dijkstra() {
        Dijskra englingMatrix = new Dijskra();
        Scanner sc = new Scanner(System.in);
        System.out.println("What node do you want to start at and stop at? Choose a number 0 through 9 for both: ");
        List<Dijskra.Node> shortestPath = englingMatrix.run(sc.nextInt(), sc.nextInt());
        printPath(shortestPath);
    }

    private static void printPath(List<Dijskra.Node> nodeList) {
        System.out.println(
            "The shortest path from " + Dijskra.Node.getNodeName(nodeList.get(nodeList.size() - 1).getNodeNameIndex())
            + " to " + Dijskra.Node.getNodeName(nodeList.get(0).getNodeNameIndex()) + " with distance " + nodeList.get(
                0).getDistance());
        for (int i = 0; i < nodeList.size(); i++) {
            Dijskra.Node node = nodeList.get(i);
            if (i == nodeList.size() - 1) {
                System.out.print(Dijskra.Node.getNodeName(node.getNodeNameIndex()));
                continue;
            }
            System.out.print(Dijskra.Node.getNodeName(node.getNodeNameIndex()) + " > ");
        }
        System.out.println();
    }
}
