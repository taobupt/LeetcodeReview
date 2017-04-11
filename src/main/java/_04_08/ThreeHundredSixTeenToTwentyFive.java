package _04_08;

import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 4/7/17.
 */
public class ThreeHundredSixTeenToTwentyFive {

    //317 Shortest Distance from All Buildings
    //
    public int shortestDistance(int[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int[][]reachable=new int[m][n];
        int[][]distance=new int[m][n];
        int buildNumber=0;
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]==1){
                    buildNumber++;
                    Queue<int[]>q=new LinkedList<>();
                    q.offer(new int[]{i,j});
                    boolean [][]vis=new boolean[m][n];
                    int ans=0;
                    while(!q.isEmpty()){
                        int size=q.size();
                        ans++;
                        for(int s=0;s<size;++s){
                            int []top=q.poll();
                            int xx=top[0];
                            int yy=top[1];
                            for(int k=0;k<4;++k){
                                int nx=xx+dx[k];
                                int ny=yy+dy[k];
                                if(nx<0||nx>=m||ny<0||ny>=n||vis[nx][ny]||grid[nx][ny]!=0)//
                                    continue;
                                vis[nx][ny]=true;
                                distance[nx][ny]+=ans;
                                reachable[nx][ny]++;
                                q.offer(new int[]{nx,ny});
                            }
                        }
                    }
                }
            }
        }
        int shortest=Integer.MAX_VALUE;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]==0 && reachable[i][j]==buildNumber)
                    shortest=Math.min(shortest,distance[i][j]);
            }
        }
        return shortest==Integer.MAX_VALUE?-1:shortest;
    }


    //319 bulb switcher
    //只有因数个数是奇数个，才会是开的，这样只有n*n
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    //322 coin change
    //纯dp
    public int coinChange(int[] coins, int amount) {
         int n=coins.length;
         int []dp=new int[amount+1];
         Arrays.fill(dp,amount+1);
         dp[0]=0;
         Arrays.sort(coins);//记住，要先sort，不用sort也行，就是break不能用了
         for(int i=1;i<=amount;++i){
            for(int j=0;j<n;++j){
                if(i>=coins[j]){
                    dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
                }else
                    break;
            }
         }
         return dp[amount]==amount+1?-1:dp[amount];
    }

    //323 Number of Connected Components in an Undirected Graph
    //union find and dfs
    public int countComponents(int n, int[][] edges) {
        UnionFind uf=new UnionFind(n);
        int res=n;
        for(int []edge:edges){
            if(uf.mix(edge[0],edge[1]))
                res--;
        }
        return res;
    }

    //dfs way

    public void dfs(int index,List<List<Integer>>adj,boolean[]vis){
        if(vis[index])
            return;
        vis[index]=true;
        List<Integer>neighbors=adj.get(index);
        for(int neigh:neighbors){
            dfs(neigh,adj,vis);
        }
        return;
    }
    public int countComponentsDFSway(int n, int[][] edges) {
        boolean []vis=new boolean[n];
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<n;++i)
            adj.add(new ArrayList<>());
        for(int []edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int cnt=0;
        for(int i=0;i<n;++i){
            if(!vis[i]){
                dfs(i,adj,vis);
                cnt++;
            }
        }
        return cnt;
    }


    //324 wiggle sort
    //总有天才能做得到，卧槽
    //tag
    //交替填入也得注意必须降序交替，否则任然有可能挨在一起。比如[4, 5, 5, 6]这一组如果升序交替填入任然是4、5、5、6，降序则为5、6、4、5
    public void wiggleSort(int[] nums) {
        //sort first
        int []copy=nums.clone();
        Arrays.sort(copy);
        int n=nums.length;
        if(n<=1)
            return;
        int index=n-1;
        if(n%2==0){
            int begin=0,end=n/2;
            while(end<n){
                nums[index--]=copy[end++];
                nums[index--]=copy[begin++];
            }
        }else{
            int begin=0,end=n/2+1;
            nums[index--]=copy[begin++];
            while(end<n){
                nums[index--]=copy[end++];
                nums[index--]=copy[begin++];
            }

        }
    }

    //bfs 访问也是可以的
    //325 maximum size subarray sum equals k
    //hashmap
    public int maxSubArrayLen(int[] nums, int k) {
        int n=nums.length;
        int sum=0;
        Map<Integer,Integer> map=new HashMap<>();
        int res=0;
        for(int i=0;i<n;++i){
            sum+=nums[i];
            if(sum==k){//这里不是直接跳，而是取maximum
                res=Math.max(res,i+1);
            }
            if(map.containsKey(sum-k)){
                res=Math.max(res,i-map.get(sum-k));
            }
            if(!map.containsKey(sum))//这里的更新是有原则的，求最大，最小，其实用到hashmap比较多，如果都是整数的话，其实用到两个窗口比较多。
                map.put(sum,i);
        }
        return res;
    }
}
