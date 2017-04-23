package _04_14;

import java.util.Arrays;

/**
 * Created by tao on 4/21/17.
 */
public class ThreeHundredSeventysixToEightytyFive {

    //377 combination sum iV
    //
    /*
    good solution, but some cases such as
    [100000000,200000000,300000000]
    1400000000
    will get Memory Limit Exceeded due to you assign the length of "res" array as target+1,

    有负数的话其实是不行的额，我们知道有负，有正，你就可以无限制的搞了，所以要限定次数的条件下才有计算的必要，否则没有这个必要
     */
    public int combinationSum4(int[] nums, int target) {
        int []dp=new int[target+1];
        dp[0]=1;
        int n=nums.length;
        Arrays.sort(nums);
        for(int i=1;i<=target;++i){
            for(int j=0;j<n;++j){
                if(i>=nums[j]){
                    dp[i]+=dp[i-nums[j]];
                }else
                    break;
            }
        }
        return dp[target];
    }

    //其实可以用hashmap搞，你晚上再看看hashmap的version


    //378 kth smallest element in a sorted matrix
    //和373居然是一样的，我想不通啊
    public int kthSmallest(int[][] matrix, int k) {
        return 0;
    }

    //383 ransome note
    public boolean canConstruct(String ransomNote, String magazine) {
        char []ransom=ransomNote.toCharArray();
        char []mag=magazine.toCharArray();
        int []cnt=new int[128];
        for(char c:mag)
            cnt[c]++;
        for(char c:ransom){
            cnt[c]--;
            if(cnt[c]<0)
                return false;
        }
        return true;
    }

}
