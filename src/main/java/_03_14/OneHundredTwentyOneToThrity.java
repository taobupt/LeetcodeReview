package _03_14;

import common.TreeNode;
import common.Tuple;
import common.UnionFind;

import java.util.*;

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


    //124  binary tree maximum path sum
    //recursive

    //one end point is root;
    public int dfs(TreeNode root,int[]res){
        if(root==null)
            return 0;
        int l=dfs(root.left,res);
        int r=dfs(root.right,res);
        int ans=0;
        ans=Math.max(Math.max(l,r)+root.val,root.val);
        res[0]=Math.max(res[0],Math.max(l+r+root.val,ans));
        return ans;
    }

    public int maxPathSum(TreeNode root) {
        int []res=new int[1];
        dfs(root,res);
        return res[0];
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

    //128 longest consecutive way
    //当然你可以排序再搞，但是复杂度上去了。
    public int longestConsecutive(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int x:nums)
            set.add(x);
        int res=0;
        for(int x:nums){
            if(!set.remove(x))
                continue;
            int after=x+1;
            int before=x-1;
            while(set.contains(after)){
                set.remove(after);
                after++;
            }
            while(set.contains(before)){
                set.remove(before);
                before--;
            }
            res=Math.max(res,after-before-1);
        }
        return res;
    }

    //还可以用weighted union find 来做
    public int longestConsecutiveByUnionFind(int[]nums){
        int n=nums.length;
        if(n==0)
            return 0;
        UnionFind uf=new UnionFind(n);
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<n;++i){
            if(!map.containsKey(nums[i])){
                if(map.containsKey(nums[i]-1)){
                    uf.unionWithWeight(i,map.get(nums[i]-1));
                }
                if(map.containsKey(nums[i]+1))
                    uf.unionWithWeight(i,map.get(nums[i]+1));
                map.put(nums[i],i);
            }
        }
        return uf.hightestRank;
//        int n=nums.length;
//        if(n<=1)
//            return n;
//        Map<Integer,Integer>map=new HashMap<>();
//        UnionFind wuf=new UnionFind(n);
//        for(int i=0;i<n;++i){
//            if(!map.containsKey(nums[i])){
//                if(map.containsKey(nums[i]-1))
//                    wuf.unionWithWeight(i,map.get(nums[i]-1));
//                if(map.containsKey(nums[i]+1))
//                    wuf.unionWithWeight(i,map.get(nums[i]+1));
//                map.put(nums[i],i);
//            }
//        }
//        return wuf.hightestRank;
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

    //130 surround region
    //bfs
    //找到一个就拓展，如果最终没遇到边界情况，就全部变为x
    //也是可以用union find
    //必须一发击中
    public void solve(char[][] board) {
        if(board.length==0||board[0].length==0)
            return;
        int m=board.length,n=board[0].length;
        int []dx={1,-1,0,0};
        int []dy={0,0,1,-1};
        boolean [][]vis=new boolean[m][n];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(board[i][j]=='O' && !vis[i][j]){
                    vis[i][j]=true;
                    Queue<int[]>q1=new LinkedList<>();
                    Queue<int[]>q2=new LinkedList<>();
                    q1.offer(new int[]{i,j});
                    q2.offer(new int[]{i,j});
                    boolean flag=true;
                    while(!q1.isEmpty()){
                        int []pos=q1.poll();
                        for(int k=0;k<4;++k){
                            int xx=pos[0]+dx[k];
                            int yy=pos[1]+dy[k];
                            if(xx<0||xx>=m||yy<0||yy>=n){
                                flag=false;
                                continue;
                            }
                            if(vis[xx][yy]||board[xx][yy]!='O')
                                continue;
                            vis[xx][yy]=true;
                            q1.offer(new int[]{xx,yy});
                            q2.offer(new int[]{xx,yy});
                        }
                    }
                    if(flag){
                        while(!q2.isEmpty()){
                            int []pos=q2.poll();
                            board[pos[0]][pos[1]]='X';
                        }
                    }
                }
            }
        }
    }

    //by union find
    //晚上要好好复习一下
    public void solveByUnionFind(char[][] board){
        if(board.length==0||board[0].length==0)
            return;
        int m=board.length,n=board[0].length;
        UnionFind uf=new UnionFind(m*n);
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                int index=i*m+j;
                if((i==0||i==m-1||j==0||j==n-1) && board[i][j]=='O')
                    uf.mix(index,m*n);
                else if(board[i][j]=='O'){
                    if(board[i-1][j]=='O')
                        uf.mix(index-n,index);
                    if(board[i+1][j]=='O')
                        uf.mix(index+n,index);
                    if(board[i][j-1]=='O')
                        uf.mix(index-1,index);
                    if(board[i][j+1]=='O')
                        uf.mix(index+1,index);
                }
            }
        }
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
                if(board[i][j]=='O' && !uf.connected(i*m+j,m*n))
                    board[i][j]='X';
            }
        }
    }

}
