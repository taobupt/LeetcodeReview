package _04_08;

import java.util.LinkedList;
import java.util.Queue;

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
}
