package common;

/**
 * Created by tao on 3/14/17.
 */
public class UnionFind {
    public int[]parent=null;
    public int[]rank=null;
    public int[]size=null;
    public int hightestRank=1;
    public UnionFind(int n){
        parent=new int[n+1];
        rank=new int[n+1];
        size=new int[n+1];
        for(int i=0;i<=n;++i){
            parent[i]=i;
            size[i]=1;
        }
    }

    public int find(int x){
        while(x!=parent[x]){
            parent[x]=parent[parent[x]];
            x=parent[x];
        }
        return x;
    }

    public boolean connected(int x,int y){
        return find(x)==find(y);
    }

    public boolean mix(int x,int y){
        int xx=find(x);
        int yy=find(y);
        if(xx==yy)
            return false;
        //比较短就在parent里面
        if(rank[xx]>rank[yy]){
            parent[yy]=xx;
        }else if(rank[xx]<rank[yy]){
            parent[xx]=yy;
        }else{
            parent[xx]=yy;
            rank[yy]++;
        }
        return true;
    }

    public boolean unionWithWeight(int x,int y){
        int xx=find(x);
        int yy=find(y);
        if(xx==yy)
            return false;
        if(size[xx]>size[yy]){
            parent[yy]=xx;
            size[xx]+=size[yy];
            hightestRank=Math.max(hightestRank,size[xx]);
        }else{
            parent[xx]=yy;
            size[yy]+=size[xx];
            hightestRank=Math.max(hightestRank,size[yy]);
        }

        return true;
    }
}
