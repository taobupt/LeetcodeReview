package _04_25;

import common.Interval;

import java.util.*;

/**
 * Created by tao on 5/27/17.
 */
public class FourHundredEightySixToFourHundredNinetyFive {



    //486 predict the winner
    //两人猜游戏的话，一般使用minmax的解法的
    //做事做了出来，完了加memory based的
    public int play(int[]nums,int begin,int end){
        if(begin==end)
            return nums[begin];
        else if(end==begin+1)
            return Math.max(nums[begin],nums[end]);
        int res=0;
        res=Math.max(res,nums[end]+Math.min(play(nums,begin+1,end-1),play(nums,begin,end-2)));
        res=Math.max(res,nums[begin]+Math.min(play(nums,begin+1,end-1),play(nums,begin+2,end)));
        return res;

    }
    public boolean PredictTheWinner(int[] nums) {
         int sum=0,n=nums.length;
         for(int x:nums)
             sum+=x;
         return play(nums,0,n-1)>=sum/2+1;
    }

    //487 Max Consecutive Ones II,一次就过还是值得欣慰的，毕竟第一次做了4次。还是要考虑一些cornner case
    public int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length;
        int i=0,start=0;
        if(n==0)
            return 0;
        List<Interval> res=new ArrayList<>();
        while(i<n){
            if(nums[i]==1){
                start=i;
                while(i<n && nums[i]==1)
                    i++;
                res.add(new Interval(start,i-1));
            }else
                i++;
        }

        if(res.isEmpty())
            return 1;
        if(res.size()==1){
            if(res.get(0).start==0 && res.get(0).end==n-1)
                return n;
            else
                return res.get(0).end-res.get(0).start+2;
        }
        int ans=res.get(0).end-res.get(0).start+2;;
        for(int j=1;j<res.size();++j){
            ans=Math.max(ans,res.get(j).end-res.get(j).start+2);
            if(res.get(j).start==res.get(j-1).end+2){
                ans=Math.max(ans,res.get(j).end-res.get(j-1).start+1);
            }
        }
        return ans;
    }

    //当然也不要高兴太早，其实也是一个window的题, 含有k个zero的题,稍微变变就是一道题
    public int findMaxConsecutiveOnesTwoWindow(int[] nums){
        int k=1,begin=0,end=0,n=nums.length,zeros=0,res=0;
        while(end<end){
            if(nums[end++]==0)
                zeros++;
            while(zeros>k){
                if(nums[begin++]==0)
                    zeros--;
            }
            res=Math.max(res,end-begin);
        }
        return res;
    }

    //follow up
    //Now let's deal with follow-up, we need to store up to k indexes of zero within the window [l, h] so that we know where to move
    // l next when the window contains more than k zero. If the input stream is infinite, then the output could be extremely large
    // because there could be super long consecutive ones. In that case we can use BigInteger for all indexes. For simplicity,
    // here we will use int
    //Time: O(n) Space: O(k)
    public int findMaxConsecutiveOnesFollowUp(int[] nums) {
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>();
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }

}
