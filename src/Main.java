import java.util.List;

public class Main {
    private static final String edgarPath =
        "C:\\Users\\sarge\\Documents\\School\\CS " + "2223\\HW5\\EdgarAllanPoeBellsB2022groomed.txt";
    private static final String edgarRegex = "[^\\x27\\x2D\\x41-\\x5a\\x61-\\x7a]";

    public static void main(String[] args) {
        hash();
    }

    public static void hash() {
        List<Long> hashes = Hash.hashStringsFromFile(edgarPath, edgarRegex);
        System.out.println("Hashes from file:");
        int perLine = 10;
        for (int i = 0; i < hashes.size(); i++) {
            if ((i % perLine == 0)) {
                System.out.println();
            }
            System.out.print(hashes.get(i) + " ");
        }
    }
}