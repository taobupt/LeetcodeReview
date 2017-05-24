package _03_25;

import common.TreeNode;

import java.util.*;

/**
 * Created by tao on 3/25/17.
 */
public class TwoHundredFifteenToTwentyFive {



    //213 House Robber II,circle
    //去掉数组，然后可以save space
    //很有意思，就是第一个不能和最后一个同时选中，俩dp，一个是负责取第一个，但不取最后一个，一个是负责取最后一个，但不可以是第一个
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==0)
            return 0;

        int []dp1=new int[n];//负责
        int []dp2=new int[n];
        if(n==1)
            return nums[0];
        for(int i=1;i<n;++i){
            dp1[i]=Math.max(dp1[i-1],nums[i]+(i<=2?0:dp1[i-2]));//废第一个
            dp2[i]=Math.max(dp2[i-1],nums[i-1]+(i<=2?0:dp2[i-2]));
        }
        return Math.max(dp1[n-1],dp2[n-1]);
    }
    //典型的回溯法
    //216
    public void dfs(List<List<Integer>>res,List<Integer>path,int k,int n,int pos){
        if(path.size()==k && n==0){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=pos;i<=9;++i){
            if(n<i)
                break;
            path.add(i);
            dfs(res,path,k,n-i,i+1);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
         List<List<Integer>>res=new ArrayList<>();
         List<Integer>path=new ArrayList<>();
         dfs(res,path,k,n,1);
         return res;
    }
    //217 Contains Duplicate
    //是hashamp
    //排序，然后检测相邻数据o(nlon)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer>set=new HashSet<>();
        for(int x:nums){
            if(!set.add(x))
                return true;
        }
        return false;
    }

    //218 the skyline problem
    public List<int[]>getSkyline(int[][]buildings){
        List<int[]>result=new ArrayList<>();
        List<int[]>height=new ArrayList<>();
        for(int []b:buildings){
            height.add(new int[]{b[0],-b[2]});
            height.add(new int[]{b[1],b[2]});
        }
        Collections.sort(height,(a,b)->{
            if(a[0]!=b[0])
                return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });
        PriorityQueue<Integer>pq=new PriorityQueue<>((a,b)->(b-a));
        pq.offer(0);
        int prev=0;
        for(int[]h:height){
            if(h[1]<0){
                pq.offer(-h[1]);
            }else
                pq.remove(h[1]);//remove need o(n) time, this can be solved by treemap;
            int cur=pq.peek();
            if(prev!=cur){
                result.add(new int[]{h[0],cur});
                prev=cur;
            }
        }
        return result;

    }

    public List<int[]> getSkylineOptimal(int[][] buildings) {
        List<int[]> points = new ArrayList<>();
        for(int [] b : buildings){
            points.add(new int[]{b[0], -b[2]});
            points.add(new int[]{b[1], b[2]});
        }
        Collections.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int [] p1, int [] p2){
                if(p1[0] == p2[0]) return p1[1] -p2[1];
                else return p1[0] - p2[0];
            }
        });
        List<int[]> result = new ArrayList<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();// using TreeMap to archive TreeMultiSet
        treeMap.put(0, 1);//add a sentinel to avoid processing with empty TreeMap
        int prev = 0;
        for(int [] point: points){
            if(point[1]<0) treeMap.put(-point[1], treeMap.getOrDefault(-point[1], 0) + 1);//reach a new rectangle, height count+1
            else {
                treeMap.put(point[1], treeMap.getOrDefault(point[1], 0) - 1);//leave a new rectangle, height count-1
                if(treeMap.get(point[1]) == 0) treeMap.remove(point[1]);
            }
            int cur = treeMap.lastKey();
            if(prev != cur){//find a new skyline strip
                result.add(new int[]{point[0], cur});
                prev = cur;
            }
        }
        return result;
    }
    //219 contains duplicates II
    //hash map and O(nlong)先排序，存的是结构体，存index 和nums[index],按照值排序
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //hashmap 搞定；
        Set<Integer>set=new HashSet<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if(i>=k)
                set.remove(nums[i-k]);
        }
        return false;
    }

    //220 Contains Duplicate III
    //Treeset and bucket sort
    //有window就不需要map了
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k<=0||t<0)
            return false;
        TreeSet<Long>set=new TreeSet<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            Long ceil=set.floor((long)nums[i]+t);
            Long floor=set.ceiling((long)nums[i]-t);
            if((ceil!=null && ceil>=nums[i])||(floor!=null && floor<=nums[i]))
                return true;
            set.add((long)nums[i]);
            if(i>=k)
                set.remove((long)nums[i-k]);//比完才删除啊，这里是index差是k，但是在sliding window里面，k是k个数，index差是k-1
        }

        return false;
    }

    //bucket sort
    //非常重要，晚上可以查看下max gap，看如何用bucket map做到的
    public int maximumGap(int[] nums){
        int n=nums.length;
        if(n<2)
            return 0;
        int min=nums[0],max=nums[0];
        for(int x:nums){
            min=Math.min(min,x);
            max=Math.max(max,x);
        }
        if(min==max)
            return 0;
        int gap=(int)Math.ceil(1.0*(max-min)/(n-1));
        int []bucketMin=new int[n];
        int []bucketMax=new int[n];
        Arrays.fill(bucketMax,Integer.MIN_VALUE);
        Arrays.fill(bucketMin,Integer.MAX_VALUE);

        for(int x:nums){
            int index=(x-min)/gap;
            bucketMin[index]=Math.min(bucketMin[index],x);
            bucketMax[index]=Math.max(bucketMax[index],x);
        }

        for(int i=0;i<n;++i){
            if(bucketMin[i]!=Integer.MAX_VALUE){
                gap=Math.max(gap,bucketMin[i]-min);
                min=bucketMax[i];
            }
        }
        return gap;
    }
    //为什么不考虑k呢，因为是有window控制的
    public boolean containsNearbyAlmostDuplicateBucket(int[] nums, int k, int t){

        if(k<=0||t<0)
            return false;
        Map<Long,Long>map=new HashMap<>();
        int n=nums.length;
        for(int i=0;i<n;++i){
            long remappedNum=(long)nums[i]-Integer.MIN_VALUE;
            long bucket=(remappedNum)/((long)t+1);
            if(map.containsKey(bucket)||(map.containsKey(bucket-1) && remappedNum-map.get(bucket-1)<=t)||(map.containsKey(bucket+1) && map.get(bucket+1)-remappedNum<=t))
                return true;
            map.put(bucket,remappedNum);
            if(i>=k){
                long num=((long)nums[i-k]-Integer.MIN_VALUE);
                map.remove(num/((long)t+1));
            }
        }
        return false;
    }

    //很常规的一道题
    //可以用滚动数组的
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)
            return 0;
        int m=matrix.length;
        int n=matrix[0].length;
        int maxValue=0;

        //处理两条边
        int [][]dp=new int[m][n];
        for(int i=0;i<n;++i){
            dp[0][i]=matrix[0][i]-'0';
            maxValue=Math.max(dp[0][i],maxValue);
        }

        for(int i=0;i<m;++i){
            dp[i][0]=matrix[i][0]-'0';
            maxValue=Math.max(dp[i][0],maxValue);
        }

        for(int i=1;i<m;++i){
            for(int j=1;j<n;++j){
                dp[i][j]=(matrix[i][j]=='0')?0:1+Math.min(dp[i][j-1],Math.min(dp[i-1][j],dp[i-1][j-1]));
                maxValue=Math.max(maxValue,dp[i][j]);
            }
        }
        return maxValue;
    }

    //222 Count Complete Tree Nodes
    //其实应该还有一个种解法，先计算上面的，再计算最后一层。
    public int getLeft(TreeNode root){
        if(root==null)
            return 0;
        int h=0;
        TreeNode node=root;
        while(node!=null){
            h++;
            node=node.left;
        }
        return h;
    }
    public int getRight(TreeNode root){
        if(root==null)
            return 0;
        TreeNode node=root;
        int h=0;
        while(node!=null){
            h++;
            node=node.right;
        }
        return h;
    }
    public int countNodes(TreeNode root) {
        if(root==null)
            return 0;
        //下面就是加速的办法，问题变成，如何判断一棵树是不是完美二叉树。左子树的高度，右边
        int l=getLeft(root);
        int r=getRight(root);
        return l==r?((1<<l)-1):countNodes(root.left)+countNodes(root.right)+1;
    }

    //count last level
    public int countNodesLastLevel(TreeNode root){
        if(root==null)
            return 0;
        if(root.left==null)
            return 1;
        int sum=0,height=0;
        TreeNode cur=root;
        while(cur.left!=null){
            sum+=(1<<height);
            cur=cur.left;
            height++;
        }
        return sum+countlastlevel(root,height);

    }

    public int countlastlevel(TreeNode root,int h){
        if(h==1){
            if(root.right!=null)
                return 2;
            else if(root.left!=null)
                return 1;
            else
                return 0;
        }
        TreeNode mid=root.left;
        int currentHeight=1;
        while(currentHeight<h){
            currentHeight++;
            mid=mid.right;
        }
        if(mid==null)
            return countlastlevel(root.left,h-1);
        else
            return (1<<(h-1))+countlastlevel(root.right,h-1);
    }

    //225  in the design


}
