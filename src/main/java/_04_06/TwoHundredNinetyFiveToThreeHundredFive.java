package _04_06;

import common.TreeNode;
import common.Tuple;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by tao on 4/6/17.
 */
public class TwoHundredNinetyFiveToThreeHundredFive {


    //297 serialize and deserialize tree

    //298. Binary Tree Longest Consecutive Sequence

    //iterative way and recurisve way
    //自顶向下

    //勉强及格，算是写出来了
    public void dfs(TreeNode root,int val,int[]res,int cnt){
        if(root==null)
            return ;

        if(root.val==val)
            cnt++;
        else
            cnt=1;
        res[0]=Math.max(res[0],cnt);
        dfs(root.left,root.val+1,res,cnt);
        dfs(root.right,root.val+1,res,cnt);

    }
    public int longestConsecutive(TreeNode root) {

        //the first way
//        int []res=new int[1];//global maximum;
//        if(root==null)
//            return 0;
//        dfs(root,root.val,res,0);
//        return res[0];


        if(root==null)
            return 0;
        return dfs(root,root.val,0);
    }
    public int dfs(TreeNode root,int val,int cnt){
        if(root==null)
            return cnt;
        if(root.val==val)
            cnt++;
        else
            cnt=1;
        int m=Math.max(dfs(root.left,root.val+1,cnt),dfs(root.right,root.val+1,cnt));
        return Math.max(m,cnt);
    }

    //更深层次的体验 queue
    //iterative
    public int longestConsecutiveIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res=1;
        Queue<Tuple<TreeNode,Integer>> q=new LinkedList<Tuple<TreeNode, Integer>>();
        q.offer(new Tuple(root,1));
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=((Integer)t.y).intValue();
            res=Math.max(val,res);
            if(node.left!=null){
                int count=node.left.val==node.val+1?val+1:1;
                q.offer(new Tuple(node.left,count));
            }
            if(node.right!=null){
                int count=node.right.val==node.val+1?val+1:1;
                q.offer(new Tuple(node.right,count));
            }
        }
        return res;
    }


    //299 bulls and cows
    public String getHint(String secret, String guess) {
        int[]cnt=new int[10];
        char[]secrets=secret.toCharArray();
        char[]guesses=guess.toCharArray();
        Set<Integer> set=new HashSet<>();//store the bulls index;
        int n=secret.length();
        for(int i=0;i<n;++i){
            if(secrets[i]==guesses[i]){
                set.add(i);
            }else
                cnt[secrets[i]-'0']++;
        }

        int bulls=set.size();
        int cows=0;
        for(int i=0;i<n;++i){
            if(set.contains(i))
                continue;
            if(cnt[guesses[i]-'0']>0){
                cows++;
                cnt[guesses[i]-'0']--;
            }
        }
        return bulls+"A"+cows+"B";
    }

    //remove the set

    public String getHintRemoveSet(String secret, String guess) {
        int[]cnt1=new int[10];
        int[]cnt2=new int[10];
        char[]secrets=secret.toCharArray();
        char[]guesses=guess.toCharArray();
        int n=secret.length();
        int bulls=0;
        int cows=0;
        for(int i=0;i<n;++i){
            if(secrets[i]==guesses[i]){
                bulls++;
            }else{
                cnt1[secrets[i]-'0']++;
                cnt2[guesses[i]-'0']++;
            }
        }

        for(int i=0;i<10;++i){
            cows+=Math.min(cnt1[i],cnt2[i]);
        }
        return bulls+"A"+cows+"B";
    }




    //300 longest increasing sequence
    //dp n^2
    //最大值不一定在最后啊，跪在这里了，不 一定在最后
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int []dp=new int[n+1];
        int res=0;
        for(int i=1;i<=n;++i){
            dp[i]=1;
            for(int j=1;j<i;++j){
                if(nums[i-1]>nums[j-1])
                    dp[i]=Math.max(dp[i],dp[j]+1);
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    //onlog(n) 的解法,非常经典



    //303 range sum query
    //in design
}
