package common;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tao on 4/3/17.
 */
public class GraphTest {

    Graph g=null;
    @Test
    public void testCycle(){
        int[]vertexs={0,1,2,3};
        int [][]edges={{0,1},{1,2},{2,3},{3,0}};
        g=new Graph(vertexs,edges);
        g.detectCycle();
    }

    @Test
    public void testTopologicalSort(){
        g=new Graph();
        int [][]edges={{1,0},{2,0},{3,1},{3,2}};
        g.topologicalSort(4,edges);
    }

}