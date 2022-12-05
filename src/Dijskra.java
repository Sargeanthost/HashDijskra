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

    public static int[] dijskra(int[][] graph, int sourceNode, int graphSize){
        int[] shortestPath = new int[graphSize];
        int[] visitedNodes = new int[graphSize];//this or inverse, have to look up
        for(int index = 0; index < shortestPath.length; index++){
            if(index == sourceNode){
                continue;
            }
            shortestPath[index] = Integer.MAX_VALUE;
        }
        //get row sourcenode
        // for each column, if there is an edge wheight add it and the current nodes value together. if less than
        // that nodes saved value in shortest path, update.



        return shortestPath;
    }

    public void run(int startingNode) {
        if(startingNode >= graphSize || startingNode < 0){
            System.out.println("Invalid starting node for engling graph!");
            return;
        }
        dijskra(graph, startingNode, 10);
    }
}
