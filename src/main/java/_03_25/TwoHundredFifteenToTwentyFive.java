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
                set.remove((long)nums[i-k]);
        }

        return false;
    }

    //bucket sort
    //非常重要，晚上可以查看下max gap，看如何用bucket map做到的
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

}
