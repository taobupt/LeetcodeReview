package common;
import com.sun.applet2.AppletParameters;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;
import java.util.List;

/**
 * Created by tao on 4/3/17.
 */



//最小生长树
    // krusal and prim algorithms, 其中克鲁斯卡尔使用并查集的， prim用优先级队
// 列，都是使用无向图，dijstra是使用有向图来做的
enum Color {
    WHITE,
    BLACK,
    GRAY
}

class Edge{
    int start;
    int dest;
    int weight;
    public Edge(int start,int end,int weight){
        this.start=start;
        this.dest=end;
        this.weight=weight;
    }

    public String toString(){
        return start+" -> "+dest+" : "+weight;
    }
}

class Vertex implements Comparable<Vertex>{
    int lable;
    int key;
    int parent;
    public Vertex(int label,int key,int parent){
        this.lable=label;
        this.key=key;
        this.parent=parent;
    }

    public int compareTo(Vertex o2){
        return key-o2.key;
    }

    public String toString(){
        return lable+" -> "+key+" : "+parent;
    }
}
public class Graph {
    private int []vertexs=null;
    private int []parent=null;
    private Map<Integer,List<Integer>>adj=null;
    private Map<Integer,List<Vertex>>neighbors=null;
    private Map<Integer,Color>color=null;
    private List<Edge>edges=null;
    private int[][]weightRelation =null;
    public Graph(){

    }

    public Graph(int []starts,int[]ends,int []weights){
        int n=starts.length;
        weightRelation=new int[n][n];
        Set<Integer>set=new HashSet<>();
        edges=new ArrayList<>();
        neighbors=new HashMap<>();
        for(int i=0;i<n;++i){
            set.add(starts[i]);
            set.add(ends[i]);
            weightRelation[starts[i]][ends[i]]=weights[i];
            weightRelation[ends[i]][starts[i]]=weights[i];
            edges.add(new Edge(starts[i],ends[i],weights[i]));
            if(!neighbors.containsKey(starts[i]))
                neighbors.put(starts[i],new ArrayList<>());
            neighbors.get(starts[i]).add(new Vertex(ends[i],Integer.MAX_VALUE,-1));
            if(!neighbors.containsKey(ends[i]))
                neighbors.put(ends[i],new ArrayList<>());
            neighbors.get(ends[i]).add(new Vertex(starts[i],Integer.MAX_VALUE,-1));
        }
        vertexs=new int[set.size()];
        int ind=0;
        for(int x:set){
            vertexs[ind++]=x;
        }
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


    // The algorithms of Kruskal and Prim
    // minimum spanning tree
    public void MSTKruskal(){

        UnionFind uf =new UnionFind(vertexs.length);
        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight-o2.weight;
            }
        });
        for(Edge e:edges){
            if(uf.find(e.start)!=uf.find(e.dest)){
                System.out.println(e);
                uf.mix(e.start,e.dest);
            }
        }
    }

    public int minKey(int []key,boolean[]vis){
        int index=-1,val =Integer.MAX_VALUE;
        for(int i=0;i<key.length;++i){
            if(!vis[i] && val>key[i]){
                index=i;
                val=key[i];
            }
        }
        return index;
    }


    public void printMST(int[]parent,int n,int[][]weight){
        for(int i=1;i<n;++i){
            System.out.println(parent[i]+" - "+ i+"    "+
                    weight[i][parent[i]]);
        }
    }

    public void MSTPrim(int[]starts,int []ends,int []weights){
        Map<Integer,List<Integer>>adjancet = new HashMap<>();
        int n=ends.length;
        Set<Integer>set=new HashSet<>();
        for(int i=0;i<n;++i){
            if(!adjancet.containsKey(starts[i]))
                adjancet.put(starts[i],new ArrayList<>());
            adjancet.get(starts[i]).add(ends[i]);
            if(!adjancet.containsKey(ends[i]))
                adjancet.put(ends[i],new ArrayList<>());
            adjancet.get(ends[i]).add(starts[i]);
            set.add(starts[i]);
            set.add(ends[i]);
        }

        int nn=set.size();
        int [][]weight=new int[nn][nn];
        for(int i=0;i<nn;++i)
            Arrays.fill(weight[i],Integer.MAX_VALUE);
        for(int i=0;i<n;++i){
           weight[starts[i]][ends[i]]=weights[i];
           weight[ends[i]][starts[i]]=weights[i];
        }
        int []key=new int[nn];
        Arrays.fill(key,Integer.MAX_VALUE);
        int []parent =new int[nn];
        Arrays.fill(parent,-1);
        boolean []vis =new boolean[nn];

        key[0]=0;
        for(int cnt =0;cnt<nn-1;++cnt){
            int u =minKey(key,vis);
            vis[u]=true;
            List<Integer>neigh =adjancet.getOrDefault(u,new ArrayList<>());
            for(int v:neigh){
                if(!vis[v] && weight[u][v]<key[v]){
                    parent[v]=u;
                    key[v]=weight[u][v];
                }
            }
        }
        printMST(parent,set.size(),weight);
    }



    public void printSolution(int []dist,int n){
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < n; i++)
            System.out.println(i+" \t\t "+dist[i]);
    }
    public void dijkstra(int[]starts,int []ends,int []weights,int src){
        Map<Integer,List<Integer>>adjancet = new HashMap<>();
        int n=ends.length;
        Set<Integer>set=new HashSet<>();
        for(int i=0;i<n;++i){
            set.add(starts[i]);
            set.add(ends[i]);
            if(!adjancet.containsKey(starts[i]))
                adjancet.put(starts[i],new ArrayList<>());
            adjancet.get(starts[i]).add(ends[i]);
        }

        int nn=set.size();
        int [][]weight=new int[nn][nn];
        for(int i=0;i<nn;++i)
            Arrays.fill(weight[i],Integer.MAX_VALUE);
        for(int i=0;i<n;++i){
            weight[starts[i]][ends[i]]=weights[i];
        }

        int []dist =new int[nn];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        boolean []vis =new boolean[nn];
        for(int cnt=0;cnt<nn-1;++cnt){
            int u = minKey(dist,vis);
            vis[u]=true;
            List<Integer>neigh =adjancet.getOrDefault(u,new ArrayList<>());
            for(int v:neigh){
                if(!vis[v] && dist[u]!=Integer.MAX_VALUE && dist[u]+weight[u][v]<dist[v]){
                    dist[v]=weight[u][v]+dist[u];
                }
            }

        }
        printSolution(dist, nn);
    }

}
