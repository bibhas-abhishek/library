package commons;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    public int val;

    public List<GraphNode> neighbors;

    public GraphNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
}
