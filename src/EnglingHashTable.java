import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnglingHashTable {
    private final int size;

    private int occupied;
    private final String[] array;
    private final int[] hashOccurrenceArray;
    private final HashMap<String, Integer> wordOffsetList = new HashMap<>();

    public EnglingHashTable(int size) {
        this.size = size;//293
        array = new String[size];
        hashOccurrenceArray = new int[size];
    }

    public void insert(String element) {
        int hash = hashCode(element);
        int index = hash;
        int distance = 0;
        hashOccurrenceArray[hash]++;

        while (!(array[index % size] == null)) {//circular indexing
            index++;
            distance++;
            //error handling
            if (index == 2 * size) {
                System.out.println("Array Filled!");
                break;
            }
        }
        array[index % size] = element;
        occupied++;
        wordOffsetList.put(element, distance);
    }

    private int hashCode(String element) {
        return Hash.hashString(element);
    }

    public float getLoadFactor() {
        return (float) occupied / size;
    }

    public String getIndex(int index) {
        String value = array[index];
        if (value == null) {
            return "0";
        }
        return value;
    }

    public int[] getContiguousEmptyArea() {
        int startPointer = 0;
        int pointerOffset = 0;

        for (int i = 0; i < size; i++) {
            int tempPointerOffset = 0;
            while (array[(i + tempPointerOffset) % size] == null) {
                tempPointerOffset++;
            }
            if (tempPointerOffset > pointerOffset) {
                startPointer = i;
                pointerOffset = tempPointerOffset;
            }
        }

        return new int[] {startPointer, pointerOffset};
    }

    public int getOccupied() {
        return occupied;
    }

    public int getSize() {
        return size;
    }

    public int[] getContiguousFilledArea() {
        int startPointer = 0;
        int pointerOffset = 0;

        for (int i = 0; i < size; i++) {
            int tempPointerOffset = 0;
            while (!(array[(i + tempPointerOffset) % size] == null)) {
                tempPointerOffset++;
            }
            if (tempPointerOffset > pointerOffset) {
                startPointer = i;
                pointerOffset = tempPointerOffset;
            }
        }

        return new int[] {startPointer, pointerOffset};
    }

    public int[] getMaxHashOccurrence() {
        int mostCommonHash = getMaxIndex(hashOccurrenceArray);

        return new int[] {mostCommonHash, hashOccurrenceArray[mostCommonHash]};
    }

    private int getMaxIndex(int[] arr) {
        int maxSumIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            maxSumIndex = arr[i] > arr[maxSumIndex] ? i : maxSumIndex;
        }
        return maxSumIndex;
    }

    public Map.Entry<String, Integer> getFarthestInsert() {
        int maxValue = (Collections.max(wordOffsetList.values()));
        String key = "";
        Map.Entry<String, Integer> farthestInsert = Map.entry("-1", -1);

        for (Map.Entry<String, Integer> entry : wordOffsetList.entrySet()) {
            if (entry.getValue() == maxValue) {
                farthestInsert = entry;
            }
        }
        return farthestInsert;
    }
}
