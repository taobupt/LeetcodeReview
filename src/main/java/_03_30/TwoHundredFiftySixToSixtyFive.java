package _03_30;

import common.TreeNode;
import common.UnionFind;

import java.util.*;

/**
 * Created by tao on 3/30/17.
 */
public class TwoHundredFiftySixToSixtyFive {


    //256 paint house
    //简单的dp
    //还有一种废空间的做法，在此不写了

    //对废空间的变量改进，同时也是不许改变原数组
    public int minCostWithoutChange(int[][]costs){
        int m=costs.length;
        if(m==0)
            return 0;
        int lastR=costs[0][0];
        int lastG=costs[0][1];
        int lastB=costs[0][2];
        for(int i=1;i<m;++i){
            //要用过其他三个变量，不然很容易跪
            int curR=Math.min(lastG,lastB)+costs[i][0];
            int curG=Math.min(lastB,lastR)+costs[i][1];
            int curB=Math.min(lastG,lastR)+costs[i][2];
            //update
            lastR=curR;
            lastB=curB;
            lastG=curG;
        }
        return Math.min(lastB,Math.min(lastG,lastR));
    }
    public int minCost(int[][]costs){
        int m=costs.length;
        if(m==0)
            return 0;
        for(int i=1;i<m;++i){
            costs[i][0]+=Math.min(costs[i-1][1],costs[i-1][2]);
            costs[i][1]+=Math.min(costs[i-1][0],costs[i-1][2]);
            costs[i][2]+=Math.min(costs[i-1][1],costs[i-1][0]);
        }
        return Math.min(costs[m-1][0],Math.min(costs[m-1][1],costs[m-1][2]));
    }
    //257 binary tree paths
    //dfs is the most simple
    //很简单的一道题，我擦，脑袋不转了
    //bfs 也是可以做的
    public void dfs(List<String>res,TreeNode root,List<String>path){
        if(root==null)
            return;
        path.add(""+root.val);
        if(root.left==null && root.right==null){
            res.add(String.join("->",new ArrayList<CharSequence>(path)));
        }
        dfs(res,root.left,path);
        dfs(res,root.right,path);
        path.remove(path.size()-1);

    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String>res=new ArrayList<>();
        List<String>path=new ArrayList<>();
        dfs(res,root,path);
        return res;
    }


    //258 add digits 快慢指针
    //数根，有快速公式
    public int getSum(int num){
        int sum=0;
        while(num>0){
            sum+=(num%10);
            num/=10;
        }
        return sum;
    }
    public int addDigits(int num){
        int fast=getSum(num);
        int slow=num;
        while(slow!=fast){
            fast=getSum(getSum(fast));
            slow=getSum(slow);
        }
        return slow;
    }

    //better
    public int addDigitsBetter(int num){
        //num=a0+a1*10+a2*100+..
        //num%9=a0+a1+a2+a3+a4=res
        //but 9%9=0,is not we want
        //we can solve this by (num-1)%9+1;
        //
        return 1+(num-1)%9;
    }


    //259 3sum smaller
    //O(n2)
    //先排序，然后再two pointers
    //可以尝试加速
    public int threeSumSmaller(int[] nums, int target) {
        int n=nums.length;
        Arrays.sort(nums);
        int res=0;
        for(int i=0;i<n-2;++i){
            int begin=i+1;
            int end=n-1;
            if(nums[i]+nums[i+1]+nums[i+2]>=target)//作为加速的
                break;
            while(begin<end){
                while(begin< end &&nums[begin]+nums[end]+nums[i]>=target)
                    end--;
                res+=end-begin;
                begin++;
            }
        }
        return res;
    }

    //origin way
    public int threeSumSmallerOriginWay(int[]nums,int target){
        int n=nums.length;
        Arrays.sort(nums);
        int cnt=0;
        for(int i=0;i<n-2;++i){
            int left=i+1,right=n-1;
            while(left<right){
                if(nums[i]+nums[left]+nums[right]<target){
                    cnt+=right-left;
                    left++;
                }else{
                    right--;
                }
            }
        }
        return cnt;
    }


    //260 single numnber III
    //其实就是bitmap， 理解x&-x
    //x&-x 就是最后1所在的值
    public int[] singleNumber(int[] nums) {
        int diff=0,n=nums.length;
        int []res=new int[2];
        for(int x:nums)
            diff^=x;
        diff&=(-diff);//(aXorb & (aXorb - 1)) ^ aXorb;
        for(int x:nums){
            if((diff&x)!=0)
                res[0]^=x;
            else
                res[1]^=x;
        }
        return res;
    }


    //261 graph valid tree
    //union find
    //无向图用union find 来检测有没有环
    //有向图来检测有没有环 dfs，white .dark,black
    //改天总结一下
    //非常经典的一道题
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf=new UnionFind(n);
        int m=edges.length;
        for(int []edge:edges){
            if(!uf.mix(edge[0],edge[1]))
                return false;
        }
        return n==m+1;
    }

    //dfs way
    //得和course schedule 比较比较
    public void dfs(int index,boolean[]vis,Map<Integer,List<Integer>>map){
        if(vis[index])
            return;
        vis[index]=true;
        List<Integer>neigtbors=map.getOrDefault(index,new ArrayList<>());
        for(int neighbor:neigtbors){
            dfs(neighbor,vis,map);
        }
    }

    public void buildGraph(int[][]edges,Map<Integer,List<Integer>>map){
        for(int[]edge:edges){
            if(!map.containsKey(edge[0]))
                map.put(edge[0],new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            if(!map.containsKey(edge[1]))
                map.put(edge[1],new ArrayList<>());
            map.get(edge[1]).add(edge[0]);
        }
    }
    public int countComponents(int n,int[][]edges){
        Map<Integer,List<Integer>>map=new HashMap<>();
        buildGraph(edges,map);
        boolean []vis=new boolean[n];
        int cnt=0;
        for(int i=0;i<n;++i){
            if(!vis[i]){
                dfs(i,vis,map);
                cnt++;
            }
        }
        return cnt;
    }
    public boolean validTreeDFSway(int n,int[][]edges){
        int x=countComponents(n,edges);
        return x==1 && n==edges.length+1;
    }


    //263 ugly number
    public boolean isUgly(int num) {
        if(num<=0)
            return false;
        while(num%5==0)
            num/=5;
        while(num%3==0)
            num/=5;
        while(num%2==0)
            num/=2;
        return num==1;
    }

    //short code
    public boolean isUglyShorter(int num){
        if(num<=0)
            return false;
        for(int i=2;i<6;++i){
            while(num%i==0)
                num/=i;
        }
        return num==1;
    }

    //the next is great 263 ugly number II
    public int nthUglyNumber(int n) {
        List<Integer>two=new ArrayList<>();
        List<Integer>three=new ArrayList<>();
        List<Integer>five=new ArrayList<>();
        two.add(1);
        three.add(1);
        five.add(1);
        int cnt=1;
        int a=0,b=0,c=0;
        int num=1;
        while(cnt<n){
            num=Math.min(two.get(a)*2,Math.min(three.get(b)*3,five.get(c)*5));
            cnt++;
            two.add(num);
            three.add(num);
            five.add(num);
            while(num>=2*two.get(a))
                a++;
            while(num>=3*three.get(b))
                b++;
            while(num>=5*five.get(c))
                c++;
        }
        return num;
    }

    //其实一个数组就够了
    public int nthUglyNumberConcise(int n){
        int a=1,b=1,c=1;//注意初始化的值就可以了，否则有你好受的，呜呜呜呜
        int[]ugly=new int[n+1];
        ugly[0]=ugly[1]=1;
        int cnt=1;
        int num=1;
        while(cnt<n){
            num=Math.min(ugly[a]*2,Math.min(ugly[b]*3,ugly[c]*5));
            ugly[++cnt]=num;
            if(ugly[cnt]>=ugly[a]*2)
                a++;
            if(ugly[cnt]>=ugly[b]*3)
                b++;
            if(ugly[cnt]>=ugly[c]*5)
                c++;
        }
        return ugly[n];
    }

    //265 paint houseII
    //这道题其实leetcode放松了，lintcode比较紧，暴力也能过
    //暴力是nk^2
    //要求做到nk
    //brute force
    public int minCostII(int[][] costs) {
        if(costs.length==0||costs[0].length==0)
            return 0;
        int n= costs.length;
        int k=costs[0].length;
        for(int i=1;i<n;++i){
            for(int j=0;j<k;++j){
                int minValue=Integer.MAX_VALUE;
                for(int m=0;m<k;++m){
                    if(m==j)
                        continue;
                    minValue=Math.min(minValue,costs[i-1][m]);
                }
                costs[i][j]+=minValue;
            }
        }
        int res=Integer.MAX_VALUE;
        for(int i=0;i<k;++i)
            res=Math.min(costs[n-1][i],res);
        return res;
    }

    //o(2nk),最小值设法求一次即可
    //次小值，最小值，如果和最小值相等，那么就赋值次小值
    public int minCostIIBetter(int[][]costs){
        if(costs.length==0||costs[0].length==0)
            return 0;
        int n=costs.length;
        int k=costs[0].length;
        for(int i=1;i<n;++i){
            //求最小值和次小值；
            int minValue=Integer.MAX_VALUE,secMin=Integer.MIN_VALUE;
            for(int j=0;j<k;++j){
                if(costs[i-1][j]<minValue){
                    secMin=minValue;
                    minValue=costs[i-1][j];
                }else if(costs[i-1][j]<secMin)
                    secMin=costs[i-1][j];
            }

            for(int j=0;j<k;++j){
                costs[i][j]+=(costs[i-1][j]==minValue?secMin:minValue);
            }
        }

        int res=Integer.MAX_VALUE;
        for(int i=0;i<k;++i)
            res=Math.min(costs[n-1][i],res);
        return res;
    }

    //most concise
    public int minCostIIMostConcise(int[][]costs){
        if (costs == null || costs.length == 0) return 0;

        int n = costs.length, k = costs[0].length;
        // min1 is the index of the 1st-smallest cost till previous house
        // min2 is the index of the 2nd-smallest cost till previous house
        int min1 = -1, min2 = -1;

        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;

            for (int j = 0; j < k; j++) {
                if (j != last1) {
                    // current color j is different to last min1
                    costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
                } else {
                    costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
                }

                // find the indices of 1st and 2nd smallest cost of painting current house i
                if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                    min2 = min1; min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];
    }









}
