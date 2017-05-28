package _04_22;

import common.Tuple;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by tao on 5/25/17.
 */
public class FourHundredFiftySixToFourHundredSixtyFive {

    //456 132 pattern 好像非常难做的样子，我的第一次跪了。非常好的一道题
    //单调站的特性，还是非常棒棒的。
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stk=new Stack<>();
        int n=nums.length,s3=Integer.MIN_VALUE;
        for(int i=n-1;i>=0;--i){
            if(nums[i]<s3)
                return true;
            while(!stk.isEmpty() && stk.peek()<nums[i]){
                s3=stk.pop();
            }
            stk.push(nums[i]);
        }
        return false;
    }

    public boolean find132patternFromBegin(int[] nums) {
        //保持min max
        Stack<Tuple<Integer,Integer>>stk=new Stack<>();//tuple.x=min,tuple.y=max;
        for(int n:nums){
            if(stk.isEmpty()||n<stk.peek().x)
                stk.push(new Tuple(n,n));
            else if(n>stk.peek().x){
                Tuple last = stk.pop();
                if(n<(int)last.y)
                    return true;
                while(!stk.isEmpty() && n>=stk.peek().y)
                    stk.pop();
                //n< stk.peek().y;
                if(!stk.isEmpty() && n>stk.peek().x)
                    return true;
                stk.push(new Tuple(last.x,n));

            }
        }
        return false;
    }



    //459 repeating substring pattern
    public boolean repeatedSubstringPattern(String s) {
        int n=s.length();
        for(int i=1;i<=n/2;++i){
            String first = s.substring(0,i);
            int j=i;
            for(;j<=n-i;j+=i){
                if(!s.substring(j,j+i).equals(first))
                    break;
            }
            if(j==n)
                return true;
        }
        return false;
    }

    //concise 抓住了问题的关键，优化了一部分
    public boolean repeatedSubstringPatternConcise(String str) {
        int len = str.length();
        for(int i=len/2 ; i>=1 ; i--) {
            if(len%i == 0) {
                int m = len/i;
                String subS = str.substring(0,i);
                int j;
                for(j=1;j<m;j++) {
                    if(!subS.equals(str.substring(j*i,i+j*i))) break;
                }
                if(j==m)
                    return true;
            }
        }
        return false;
    }

    //460 in design LFU cache



    //461 hamming distance
    public int hammingDistance(int x, int y) {
        int cnt=0;
        for(int i=31;i>=0;--i){
            int mask = 1<<i;
            int a = x&mask;
            int b = y&mask;
            if(a!=b)
                cnt++;
        }
        return cnt;
    }

    //462 Minimum Moves to Equal Array Elements II
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int begin=0,end=nums.length-1;
        int cnt=0;
        while(begin<end){
            cnt+=nums[end]-nums[begin];
            end--;
            begin++;
        }
        return cnt;
    }

    //island perimeter
    public int islandPerimeter(int[][] grid) {
        if(grid.length==0||grid[0].length==0)
            return 0;
        int m=grid.length,n=grid[0].length;
        int cnt=0,num=0;
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(grid[i][j]==1){
                    num++;
                    if(j<n-1 && grid[i][j+1]==1)
                        cnt++;
                    if(i<m-1 && grid[i+1][j]==1)
                        cnt++;
                }
            }
        }
        return 4*num-2*cnt;
    }


}
