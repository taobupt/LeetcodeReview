package _03_22;

import common.TreeNode;
import common.UnionFind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 3/22/17.
 */
public class OneHundredNinetyOneToZero {

    //191 number of 1bits
    public int hammingWeight(int n) {
        //return Integer.bitCount(n); 作弊
        int bit=0;
        while(n!=0){
            bit++;
            n&=(n-1);
        }
        return bit;
    }

    //198 house robber
    //dp
    public int rob(int[] nums) {
        int n=nums.length;
        int []dp=new int[n+1];
        for(int i=1;i<=n;++i){
            dp[i]=Math.max((i<2?0:dp[i-2])+nums[i-1],dp[i-1]);
        }
        return dp[n];
    }

    //199 typical questions
    //recursive way
    public void rightSideView(List<Integer>res,TreeNode node,int level){
        if(node==null)
            return;
        if(res.size()<=level)
            res.add(node.val);
        rightSideView(res,node.right,level+1);//just change direction to get left view
        rightSideView(res,node.left,level+1);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer>res=new ArrayList<>();
        rightSideView(res,root,0);
        return res;
    }

    //iteartiave way
    public List<Integer>rightSideViewIterative(TreeNode node){
        List<Integer>res=new ArrayList<>();
        if(node==null)
            return res;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty()){
            int size=q.size();
            while(size -- >0){
                TreeNode top=q.poll();
                if(size==0)
                    res.add(top.val);
                if(top.left!=null)
                    q.offer(top.left);
                if(top.right!=null)
                    q.offer(top.right);
            }
        }
        return res;
    }

    //200 number of islands
    //bfs dfs uf
    public void dfs(char[][]grid,int x,int y){
        if(x<0||x>=grid.length||y<0||y>=grid[0].length||grid[x][y]!='1')
            return;
        grid[x][y]='@';
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y-1);
        dfs(grid,x,y+1);
    }
    public int numIslands(char[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    ans++;
                }
            }
        }
        return ans;
    }

    //bfs
    public int numIsLands(char[][]grid){
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        Queue<int[]>q=new LinkedList<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    ans++;

                    q.offer(new int[]{i,j});
                    while(!q.isEmpty()){
                        int []top=q.poll();
                        for(int k=0;k<4;++k){
                            int xx=top[0]+dx[k];
                            int yy=top[1]+dy[k];
                            if(xx<0||xx>=m||yy<0||yy>=n||grid[xx][yy]!='1')
                                continue;
                            grid[xx][yy]='@';
                            q.offer(new int[]{xx,yy});
                        }
                    }
                }
            }
        }
        return ans;
    }

    //union find
    public int numIslandsByUnionFind(char[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int ans=0;
        UnionFind uf=new UnionFind(m*n);
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]=='1'){
                    ans++;
                    int index=i*n+j;
                    if(i>0 && grid[i-1][j]=='1'){
                        if(uf.mix(index,index-n))
                            ans--;
                    }
                    if(i<m-1 && grid[i+1][j]=='1'){
                        if(uf.mix(index,index+n))
                            ans--;
                    }
                    if(j>0 && grid[i][j-1]=='1'){
                        if(uf.mix(index,index-1))
                            ans--;
                    }
                    if(j<n-1 && grid[i][j+1]=='1'){
                        if(uf.mix(index,index+1))
                            ans--;
                    }
                }
            }
        }
        return ans;
    }



}
