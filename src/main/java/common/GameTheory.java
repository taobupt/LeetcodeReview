package common;

import java.util.Arrays;

/**
 * Created by tao on 4/5/17.
 */
public class GameTheory {


    //这里都是些博弈类的题目，还是很有针对性的

    //lintcode coins in a line
    public boolean firstWillWin(int n) {
        // write your code here
        return n%3!=0;
    }


    //博弈类的解法
    public boolean firstWillWinGame(int n) {
        // write your code here
        if(n==0)
            return false;
        if(n<=2)
            return true;
        boolean []dp=new boolean[n+1];
        dp[1]=dp[2]=true;
        for(int i=3;i<=n;++i)
            dp[i]=(!dp[i-1]|!dp[i-2]);
        return dp[n];
    }


    //值最大，就赢了，minmax，博弈类的精华，必要的时候剪枝
    public boolean firstWillWin(int[] values) {
        // write your code here
        int n=values.length;
        if(n<=2)
            return true;
        int []dp=new int[n+1];//
        Arrays.fill(dp,-1);
        dp[0]=0;
        dp[1]=values[n-1];
        dp[2]=values[n-2]+values[n-1];
        int sum=0;
        for(int x:values)
            sum+=x;
        return 2*memorySearch(n,values,dp)>sum;
    }
    public int memorySearch(int coins,int[]values,int []dp){
        if(coins<0)
            return 0;
        if(dp[coins]!=-1)
            return dp[coins];
        int n=values.length;
        int onemax=values[n-coins]+Math.min(memorySearch(coins-2,values,dp),memorySearch(coins-3,values,dp));
        int twomax=values[n-coins]+values[n-coins+1]+Math.min(memorySearch(coins-3,values,dp),memorySearch(coins-4,values,dp));
        dp[coins]=Math.max(onemax,twomax);
        return dp[coins];
    }

    //coins in a line another solution

    public boolean firstWillWinAnotherSolution(int[] values) {
        // write your code here
        int n=values.length;
        if(n<=2)
            return true;
        int []dp=new int[n+1];//
        Arrays.fill(dp,-1);
        dp[0]=0;
        dp[1]=values[n-1];
        dp[2]=values[n-2]+values[n-1];
        return helper(dp,n,values)>0;
    }
    public int helper(int[]dp,int coins,int[]values){
        if(coins<0)
            return 0;
        if(dp[coins]!=-1)
            return dp[coins];
        int n=values.length;
        dp[coins]=Math.max(values[n-coins]-helper(dp,coins-1,values),values[n-coins]+values[n-coins+1]-helper(dp,coins-2,values));
        return dp[coins];
    }


    //leetcode 486 predict the winner
    public boolean PredictTheWinner(int[] nums) {
        //和上题有稍微不同的地方
        //Because if you look at the sum of all the even element S_even and the sum of all the odd elements S_odd, then compare
        // the two sums to decide which elements to take, i.e you can force your opponent to either take all of the even elements
        // or all of the odd element. Hence you can always beet your opponent.
        int n=nums.length;
        if(n%2==0)
            return true;
        int [][]dp=new int[n][n];
        if(n<=1)
            return true;
        for(int i=0;i<n;++i)
            Arrays.fill(dp[i],-1);//假设没有面值为 0 的硬币，相比上一题跳过了 Arrays.fill -1 的初始化
        for(int i=0;i<n;++i)
            dp[i][i]=nums[i];
        int sum=0;
        for(int x:nums)
            sum+=x;
        return 2*PredictTheWinner(nums,dp,0,n-1)>sum;
    }
    public int PredictTheWinner(int[]nums,int[][]dp,int begin,int end){
        if(begin>end)
            return 0;
        if(dp[begin][end]!=-1)
            return dp[begin][end];
        int n=nums.length;
        int onemax=nums[begin]+Math.min(PredictTheWinner(nums,dp,begin+2,end),PredictTheWinner(nums,dp,begin+1,end-1));
        int twomax=nums[end]+Math.min(PredictTheWinner(nums,dp,begin+1,end-1),PredictTheWinner(nums,dp,begin,end-2));
        //二维dp
        dp[begin][end]=Math.max(onemax,twomax);
        return dp[begin][end];
    }

    //374 guess number higher or lower
    //注意api的返回值是啥，否则很容易搞混


    //375 Guess Number Higher or Lower II
    public int getMoneyAmount(int n) {
        //就不是两次了，而是多次的minmax
         int [][]dp=new int[n+1][n+1];
         //dp[L][R]=Math.min(i+max(dp[L][i-1],dp[i+1][R]));
        //R 要用到低层的信息，l要用到高层的信息
        for(int L=n-1;L>=1;--L){
            for(int R=L+1;R<=n;++R){
                dp[L][R]=Integer.MAX_VALUE;
                for(int i=L;i<=R;++i)
                    dp[L][R]=Math.min(dp[L][R],i+Math.max(dp[L][i-1],(i==R?0:dp[i+1][R])));
            }
        }
        return dp[1][n];
    }
    //

    public int getMoneyAmountMemory(int n){
        return getMoneyAmount(1,n,new int[n+1][n+1]);
    }
    public int getMoneyAmount(int start,int end,int[][]dp){
        if(start>=end)
            return 0;
        if(dp[start][end]!=0)//start==end, 必猜中啊
            return dp[start][end];
        int value=Integer.MAX_VALUE;
        for(int i=start;i<=end;++i){
            value=Math.min(value,i+Math.max(getMoneyAmount(start,i-1,dp),getMoneyAmount(i+1,end,dp)));
        }
        dp[start][end]=value;
        return value;
    }


}


