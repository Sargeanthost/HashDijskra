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
        List<Node> listOfNodes = new ArrayList<>();
        for (int row = 0; row < graphSize; row++) {
            if (row == sourceNode) {
                Node node = new Node(row, 0);
                listOfNodes.add(node);
                continue;
            }
            Node node = new Node(row, Integer.MAX_VALUE);
            listOfNodes.add(node);
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(listOfNodes);

        while (priorityQueue.size() > 0) {
            Node currentNode = priorityQueue.poll();//current shortest. at start, it will be sourceNode
            if (currentNode.equals(listOfNodes.get(destNode))) {
                break;
            }
            for (int neighbor = 0; neighbor < graphSize; neighbor++) {
                Node possibleNeighborNode = listOfNodes.get(neighbor);
                int possibleDistance = graph[currentNode.getNodeNameIndex()][neighbor] + currentNode.getDistance();
                if (graph[currentNode.getNodeNameIndex()][neighbor] != 0 && neighbor != currentNode.getNodeNameIndex()
                    && possibleDistance < possibleNeighborNode.getDistance()) {
                    possibleNeighborNode.setPrevious(currentNode);
                    possibleNeighborNode.setDistance(possibleDistance);
                    //reorder, since java has these methods private, even tho O(n), but we're not racing here
                    if (!priorityQueue.remove(listOfNodes.get(neighbor))) {
                        System.out.println("Something went wrong removing previous old value for node in pQueue");
                    }
                    priorityQueue.add(listOfNodes.get(neighbor));
                }
            }
        }
        return backTrackPath(listOfNodes.get(destNode));
    }

    List<Node> backTrackPath(Node node) {
        List<Node> pathList = new ArrayList<>();

        Node currentNode = node;
        while (currentNode != null) {
            if (currentNode.getPrevious() == null) {
                pathList.add(currentNode);
                break;
            }
            pathList.add(currentNode);
            currentNode = currentNode.getPrevious();
        }
        System.out.println();
        return pathList;
    }

    /**
     * Run Dijkstra's algorithm on Engling's graph
     *
     * @param startingNode the starting node index. i.e. A=0, D=9
     * @param destNode     the goal node
     * @return returns the shortest path from startingNode to destNode
     */
    public List<Node> run(int startingNode, int destNode) {
        if (startingNode >= graphSize || startingNode < 0 || destNode >= graphSize || destNode < 0) {
            System.out.println("Invalid starting node for engling graph!");
            return null;
        }
        return englingDijskra(englingGraph, startingNode, destNode, 10);
    }

    static class Node implements Comparable<Node> {
        private Node previous;
        private final int nodeNameIndex;
        private int distance;

        /**
         * Dijskra specific node.
         *
         * @param nodeNameIndex the index of the graph's row
         * @param distance      the distance from the starting node to this node
         */
        Node(int nodeNameIndex, int distance) {
            this.nodeNameIndex = nodeNameIndex;
            this.distance = distance;
        }

        public int getNodeNameIndex() {
            return nodeNameIndex;
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

        public static String getNodeName(int index) {
            return switch (index) {
                case 9 -> "D";
                case 6 -> "I";
                case 1 -> "J";
                case 4 -> "K";
                case 5 -> "S";
                case 8 -> "T";
                case 3 -> "R";
                case 0 -> "A";
                case 2 -> "M";
                case 7 -> "N";
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
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
