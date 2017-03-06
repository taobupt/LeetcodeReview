package _03_05;




import common.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * Created by tao on 3/5/17.
 */
public class FiftyOneToSixty {



    //这两题和solve suduko,做个比较，希望能到一些经验吧
    //51 NQueen

    public void dfs(List<List<String>>res,int[]ans,int pos){
        if(pos==ans.length){
            List<String>path=new ArrayList<>();
            for(int i=0;i<pos;++i){
                StringBuilder sb=new StringBuilder();
                for(int j=1;j<=pos;++j)
                    sb.append(ans[i]==j?'Q':'.');
                path.add(sb.toString());
            }
            res.add(path);
            return;
        }

        for(int i=1;i<=ans.length;++i){
            if(check(ans,pos,i)){
                ans[pos]=i;
                dfs(res,ans,pos+1);//forget about plus one
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>>res=new ArrayList<>();
        int []ans=new int[n];
        dfs(res,ans,0);
        return res;
    }


    //52 nqueensII
    //backtracking algo
    //actually you can use the min conflict
    //also you can use bit
    public boolean check(int[]res,int pos,int x){
        for(int i=0;i<pos;++i){
            if(res[i]==x ||Math.abs(res[i]-x)==pos-i)
                return false;
        }
        return true;
    }
    public void dfs(int[]res,int []cnt,int pos){
        if(pos==res.length){
            cnt[0]++;
            return;
        }

        for(int i=1;i<=res.length;++i){
            if(check(res,pos,i)){
                res[pos]=i;
                dfs(res,cnt,pos+1);
            }
        }
    }

    public int totalNQueens(int n) {
        int []res=new int[n];
        int []cnt=new int[1];//result;
        dfs(res,cnt,0);
        return cnt[0];
    }

    //53. Maximum Subarray

    //kadane algorithms
    public int maxSubArray(int[] nums){
        int res=Integer.MIN_VALUE;
        int sum1=0,n=nums.length;
        int start=0,end=0,s=0;
        for(int i=0;i<n;++i){
            sum1+=nums[i];
            if(res<sum1){
                res=sum1;
                end=i;
                start=s;
            }
            if(sum1<=0){
                s=i+1;
                sum1=0;
            }
        }
        return res;
    }

    //稍微改下就是求最小值了
    //dynamic programming
    public int maxSubarrayDp(int[] nums){
        int n=nums.length;
        int res=Integer.MIN_VALUE;
        int []dp=new int[n];
        for(int i=0;i<n;++i){
            if(i==0){
                dp[i]=nums[i];
            }else{
                dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            }
            res=Math.max(dp[i],res);
        }
        return res;
    }

    //divide and conquer
    //O(nlogn)
    public int middlesum(int[]nums,int begin,int mid,int end){
        int leftsum=Integer.MIN_VALUE;
        int sum=0;
        for(int i=mid;i>=begin;--i){
            sum+=nums[i];
            if(leftsum<sum)
                leftsum=sum;
        }
        sum=0;
        int rightsum=Integer.MIN_VALUE;
        for(int i=mid+1;i<=end;++i){
            sum+=nums[i];
            if(rightsum<sum)
                rightsum=sum;
        }
        return leftsum+rightsum;
    }
    public int maxSubArray(int []nums,int begin,int end){
        if(begin>end)
            return Integer.MIN_VALUE;
        if(begin==end)
            return nums[begin];
        int mid=(end-begin)/2+begin;
        int leftsum=maxSubArray(nums,begin,mid);//这里要注意是mid，不是mid-1，毕竟只是分成两部分，不是叫你把中间元素扔了
        int rightsum=maxSubArray(nums,mid+1,end);
        int middlesum=middlesum(nums,begin,mid,end);
        return Math.max(leftsum,Math.max(rightsum,middlesum));

    }
    public int maxSubarrayConquer(int[]nums){
        return maxSubArray(nums,0,nums.length-1);
    }

    //divide and conquer
    //O(N);
//    mx (largest sum of this subarray),sums[0]
//    lmx(largest sum starting from the left most element),sums[1]
//    rmx(largest sum ending with the right most element),sums[2]
//    sum(the sum of the total subarray).//sums[3]

    //    mx = max(max(mx1, mx2), rmx1 + lmx2);
//    lmx = max(lmx1, sum1 + lmx2);
//    rmx = max(rmx2, sum2 + rmx1);
//    sum = sum1 + sum2;
    //上述的关系理清楚了就可以了，其实也不是很难，比上一个的divide and conquer 要强不少
    public void conquer(int[]nums,int begin,int end,int[]sums){
        if(begin>end){
            return;
        }
        if(begin==end){
            sums[0]=sums[1]=sums[2]=sums[3]=nums[begin];
            return;
        }

        int mid=(end-begin)/2+begin;
        int []sums1=new int[4];
        Arrays.fill(sums1,Integer.MIN_VALUE);
        conquer(nums,begin,mid,sums1);
        int []sums2=new int[4];
        Arrays.fill(sums2,Integer.MIN_VALUE);
        conquer(nums,mid+1,end,sums2);
        sums[0]=Math.max(Math.max(sums1[0],sums2[0]),sums1[2]+sums2[1]);
        sums[1]=Math.max(sums1[1],sums1[3]+sums2[1]);
        sums[2]=Math.max(sums2[2],sums2[3]+sums1[2]);
        sums[3]=sums1[3]+sums2[3];


    }
    public int maxSubarrayConquer2(int[]nums){
        int []sums=new int[4];
        Arrays.fill(sums,Integer.MIN_VALUE);
        conquer(nums,0,nums.length-1,sums);
        return sums[0];
    }


    //54 spiral matrix
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer>res=new ArrayList<>();
        if(matrix.length==0||matrix[0].length==0)
            return res;
        int rowsBeg=0,rowsEnd=matrix.length-1,colsBeg=0,colsEnd=matrix[0].length-1;
        while(rowsBeg<=rowsEnd && colsBeg<=colsEnd){
            for(int i=colsBeg;i<=colsEnd;++i){
                res.add(matrix[rowsBeg][i]);
            }
            rowsBeg++;
            for(int i=rowsBeg;i<=rowsEnd;++i){
                res.add(matrix[i][colsEnd]);
            }
            colsEnd--;

            if(rowsBeg<=rowsEnd) {//in case of visiting again
                for (int i = colsEnd; i >= colsBeg; --i) {
                    res.add(matrix[rowsEnd][i]);
                }
                rowsEnd--;
            }

            if(colsBeg<=colsEnd) {
                for (int i = rowsEnd; i >= rowsBeg; --i) {
                    res.add(matrix[i][colsBeg]);
                }
                colsBeg++;
            }
        }
        return res;
    }

    //55 jump games
    public boolean canJump(int[] nums) {
        int n=nums.length,maxReach=0;
        for(int i=0;i<n && i<=maxReach && maxReach<n-1;++i){
            maxReach=Math.max(maxReach,nums[i]+i);
        }
        return maxReach>=n-1;
    }


    //another way to solve this, from back to front
    //faster
    public boolean canJumpAnother(int[]nums){
        int n=nums.length,last=n-1;
        for(int i=n-2;i>=0;--i){
            if(nums[i]+i>=last)
                last=i;
        }
        return last<=0;//in case of empty;
    }


    public boolean canJumpTwo(int[]nums){
        int n=nums.length,reachable=0;
        for(int i=0;i<n;++i){
            if(i>reachable)
                return false;
            reachable=Math.max(reachable,nums[i]=i);//in case of nums[i]=0;
        }
        return true;
    }
    //actually you can choose the optimal position every time

    //56 merge interval

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval>res=new ArrayList<>();
        int n=intervals.size();
        if(n==0)
            return res;
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start,o2.start);//
            }
        });
        res.add(intervals.get(0));
        for(int i=1;i<n;++i){
            Interval end=res.get(res.size()-1);//其实也不一定要这么烦，只要有一个变量就可以，可以在else里才加入res，但是这样的话，就得在返回res之前加上这个变量
            if(end.end>=intervals.get(i).start){
                end.end=Math.max(end.end,intervals.get(i).end);//pay attention to this,[2,8] is int he fornt of [2,6],max(end.end,intervals.get(i).end);
                res.set(res.size()-1,end);
            }else
                res.add(intervals.get(i));
        }
        return res;
    }

    //like meeting roomsII ,separate start and end;

    //非常牛逼的做法
    public List<Interval>mergeSeparate(List<Interval>intervals){
        List<Interval>res=new ArrayList<>();
        int n=intervals.size();
        if(n==0)
            return res;
        int []starts=new int[n];
        int []ends=new int[n];
        for(int i=0;i<n;++i){
            starts[i]=intervals.get(i).start;
            ends[i]=intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        for(int i=0,j=0;i<n;++i){
            if(i==n-1||starts[i+1]>ends[i]){//这个条件用的相当巧妙
                res.add(new Interval(starts[j],ends[i]));
                j=i+1;
            }
        }
        return res;
    }

    //57 insert interval
    //可以插进intervals，然后直接调用上一问的解法，时间复杂度提高了
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval>res=new ArrayList<>();
        int n=intervals.size();
        int index=0;
        while(index<n && intervals.get(index).end<newInterval.start)
            res.add(intervals.get(index++));
        while(index<n && newInterval.end>=intervals.get(index).start){
            newInterval.start=Math.min(newInterval.start,intervals.get(index).start);
            newInterval.end=Math.max(newInterval.end,intervals.get(index).end);
            index++;
        }
        res.add(newInterval);
        while(index<n)
            res.add(intervals.get(index++));
        return res;
    }

    //58 length of last word
    //感觉可以作弊
    public int lengthOfLastWord(String s) {
        int n=s.length();
        if(n==0)//如果不想用trim的话，那你就得检测各种corn case
            return 0;
        int index=n-1;
        while(index>=0 && Character.isSpaceChar(s.charAt(index)))//记住万事都要写范围
            index--;
        int end=index;
        while(index>=0){
            if(Character.isSpaceChar(s.charAt(index)))
                break;
            index--;
        }
        return end-index;
        //return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }


    //59 spiral matrix
    public int[][] generateMatrix(int n) {
        int [][]res=new int[n][n];
        int index=1;
        int rowsBeg=0,rowsEnd=n-1,colsBeg=0,colsEnd=n-1;
        while(rowsBeg<=rowsEnd && colsBeg<=colsEnd){
            for(int i=colsBeg;i<=colsEnd;++i){
                res[rowsBeg][i]=index++;
            }
            rowsBeg++;
            for(int i=rowsBeg;i<=rowsEnd;++i){
                res[i][colsEnd]=index++;
            }
            colsEnd--;

            if(rowsBeg<=rowsEnd) {//in case of visiting again
                for (int i = colsEnd; i >= colsBeg; --i) {
                    res[rowsEnd][i]=index++;
                }
                rowsEnd--;
            }

            if(colsBeg<=colsEnd) {
                for (int i = rowsEnd; i >= rowsBeg; --i) {
                    res[i][colsBeg]=index++;
                }
                colsBeg++;
            }
        }
        return res;
    }


    // 60. Permutation Sequence
    //其实就是考到了数学知识罢了。
    public String getPermutation(int n, int k) {
        int []dp=new int[13];//13! is larger than 2**31
        dp[0]=1;
        for(int i=1;i<=12;++i)
            dp[i]=i*dp[i-1];
        StringBuilder sb=new StringBuilder();
        List<Integer>li=new ArrayList<>();
        for(int i=1;i<=n;++i)
            li.add(i);
        int index=n-1;
        while(k>0){
            int ind=k%dp[index]==0?k/dp[index]-1:k/dp[index];
            sb.append(li.get(ind));
            li.remove(ind);
            k-=(k/dp[index])*dp[index];
            index--;
        }
        if(!li.isEmpty()){
            int xx=li.size();
            for(int i=xx-1;i>=0;--i)
                sb.append(li.get(i));
        }
        return sb.toString();
    }

}
