import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijskra {
    //TODO if time allows change this to read from a file

    private final int[][] englingGraph =
        {{0, 53, 10, 12, 0, 0, 0, 0, 0, 0}, {53, 0, 33, 0, 2, 0, 101, 0, 0, 0}, {10, 33, 0, 9, 30, 18, 0, 0, 0, 0},
            {12, 0, 9, 0, 0, 17, 0, 0, 6, 0}, {0, 2, 30, 0, 0, 14, 123, 122, 0, 0}, {0, 0, 18, 17, 14, 0, 0, 137, 7, 0},
            {0, 101, 0, 0, 123, 0, 0, 8, 0, 71}, {0, 0, 0, 0, 122, 137, 8, 0, 145, 66},
            {0, 0, 0, 6, 0, 7, 0, 145, 0, 212}, {0, 0, 0, 0, 0, 0, 71, 66, 212, 0}};
    private final int graphSize = 10;

    /**
     * Finds the shortest path from the source node to all the given nodes. The entry in the returned list reading 0
     * is the node itself.
     *
     * @param graph     the graph to traverse
     * @param graphSize the size of the nxn graph
     * @return returns a list of weights from the source node to each other node
     */

    public List<Node> englingDijskra(int[][] graph, int sourceNode, int destNode, int graphSize) {
        //        List<Map.Entry<Integer, Integer>> dumpList = new ArrayList<>();
        //        PriorityQueue<Map.Entry<Integer, Integer>> pQueue =
        //            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());//<node, distance>. sort low high
        List<Node> listOfNodes = new ArrayList<>();
        for (int row = 0; row < graphSize; row++) {
            //            String name = switch (row) {
            //                case 0 -> "A";
            //                case 1 -> "J";
            //                case 2 -> "M";
            //                case 3 -> "R";
            //                case 4 -> "K";
            //                case 5 -> "S";
            //                case 6 -> "I";
            //                case 7 -> "N";
            //                case 8 -> "T";
            //                case 9 -> "D";
            //                default -> throw new IllegalStateException("Unexpected value: " + row);
            //            };
            if (row == sourceNode) {
                Node node = new Node(row, 0);
            }
            Node node = new Node(row, Integer.MAX_VALUE);
            listOfNodes.add(node);
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(listOfNodes);

        while (priorityQueue.size() > 0) {
            //TODO compare to destnode
            Node currentNode = priorityQueue.poll();//current shortest. at start it will be sourceNode
            currentNode.setHasBeenVisited();
            for (int neighbor = 0; neighbor < graphSize; neighbor++) {
                //current column, until column exhausted ("neighbor" = neighbor index, value = distance from me to u
                Node possibleNeighborNode = listOfNodes.get(neighbor);
                //my current distance plus my distance to you
                int possibleDistance = graph[currentNode.getNodeNameIndex()][neighbor] + currentNode.getDistance();
                if (!possibleNeighborNode.hasBeenVisited() && neighbor != currentNode.getNodeNameIndex()
                    && possibleDistance < possibleNeighborNode.getDistance()) {
                    possibleNeighborNode.setPrevious(currentNode);
                    possibleNeighborNode.setDistance(possibleDistance);
                    //reorder, since java has these methods private
                    if (!priorityQueue.remove(listOfNodes.get(neighbor))) {
                        System.out.println("Something went wrong removing previous old value for node in pQueue");
                    }
                    priorityQueue.add(listOfNodes.get(neighbor));
                }
            }
            if (currentNode.equals(listOfNodes.get(destNode))) {
                backTrackPath(currentNode);
                break;
            }
        }
        return backTrackPath(listOfNodes.get(destNode));
    }

    List<Node> backTrackPath(Node node) {
        List<Node> pathList = new ArrayList<>();

        Node currentNode = node;
        while (node != null) {
            pathList.add(currentNode);
            currentNode = node.getPrevious();
        }
        System.out.println();
        return pathList;
    }

    public static String getNodeName(int index) {
        return switch (index) {
            case 0 -> "A";
            case 1 -> "J";
            case 2 -> "M";
            case 3 -> "R";
            case 4 -> "K";
            case 5 -> "S";
            case 6 -> "I";
            case 7 -> "N";
            case 8 -> "T";
            case 9 -> "D";
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public void run(int startingNode, int destNode) {
        if (startingNode >= graphSize || startingNode < 0) {
            System.out.println("Invalid starting node for engling graph!");
            return;
        }
        englingDijskra(englingGraph, startingNode, destNode, 10);
    }

    class Node implements Comparable<Node> {
        private Node previous;

        private int nodeNameIndex;
        private int distance;
        private boolean hasBeenVisited = false;

        Node(int nodeNameIndex, int distance) {
            this.nodeNameIndex = nodeNameIndex;
            this.distance = distance;
        }

        public int getNodeNameIndex() {
            return nodeNameIndex;
        }

        void setHasBeenVisited() {
            hasBeenVisited = true;
        }

        boolean hasBeenVisited() {
            return hasBeenVisited;
        }

        void setDistance(int distance) {
            this.distance = distance;
        }

        int getDistance() {
            return distance;
        }

        void setPrevious(Node previous) {
            this.previous = previous;
        }

        Node getPrevious() {
            return previous;
        }

        @Override
        public int compareTo(Node toCompare) {
            int compare = this.distance - toCompare.getDistance();
            if (compare == 0) {
                return 0;
            } else if (compare > 0) {
                return 1;
            }
            return -1;
        }
    }
}
