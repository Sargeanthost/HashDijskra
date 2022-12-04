import java.util.ArrayList;
import java.util.List;

public class Dijskra {
    //TODO if time allows change this to read from a file

    private final static int[][] graph =
        {{0, 53, 10, 12, 0, 0, 0, 0, 0, 0}, {53, 0, 33, 0, 2, 0, 101, 0, 0, 0}, {10, 33, 0, 9, 30, 18, 0, 0, 0, 0},
            {12, 0, 9, 0, 0, 17, 0, 0, 6, 0}, {0, 2, 30, 0, 0, 14, 123, 122, 0, 0}, {0, 0, 18, 17, 14, 0, 0, 137, 7, 0},
            {0, 101, 0, 0, 123, 0, 0, 8, 0, 71}, {0, 0, 0, 0, 122, 137, 8, 0, 145, 66},
            {0, 0, 0, 6, 0, 7, 0, 145, 0, 212}, {0, 0, 0, 0, 0, 0, 71, 66, 212, 0}};
    private static final int graphSize = 10;

    /**
     * Finds the shortest path from the source node to all the given nodes. The entry in the returned list reading 0
     * is the node itself.
     * @param graph the graph to traverse
     * @param graphSize the size of the nxn graph
     * @return returns a list of weights from the source node to each other node
     */

    public static List<Integer> dijskra(int[][] graph, int sourceNode, int graphSize){
        List<Integer> shortestPath = new ArrayList<>();

        return shortestPath;
    }
    //dfs
}
