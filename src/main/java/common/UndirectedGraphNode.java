package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 3/15/17.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}
