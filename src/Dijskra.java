import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

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
     *
     * @param graph     the graph to traverse
     * @param graphSize the size of the nxn graph
     * @return returns a list of weights from the source node to each other node
     */

    public static int[] dijskra(int[][] graph, int sourceNode, int graphSize) {
        int[] finalDistances = new int[graphSize];//index is the node, value is the final shortest path from source
        List<Map.Entry<Integer,Integer>> dumpList = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pQueue =
            new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());//<node, distance>. sort low high

        for (int i = 0; i < graphSize; i++) {
            if (i == sourceNode) {
                dumpList.add(i, Map.entry(sourceNode, 0));
            }
            dumpList.add(i, Map.entry(i,Integer.MAX_VALUE));
        }
        pQueue.addAll(dumpList);

        while (pQueue.size() > 0) {
            Map.Entry<Integer, Integer> nodeData = pQueue.poll();
            finalDistances[nodeData.getKey()] = nodeData.getValue();

            int nodeIndex = nodeData.getKey();
            int nodeValue = nodeData.getValue();
            int[] potentialNeighbors = graph[nodeIndex];
//            Map.Entry<Integer,Integer>[] referenceQueue = (Map.Entry<Integer, Integer>[]) pQueue.toArray();//bleh, dont
            // want to
            // redo what i have
            for (int i = 0; i < graphSize; i++) {
                int potentialNeighborValue = graph[nodeIndex][i];
                Map.Entry<Integer,Integer> workingEntry = dumpList.get(i); // more bleh
//                for(Map.Entry<Integer,Integer> entry: referenceQueue){
//                    if (entry.getKey() == i){
//                        workingEntry = entry;//! ONLY FOR VALUE CHECKING
//                    }
//                }

                //if not self, & me plus my connection is <less than your existing connection...
                if(potentialNeighborValue != 0 && i != nodeIndex && nodeValue + potentialNeighborValue < workingEntry.getValue()){

                }
            }
        }
        return finalDistances;
    }

    public void run(int startingNode) {
        if (startingNode >= graphSize || startingNode < 0) {
            System.out.println("Invalid starting node for engling graph!");
            return;
        }
        dijskra(graph, startingNode, 10);
    }

    //    class SmartPriorityQueue {
    //
    //        int size;//effective size
    //        List<Map.Entry<Integer,Integer>> list;//node, weight
    //
    //        /**
    //         * Queue made for dijskra. Initialize queue with graph.length elems, and set first entry in map to the
    //         node,
    //         * and the second to Infinity. When update is needed, will move map entry up based on the value of the
    //         node
    //         * key. This happens after each modification internally. {@link SmartPriorityQueue#pop} will return the
    //         * map entry and delete it from the queue.
    //         */
    //        SmartPriorityQueue() {
    //            size = 10;
    //            arr = new int[size];
    //            for (int index = 1; index < size; index++) {
    //                arr[index] = initValue;
    //            }
    //        }
    //
    //        void modifyIndex(int node, int value){
    //            for(Map.Entry<Integer, Integer> entry : list)
    //
    //            sort();
    //        }
    //
    //        int get
    //        void sort(){
    //            Arrays.sort(arr);
    //        }
    //    }
}
