package _04_07;

import common.FenwickTree;
import common.TreeNode;
import common.Tuple;

import java.util.*;

/**
 * Created by tao on 4/6/17.
 */
public class ThreeHundredSixToFifteen {


    //306 backtracking
    public boolean backtrack(String num,long first,long second,int index){
        if(index==num.length())
            return true;
        for(int i=index+1;i<=num.length();++i){
            long third=Long.parseLong(num.substring(index,i));
            if(i-index>1 && num.charAt(index)=='0')
                break;
            if(third==first+second && backtrack(num,second,third,i))
                return true;
            if(third>first+second)
                break;
        }
        return false;
    }
    public boolean isAdditiveNumber(String num) {
        int n=num.length();
        if(n<3)
            return false;
        for(int i=1;i<=n/2;++i){//注意第二个数有可能比第一个数小
            long first=Long.parseLong(num.substring(0,i));
            if(i>1 && num.charAt(0)=='0')//注意检测lead 0
                break;
            for(int j=i+1;j<=2*n/3;++j){//不是2*n/3+1，不然过不了"111"
                long second=Long.parseLong(num.substring(i,j));
                if(j-i>1 && num.charAt(i)=='0')
                    break;
                if(backtrack(num,first,second,j))
                    return true;
            }
        }
        return false;
    }

    //307 range sum query -mutable in design
    //可以试试用segement tree做做

    //308 range sum query 2d-mutable 其实就是二维的fenwick tree


    //309 best time to buy stock and sell stock with cool down
    //两个数组
    public int maxProfit(int[] prices) {
        int n=prices.length;
        if(n<2)
            return 0;
        int []buy=new int[n];//the profit when buy
        int []sell=new int[n];//the profit when sell
        buy[0]=-prices[0];
        //sell[0]=prices[0];
        for(int i=1;i<n;++i){
            buy[i]=Math.max(buy[i-1],(i<2?0:sell[i-2])-prices[i]);
            sell[i]=Math.max(sell[i-1],buy[i-1]+prices[i]);
        }
        return Math.max(sell[n-1],buy[n-1]);
    }

    //save space
    public int maxProfitSaveSpace(int[] prices) {
        int n=prices.length;
        if(n<2)
            return 0;
        int buy=-prices[0];
        int sell=0;
        int beforeSell=0;
        for(int i=1;i<n;++i){
            int lastbuy=buy;
            buy=Math.max(buy,beforeSell-prices[i]);
            beforeSell=sell;
            sell=Math.max(sell,lastbuy+prices[i]);
        }
        return Math.max(sell,buy);
    }


    //310 minimum height tree topologicial sort
    //最多俩个,每次减去叶子节点，晚上复习一下其他的解法
    //tag
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>>adj=new ArrayList<>();
        for(int i=0;i<n;++i)
            adj.add(new ArrayList<>());
        int[]degree=new int[n];
        for(int[]edge:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
            ++degree[edge[1]];
            ++degree[edge[0]];
        }
        Queue<Integer>q=new LinkedList<>();
        for(int i=0;i<n;++i){
            if(degree[i]<=1)
                q.offer(i);
        }
        int ans=0;
        Set<Integer>set=new HashSet<>();//visited.
        Set<Integer>res=new HashSet<>();
        boolean canAdd=false;
        while(!q.isEmpty()){
            if(ans>=n-2)
                canAdd=true;
            int size=q.size();
            for(int i=0;i<size;++i){
                int top=q.poll();
                ans++;
                if(canAdd)
                    res.add(top);
                set.add(top);
                List<Integer>neighbors=adj.get(top);
                for(int x:neighbors){
                    --degree[x];
                    if(degree[x]<=1 && !set.contains(x)){
                        q.offer(x);
                        set.add(x);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    //311. Sparse Matrix Multiplication
    //有普通做法，就是一行乘以一列的
    //这个对于c而言，并不是一步到位的，而是分步达到的。
    //better way
    //对于矩阵来说，随便乘都是可以的，只要你保证c[i][j]+=A[I][M]*B[M][J]
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length==0||A[0].length==0||B[0].length==0)
            return new int[0][0];
        int m=A.length,n=A[0].length,k=B[0].length;
        //A is m*n B is n*k
        //C is m*k;
        int [][]C=new int[m][k];
        for(int x=0;x<m;++x){
            for(int y=0;y<n;++y){
                if(A[x][y]==0)
                    continue;
                for(int z=0;z<k;++z){
                    C[x][z]+=A[x][y]*B[y][z];
                }
            }
        }
        return C;
    }

    //common way 就是调换了下循环的顺序，但是这样就不能判断A[I][J]


    //313
    //priority queue, 每次都是找那个最小的
    //O( log(k)N )
    //tag 晚上可以复习一下
    public int nthSuperUglyNumber(int n, int[] primes) {
        int []res=new int[n+1];
        res[1]=1;
        int[]index=new int[primes.length];
        Arrays.fill(index,1);
        int nn=primes.length;
        int cnt=1;
        while(cnt<n){//所有的都要注意，不要随便==n
            int minValue=Integer.MAX_VALUE;
            for(int i=0;i<nn;++i){
                minValue=Math.min(res[index[i]]*primes[i],minValue);
            }
            res[++cnt]=minValue;
            for(int i=0;i<nn;++i){
                if(res[cnt]>=primes[i]*res[index[i]])
                    ++index[i];
            }
        }
        return res[n];
    }


    //314 hashmap 搞定
    //the result has the same List<Integer> elements but in the wrong order.
    public void dfs(TreeNode root,int level,Map<Integer,List<Integer>>map){
        if(root==null)
            return;
        if(!map.containsKey(level))
            map.put(level,new ArrayList<>());
        map.get(level).add(root.val);
        dfs(root.left,level-1,map);
        dfs(root.right,level+1,map);
    }
    public List<List<Integer>> verticalOrder(TreeNode root) {
       Map<Integer,List<Integer>>map=new HashMap<>();
        dfs(root,0,map);
       List<List<Integer>>res=new ArrayList<>();
       List<Integer>index=new ArrayList<>(map.keySet());
       Collections.sort(index);
       for(int x:index)
           res.add(map.get(x));
       return res;
    }


    //bfs
    public List<List<Integer>> verticalOrderBFS(TreeNode root) {
        Map<Integer,List<Integer>>map=new HashMap<>();
        int minLevel=Integer.MAX_VALUE,maxLevel=Integer.MIN_VALUE;
        Queue<Tuple<TreeNode,Integer>>q=new LinkedList<>();
        List<List<Integer>>res=new ArrayList<>();
        if(root==null)
            return res;
        q.offer(new Tuple<>(root,0));
        while(!q.isEmpty()){
            Tuple top=q.poll();
            TreeNode node=(TreeNode) top.x;
            int level=(Integer)top.y;
            minLevel=Math.min(minLevel,level);
            maxLevel=Math.max(maxLevel,level);
            if(!map.containsKey(level))
                map.put(level,new ArrayList<>());
            map.get(level).add(node.val);
            if(node.left!=null){
                q.offer(new Tuple(node.left,level-1));
            }
            if(node.right!=null){
                q.offer(new Tuple(node.right,level+1));
            }
        }
        for(int i=minLevel;i<=maxLevel;++i){
            res.add(map.get(i));
        }
        return res;

    }

    //也可以先得到range,省得用hashmap
    //double linkedlist
    //双链表，只是val是一个vector
    public void getRange(TreeNode root,int[]width,int level){
        if(root==null)
            return;
        width[0]=Math.min(width[0],level);
        width[1]=Math.max(width[1],level);
        getRange(root.left,width,level-1);
        getRange(root.right,width,level+1);
    }



    //315 count of smaller number after self
    //merge sort and fenwick tree
    public List<Integer> countSmaller(int[] nums) {
        int n=nums.length;
        FenwickTree tree=new FenwickTree(n);
        int[]arr=nums.clone();
        Arrays.sort(arr);
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i)
            map.put(arr[i],i+1);
        List<Integer>res=new ArrayList<>();
        for(int i=n-1;i>=0;--i){
            res.add(tree.sum(map.get(nums[i])-1));//这里跪了，忘了-1，你应该和reverse pair做一下,统计比他小的啊，这不是很正常吗
            tree.add(map.get(nums[i]),1);
        }
        Collections.reverse(res);
        return res;
    }

    //merge sort way

    int []ans=null;
    public void merge(List<Tuple<Integer,Integer>>tuples,int begin,int mid,int end){

        int i=begin,j=mid+1,rightCount=0;
        List<Tuple<Integer,Integer>>copy=new ArrayList<>();
        while(i<=mid && j<=end){
            if(tuples.get(i).x>tuples.get(j).x){
                copy.add(tuples.get(j++));
                //ans[tuples.get(i).y]+=mid-i+1;
            }else{
                ans[tuples.get(i).y]+=j-mid-1;
                copy.add(tuples.get(i++));
            }
        }

        while(i<=mid){
            ans[tuples.get(i).y]+=end-mid;
            copy.add(tuples.get(i++));

        }
        while(j<=end)
            copy.add(tuples.get(j++));

        int n=copy.size();
        for(int ii=0;ii<n;++ii){
            tuples.set(begin+ii,copy.get(ii));
        }
    }
    public void mergeSort(List<Tuple<Integer,Integer>>tuples,int begin,int end){
        if(begin>=end)
            return;
        int mid=(end-begin)/2+begin;
        mergeSort(tuples,begin,mid);
        mergeSort(tuples,mid+1,end);
        merge(tuples,begin,mid,end);
    }
    public List<Integer> countSmallerMergeSort(int[] nums) {
        List<Tuple<Integer,Integer>>tuples=new ArrayList<>();
        int n=nums.length;
        for(int i=0;i<n;++i)
            tuples.add(new Tuple<>(nums[i],i));
        ans=new int[n];
        mergeSort(tuples,0,n-1);
        List<Integer>anss=new ArrayList<>();
        for(int x:ans)
            anss.add(x);
        return anss;
    }




}
