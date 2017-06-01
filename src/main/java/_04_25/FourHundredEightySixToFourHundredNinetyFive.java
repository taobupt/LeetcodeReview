package _04_25;

import common.FenwickTree;
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

    //490 the maze
    //就是过了，就是nb
    public boolean dfs(int[][]maze,int[]dx,int []dy,int []start,int []destination,boolean[][][]vis){
        if(start[0]==destination[0] && destination[1]==start[1])
            return true;

        vis[start[0]][start[1]][0]=true;
        vis[start[0]][start[1]][1]=true;
        for(int k=0;k<4;++k){
            int xx =start[0]+dx[k];
            int yy = start[1]+dy[k];
            if(xx>=maze.length ||xx<0||yy>=maze[0].length||yy<0||vis[xx][yy][k/2]||maze[xx][yy]==1)
                continue;
            //沿着这条直线走到终点；
            vis[xx][yy][k/2]=true;
            while(!(xx+dx[k]>=maze.length ||xx+dx[k]<0||yy+dy[k]>=maze[0].length||yy+dy[k]<0||vis[xx+dx[k]][yy+dy[k]][k/2]||maze[xx+dx[k]][yy+dy[k]]==1)){
                xx+=dx[k];
                yy+=dy[k];
                vis[xx][yy][k/2]=true;//简直是神奇，毕竟标记一个点走没走过，跟那个方向是有关的,用bfs就不用管了。卧槽
            }
            if(dfs(maze,dx,dy,new int[]{xx,yy},destination,vis))
                return true;
        }
        return false;
    }
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze.length==0||maze[0].length==0)
            return false;
        int m=maze.length,n=maze[0].length;
        int cnt=0;
        //check how many walls in destination
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        //dfs
        boolean [][][]vis=new boolean[m][n][2];
        return dfs(maze,dx,dy,start,destination,vis);
    }

    //491 increasing subsequences

    public void dfs(Set<List<Integer>>res,int[]nums,int index,int length,List<Integer>path){
        if(length==path.size()){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int ind=index;ind<nums.length;++ind){
            if(path.isEmpty()||nums[ind]>=path.get(path.size()-1)){
                path.add(nums[ind]);
                dfs(res,nums,ind+1,length,path);
                path.remove(path.size()-1);
            }
        }
    }
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>>res =new HashSet<>();
        int n=nums.length;
        for(int length=2;length<=n;++length){
            dfs(res,nums,0,length,new ArrayList<Integer>());
        }
        List<List<Integer>>ans=new ArrayList<>();
        ans.addAll(res);
        return ans;
    }

    //也可以这样，卧槽，其实可以这样的，达到很好的加速效果
    /*
    public void dfs(Set<List<Integer>>res,int[]nums,int index,List<Integer>path){
        if(path.size()>=2){
            res.add(new ArrayList<>(path));
           // return;
        }
        for(int ind=index;ind<nums.length;++ind){
            if(path.isEmpty()||nums[ind]>=path.get(path.size()-1)){
                path.add(nums[ind]);
                dfs(res,nums,ind+1,path);
                path.remove(path.size()-1);
            }
        }
    }
     */


    //492 Construct the Rectangle
    //2 这个测试例子没过
    public int[] constructRectangle(int area) {
        int L =(int)Math.sqrt(area);
        if(L*L==area){
            return new int[]{L,L};
        }else{
            for(int l=L;l<=area;++l){
                if(area%l==0){
                    l = Math.max(l,area/l);
                    int width =Math.min(l,area/l);
                    return new int[]{l,width};
                }
            }
        }
        return new int[2];

        /*
        int w = (int)Math.sqrt(area);
	while (area%w!=0) w--;
	return new int[]{area/w, w};
         */
    }


    //493 reverse pair, 经典之作

    int reverseNum=0;
    public void merge(int []nums,int begin,int mid,int end){
//        int []res =nums.clone();
//        int ind=begin,i=begin,j=mid+1;
//        while(i<=mid && j<=end){
//            if(nums[i]<=nums[j]){
//                res[ind++]=nums[i++];
//            }else{
//                res[ind++]=nums[j++];
//            }
//        }
//        while(i<=mid){
//            res[ind++]=nums[i++];
//        }
//        while(j<=end){
//            res[ind++]=nums[j++];
//        }
//        for(int kk=begin;kk<ind;++kk)
//            nums[kk]=res[kk];

        int ind=begin,i=begin,j=mid+1;
        for(;i<=mid;++i){
            while(j<=end && nums[j]<nums[i])
                j++;
            reverseNum+=(j-mid-1);
        }


        //下面这种做法是错的，你j动的时候i不懂，那么1+2+3+4。。。。

//        while(i<=mid && j<=end){
//            if(nums[i]>nums[j]){
//                reverseNum+=j-mid;
//                j++;
//            }else
//                i++;
//        }
//        i++;
//        while(i<=mid){
//            if(nums[i]>nums[end])
//                reverseNum+=end-mid;
//            i++;
//        }
        Arrays.sort(nums,begin,end+1);
    }
    public void merge(int nums[],int start,int end){
        if(start>=end)
            return;
        int mid = (end-start)/2+start;
        merge(nums,start,mid);
        merge(nums,mid+1,end);
        merge(nums,start,mid,end);

    }

    //Fenwick tree to calculate the number
    public int reversePairs(int[] nums) {
        int reverseNumbers=0;
        int n=nums.length;
        FenwickTree tree =new FenwickTree(n);
        //先离散化
        int []res=nums.clone();
        Arrays.sort(res);
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i){
            map.put(res[i],i+1);
        }

        for(int j=n-1;j>=0;--j){
            reverseNumbers+=tree.sum(map.get(nums[j])-1);
            tree.add(map.get(nums[j]),1);//add 只能从上往下加，不能从下往上加，当然你可以修改code
        }
        return reverseNumbers;
    }

    //494 target sum

    public int recursive(int []nums,int start,int end,int sum){
        if(start>end)
            return 0;
        if(start==end){
            if(nums[start]==sum && sum==0)
                return 2;
            else if(nums[start]==sum||sum==-nums[start])
                return 1;
            else
                return 0;
        }
        return recursive(nums,start+1,end,sum+nums[start])+recursive(nums,start+1,end,sum-nums[start]);
    }
    public int findTargetSumWays(int[] nums, int S) {
        return recursive(nums,0,nums.length-1,S);
    }

    // 居然转换成了背包来做，真是瞎了眼了。好强
    /*
    sum(P) - sum(N) = target
    sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    2 * sum(P) = target + sum(nums)
     */

    public int subsetSum(int[]nums,int target){
        int []dp=new int[target+1];
        for(int x:nums){
            for(int v = target;v>=x;--v)
                dp[v]+=dp[v-x];
        }
        return dp[target];
    }
    public int findTargetSumWaysConcise(int[] nums, int S){
        int sum =0;
        for(int x:nums)
            sum+=x;
        return (sum+S)%2>0||S>sum?0:subsetSum(nums,(S+sum)>>>1);
    }


    //495 temmo attacking 就是一个合并区间的过程,合并区间居然忘了，正确的做法应该是直接把第一
    // 个扔进数组，然后不停的比较数组最后一个和最新的interval，但是没想那些也是做出来了，不要想着背，要想着如何舒服的做出来
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n=timeSeries.length;
        if(n==0)
            return 0;
        List<Interval>times=new ArrayList<>();
        for(int x:timeSeries){
            times.add(new Interval(x,x+duration-1));
        }
        Interval first = times.get(0);
        List<Interval>res =new ArrayList<>();
        int nn=times.size();
        for(int i=1;i<nn;++i){
            if(times.get(i).start<=first.end){
                first.end=Math.max(first.end,times.get(i).end);
            }else{
                res.add(first);
                first= times.get(i);
            }
        }
        res.add(first);
        int ret =0;
        for(Interval interval:res){
            ret+=interval.end-interval.start+1;
        }
        return ret;
    }

    //简化的话，你就是不需要区间，直接用一个start和end来表示就可以了



}
