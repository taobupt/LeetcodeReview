package _03_14;

import common.TreeNode;
import common.Tuple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tao on 3/14/17.
 */
public class OneHundredTwentyOneToThrity {

    //121 best time to buy stock
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int maxPro=0,minPrice=0;
        for(int i=0;i<n;++i){
            minPrice=(i==0?prices[0]:Math.min(minPrice,prices[i]));
            maxPro=Math.max(maxPro,prices[i]-minPrice);
        }
        return maxPro;
    }

    //122 best time to buy and stock, as many as possible

    //d-a=(d-c)+(c-b)+(b-a)
    public int maxProfitII(int[] prices) {
        int res=0,n=prices.length;
        for(int i=1;i<n;++i){
            if(prices[i]>prices[i-1])
                res+=prices[i]-prices[i-1];
        }
        return res;
    }

    //another solution, find the local minimum
    //find the local maximum,and sum them up
    public int maxProfitII2(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            // find next local minimum
            while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
            int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
            // find next local maximum
            while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }


    //125 valid palindrome string
    public boolean isPalindrome(String s) {
        int n=s.length();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;++i)
            if(Character.isLetterOrDigit(s.charAt(i)))
                sb.append(Character.toLowerCase(s.charAt(i)));
        n=sb.length();
        for(int i=0;i<n/2;++i){
            if(sb.charAt(i)!=sb.charAt(n-i-1))
                return false;
        }
        return true;
    }

    //直接in place 干
    public boolean isPalindromeInPlace(String s){
        char[]ss=s.toCharArray();
        int n=ss.length;
        int start=0,end=n-1;
        while(start<end){
            while(start<end && !Character.isLetterOrDigit(ss[start]))//注意前面的条件，否则很容易跪啊
                start++;
            while(start<end && !Character.isLetterOrDigit(ss[end]))
                end--;
            if(start<end && Character.toLowerCase(ss[start++])!=Character.toLowerCase(ss[end--]))
                return false;
        }
        return true;
    }

    //129 sum root to leaf
    //recursive way

    public void dfs(TreeNode root,int[]res,int sum){
        if(root==null)
            return;
        if(root.left==null && root.right==null){
            res[0]+=10*sum+root.val;
            return;
        }

        dfs(root.left,res,10*sum+root.val);
        dfs(root.right,res,10*sum+root.val);
    }

    public int sumNumbers(TreeNode root) {
        int []res=new int[1];
        dfs(root,res,0);
        return res[0];
    }

    //iterative way, use queue,store the <TreeNode,val>;
    public int sumNumbersIterative(TreeNode root){
        if(root==null)
            return 0;
        Queue<Tuple<TreeNode,Integer>> q=new LinkedList<>();
        q.offer(new Tuple(root,root.val));
        int ans=0;
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=(Integer)t.y;
            if(node.left==null && node.right==null){
                ans+=val;
                continue;
            }
            if(node.left!=null)
                q.offer(new Tuple(node.left,10*val+node.left.val));
            if(node.right!=null)
                q.offer(new Tuple(node.right,10*val+node.right.val));
        }
        return ans;
    }
}
