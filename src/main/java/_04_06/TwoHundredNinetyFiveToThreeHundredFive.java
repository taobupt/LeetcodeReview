package _04_06;

/**
 * Created by tao on 4/6/17.
 */
public class TwoHundredNinetyFiveToThreeHundredFive {



    //300 longest increasing sequence
    //dp n^2
    //最大值不一定在最后啊，跪在这里了，不 一定在最后
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int []dp=new int[n+1];
        int res=0;
        for(int i=1;i<=n;++i){
            dp[i]=1;
            for(int j=1;j<i;++j){
                if(nums[i-1]>nums[j-1])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    //onlog(n) 的解法,非常经典



    //303 range sum query
    //in design
}
