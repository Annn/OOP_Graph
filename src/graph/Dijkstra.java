package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dijkstra {


    ArrayList<Integer> path;
    public ArrayList<Integer> findPath(Graph graph, int fromNode, int toNode){

//
//        int len = graph.noOfNodes;
//        int[] unvisitedNodes = new int[len];
//        int[] visitedNodes = new int[len];
//
//        int infinity = (int) Double.POSITIVE_INFINITY;
//        int prevNode = 0;
//        for (int i = 0; i < len; i++){
//            unvisitedNodes[i] = i;
//        }
//
////        initialization of the table: nodes, shortest dist, prev nodes
//        List<int[]> distList = new ArrayList<>();
//
//        int[] temp = new int[3];
//        for (int i = 0; i < len; i++){
//            temp[0] = i;
//            temp[1] = infinity;
//            temp[2] = prevNode;
//            distList.add(temp);
//        }
////        the distance between first node and itself
//        distList.get(fromNode)[1] = 0;
//
////        First, we’ll visit the vertex with the smallest-known cost/distance
//        int[] tempArr = new int[len];
//        for (int i = 0; i < len; i++){
//            tempArr[i] = distList.get(i)[1];
//        }
//        int minDist = 0;
//
//        for (int i = 0; i < len; i++){
//            minDist = Arrays.stream(tempArr).min().getAsInt();
//
//        }
//        System.out.println(minDist);
//
//        for (int i = 0; i < len; i++) {
//
//            for (int j = 0; j < graph.nodeInfo.get(1)[i]; j++) {
//                int leaf = graph.nodeInfo.get(i)[j + 2];
//                int distance = graph.weights.get(fromNode) + graph.weights.get(leaf);
////                if (distance < )
//            }
////            unvisitedNodes.add();
//        }
//
//
////        while (! unvisitedNodes.isEmpty()){
//
////        }

        path = new ArrayList<>();

        int len = graph.noOfNodes;
        ArrayList<Integer> unvisitedNodes = new ArrayList<>();
        Integer[] prevNodes = new Integer[len + 1];
        Integer[] distances = new Integer[len + 1];

        int infinity = (int) Double.POSITIVE_INFINITY;

        // Initializations
        distances[fromNode] = 0;
        for (int[] ndi : graph.nodeInfo) {
            int node = ndi[0];
            if (node != fromNode) {
                distances[node] = infinity;
                prevNodes[node] = null;
            }
            unvisitedNodes.add(node);
        }

        while (!unvisitedNodes.isEmpty()) {
            Integer u = unvisitedNodes.get(0);
            double minDist = distances[u];
            for (Integer node : unvisitedNodes) {
                if (distances[node] < minDist) {
                    u = node;
                    minDist = distances[u];
                }
            }
            unvisitedNodes.remove(u);

            // check the neighbours
            ArrayList<Integer> neighbours = new ArrayList<>();
            for (int[] ndi : graph.nodeInfo) {
                int node = ndi[0];
                if (node == u) {
                    for (int i = 0; i < ndi[1]; i++)
                        neighbours.add(ndi[2 + i]);
                }
            }


            for (Integer v : neighbours) {
                int delta = distances[u] + graph.weights.get(v - 1);
                if (delta < distances[v]) {
                    distances[v] = delta;
                    prevNodes[v] = u;
                }
            }
        }


        Integer u = toNode;
        while (prevNodes[u] != null) {
            path.add(0, u);
            u = prevNodes[u];
        }
        path.add(0, fromNode);

        return path;
    }

    public String printPathStr(Graph graph){
        String res = "";
        for (Integer p : path){
            res += graph.words.get(p - 1);
//            System.out.print(res);
        }
        return res;
    }
}
