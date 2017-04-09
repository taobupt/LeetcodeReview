package mathQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tao on 4/7/17.
 */
public class MathQuestions {

    //453. Minimum Moves to Equal Array Elements
    //这道题其实很考脑筋急转弯
    //n-1 个小的增加1，相当于1个最大的减小一，相对，等下会有具体的数学证明
    //最小的值是min，经过m步骤，那么最终值为min+m，(min+m)*n-sum=(n-1)*m
    //m=sum-n*min;
    public int minMoves(int[] nums) {
        if(nums.length==0)
            return 0;
        int minValue=Integer.MAX_VALUE,n=nums.length;
        int sum=0;
        for(int x:nums){
            if(x<minValue)
                minValue=x;
            sum+=x;
        }
        return sum-n*minValue;
    }

    //Minimum Moves to Equal Array Elements II
    //做过的题就会忘，我也是吊炸天
    //quick selec and find meadian, if the length is odd ,then ok, else just return one of the two elements
    public int minMoves2(int[] nums) {
        int n=nums.length,begin=0,end=n-1;
        Arrays.sort(nums);
        int cnt=0;
        while(begin<end){
            cnt+=nums[end--]-nums[begin++];//median is x, nums[end]-x+x-nums[beign];其实就是把x省去了
        }
        return cnt;
    }

    //296 Best Meeting Point
    public int minTotalDistance(int[][] grid) {
        //x,y 分开，然后同时走
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        List<Integer> xx=new ArrayList<>();
        List<Integer>yy=new ArrayList<>();
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]==1){
                    xx.add(i);
                    yy.add(j);
                }
            }
        }
        Collections.sort(xx);
        Collections.sort(yy);
        int cnt=0;
        int begin=0,end=xx.size()-1;
        while(begin<end){
            cnt+=xx.get(end)-xx.get(begin);
            cnt+=yy.get(end--)-yy.get(begin++);//typo
        }
        return cnt;
    }

}
