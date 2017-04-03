package common;
import java.util.*;
import java.util.List;

/**
 * Created by tao on 4/3/17.
 */

enum Color {
    WHITE,
    BLACK,
    GRAY
}
public class Graph {
    private int []vertexs=null;
    private int []parent=null;
    private Map<Integer,List<Integer>>adj=null;
    private Map<Integer,Color>color=null;

    public Graph(){

    }
    public Graph(int[]nodes,int[][]edges){
        int n=nodes.length,m=edges.length;
        vertexs=nodes.clone();
        parent=new int[n];
        adj=new HashMap<>();
        color=new HashMap<>();
        for(int i=0;i<n;++i)
            color.put(i,Color.WHITE);
        for(int i=0;i<m;++i){
            if(!adj.containsKey(edges[i][0]))
                adj.put(edges[i][0],new ArrayList<>());
            adj.get(edges[i][0]).add(edges[i][1]);
        }
    }


    //whether there is a circle, if it has,print that one
    //当然进行topological sort is ok
    public void printCycle(int v,int u){
        if(v==u){
            System.out.println(v+" ");
        }else{
            printCycle(parent[v],u);
            System.out.println(v);
        }
    }

    public void dfs(int node){
        boolean cycle=false;
        color.put(node,Color.GRAY);
        int vv=0;
        List<Integer>neighbors=adj.getOrDefault(node,new ArrayList<>());
        for(int neighbor:neighbors){
            if(color.get(neighbor)==Color.WHITE){
                parent[neighbor]=node;
                dfs(neighbor);
            }else if(color.get(neighbor)==Color.GRAY){
                parent[neighbor]=node;
                vv=neighbor;
                cycle=true;
                break;
            }
        }
        color.put(node,Color.BLACK);
        if(cycle)
            printCycle(node,vv);
    }

    public void detectCycle(){
        for(int vertex:vertexs){
            if(color.get(vertex)==Color.WHITE)
                dfs(vertex);
        }
    }



    public boolean dfs(int node,Stack<Integer>stk,boolean[]vis,Map<Integer,List<Integer>>edges,boolean[]isLoop){
        if(vis[node])
            return true;
        if(isLoop[node])
            return false;
        isLoop[node]=true;
        List<Integer>neighbors=edges.getOrDefault(node,new ArrayList<>());
        for(int neighbor:neighbors){
            if(!dfs(neighbor,stk,vis,edges,isLoop))
                return false;
        }
        vis[node]=true;
        stk.push(node);
        return true;
    }
    //topological sort,怪不得可以进行有没有环进行检测
    public int[] topologicalSort(int numvertexs, int[][] edges){
        Stack<Integer> stk=new Stack<>();
        boolean []vis=new boolean[numvertexs];
        Map<Integer,List<Integer>>neighbors=new HashMap<>();
        for(int []edge:edges){
            if(!neighbors.containsKey(edge[1]))
                neighbors.put(edge[1],new ArrayList<>());
            neighbors.get(edge[1]).add(edge[0]);
        }
        for(int i=0;i<numvertexs;++i){
            if(!dfs(i,stk,vis,neighbors,new boolean[numvertexs]))
                return new int[]{};
        }
        int []res=new int[stk.size()];
        int index=0;
        while(!stk.isEmpty()){
            res[index++]=stk.pop();
        }
        return res;
    }

}
