package graph;

import java.io.IOException;

public interface GraphInterface {
    int convertBinaryToDec(String line);
    int calcWeights(String text);
    void buildGraph(String name) throws IOException;
}
