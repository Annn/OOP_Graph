package graph;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Graph g = new Graph("vertex.txt");
//        System.out.println(g.convertBinaryToDec("0011"));

//        g.buildGraph("vertex.txt");

//        int weight = g.calcWeights("przyk(3x3)la(1x2)d(2x4)");
//        System.out.println(weight);

        Dijkstra di = new Dijkstra();
        ArrayList<Integer> path = di.findPath(g, 1, 6);

        for (Integer node : path) {
            System.out.print(node + " ");
        }
//        System.out.println();
//        for (String word : g.words){
//            System.out.print(word + " ");
//        }
        System.out.println();
        System.out.println(di.printPathStr(g));

    }
}
