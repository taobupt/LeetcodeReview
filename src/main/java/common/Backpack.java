package common;

import java.util.*;

/**
 * Created by tao on 5/23/17.
 */
public class Backpack {

    //lint code 92 backpack
    public int backPack(int m, int[] A) {
        // write your code here
        //two dimension
        //f[i][v]=f[i-1][v],f[i-1][m-A[i]]+A[i];
        int n=A.length;
        int []dp=new int[m+1];
        for(int i=0;i<n;++i){
            for(int j=m;j>=A[i];--j){
                dp[j]=Math.max(dp[j],dp[j-A[i]]+A[i]);
            }
        }
        return dp[m];
    }

    //two dimension backpack
    public int backPackUnoptimized(int m, int[] A) {
        // write your code here
        //two dimension
        //f[i][v]=f[i-1][v],f[i-1][m-A[i]]+A[i];
        //灵活变通啊，小伙子
        int n=A.length;
        int [][]dp=new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            for(int j=0;j<=m;++j){
                dp[i][j]=dp[i-1][j];
                if(j>=A[i-1])
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-A[i-1]]+A[i-1]);
            }
        }
        return dp[n][m];

    }

    //125 backpack II
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        //这才是正中的背包
        int []dp=new int[m+1];
        int n=A.length;
        for(int i=0;i<n;++i){
            for(int j=m;j>=A[i];--j){
                dp[j]=Math.max(dp[j],dp[j-A[i]]+V[i]);
            }
        }
        return dp[m];
    }

    //two dimension
    public int backPackIIUnoptimized(int m, int[] A, int V[]){
        int n=A.length;
        int [][]dp=new int[n+1][m+1];
        for(int i=1;i<=n;++i){
            for(int j=0;j<=m;++j){
                dp[i][j]=dp[i-1][j];
                if(j>=A[i-1])
                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-A[i-1]]+V[i-1]);
            }
        }
        return dp[n][m];
    }


    //重复选择+最大价值
    public int backPackIII(int m,int []A,int V[]){
        int n=A.length;
        int dp[]=new int[m+1];
        for(int i=0;i<n;++i){
            for(int j=A[i];j<=m;++j){
                dp[j]=Math.max(dp[j],dp[j-A[i]]+V[i]);
            }
        }
        return dp[m];
    }

    //重复选择+唯一排列+装满可能性总数, VI这种是有重复的排列的。也就　combination SUMIV
    //backup IV
    public int backPackIV(int m,int []A){
        int []dp=new int[m+1];
        int n=A.length;
        dp[0]=1;
        Map<Integer,HashSet<Integer>>map=new HashMap<>();
        map.put(0,new HashSet<>());
        //map.get(0).add(0);
        for(int i=1;i<=m;++i){
            map.put(i,new HashSet<>());
            for(int j=0;j<n;++j){
                if(i>=A[j]){
                    dp[i]+=dp[i-A[j]];
                    if(dp[i]-A[j]!=0){
                        map.get(i).addAll(map.get(i-A[j]));
                        map.get(i).add(j);
                    }
                }
            }
        }
        return map.get(m).size();
    }
    //save space
    public int backPackIVSaveSpace(int m,int []A){
        int []dp=new int[m+1];
        int n=A.length;
        dp[0]=1;
        for(int i=0;i<n;++i){
            for(int j=0;j<=m;++j){
                if(j>=A[i])
                    dp[j]+=dp[j-A[i]];
            }
        }
        return dp[m];
    }



    //单次选择+装满可能性总数
    public int backPackV(int m,int []A){
        int []dp=new int[m+1];
        int n=A.length;
        dp[0]=1;
        for(int i=0;i<n;++i){
            for(int j=m;j>=A[i];--j){
                dp[j]+=dp[j-A[i]];
            }
        }
        return dp[m];
    }

    //backpackVI ,combination sumIV, 重复的排列
    public int backpackVi(int[] nums, int target) {
        int []dp=new int[target+1];
        dp[0]=1;
        int n=nums.length;
        for(int i=1;i<=target;++i){
            for(int j=0;j<n;++j){
                if(i>=nums[j]){
                    dp[i]+=dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }

    //minimum adjustment cost
    //看不出和背包有啥关系啊
    //确实是功力太弱啊
    public int MinAdjustmentCost(ArrayList<Integer> A, int target) {
        // write your code here
        return 0;
    }


}
