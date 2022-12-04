import java.util.Map;

public class EnglingHashTable {
    private final int size;


    private int occupied;
    private String[] array;

    public EnglingHashTable(int size) {
        this.size = size;//293
        array = new String[size];
    }

    public void insert(String element) {
        int hash = hashCode(element);
        while (!(array[hash % size] == null)) {//circular indexing
            hash++;
            //error handling
            if (hash == 2 * size) {
                System.out.println("Array Filled!");
                break;
            }
        }
        array[hash % size] = element;
        occupied++;
    }

    private int hashCode(String element) {
        return Hash.hashString(element);
    }

    public float getLoadFactor() {
        return (float) occupied / size;
    }

    public String getIndex(int index){
        String value = array[index];
        if (value == null) {
            return "Nothing";
        }
        return value;
    }
    public int getOccupied() {
        return occupied;
    }

    public int getSize(){
        return size;
    }
}
