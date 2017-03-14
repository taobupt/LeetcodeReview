package _03_13;

import common.Tree;
import common.TreeLinkNode;
import common.TreeNode;
import common.Tuple;

import java.util.*;

/**
 * Created by tao on 3/13/17.
 */
public class OneHundredTenToOneHundredTwenty {

    //111 minimum depth of binary tree
    //recursive way
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;
        if(root.left==null)
            return 1+minDepth(root.right);
        if(root.right==null)
            return 1+minDepth(root.left);
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }

    //iterative way
    public int minDepthIterative(TreeNode root){
        if(root==null)
            return 0;
        Queue<Tuple<TreeNode,Integer>>q=new LinkedList<>();
        q.offer(new Tuple<>(root,1));
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=(Integer)t.y;
            if(node.left==null && node.right==null)
                return val;
            if(node.left!=null)
                q.offer(new Tuple(node.left,val+1));
            if(node.right!=null)
                q.offer(new Tuple(node.right,val+1));
        }
        return 0;
    }

    //112 path sum
    //recursive way
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;
        if(root.left==null && root.right==null && root.val==sum)
            return true;
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }
    //iterative way
    public boolean hasPathsumIterative(TreeNode root,int sum){
        if(root==null)
            return false;
        Queue<Tuple<TreeNode,Integer>>q=new LinkedList<>();
        Map<TreeNode,TreeNode>map=new HashMap<>();
        List<List<Integer>>res=new LinkedList<>();
        q.offer(new Tuple(root,root.val));
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=(Integer)t.y;
            if(node.left==null && node.right==null && val==sum){
                List<Integer>path=new LinkedList<>();
                path.add(0,node.val);
                while(map.containsKey(node)){
                    node=map.get(node);
                    path.add(0,node.val);
                }
                res.add(path);
                return true;
            }
            if(node.left!=null){
                map.put(node.left,node);
                q.offer(new Tuple(node.left,node.left.val+val));
            }
            if(node.right!=null){
                map.put(node.right,node);
                q.offer(new Tuple(node.right,node.right.val+val));
            }
        }
        return false;
    }

    //113 path sum

    //recursive way

    public void dfs(TreeNode root, int sum, List<List<Integer>>res, List<Integer>path){
        if(root==null)
            return;
        if(sum==root.val && root.left==null && root.right==null){//判断叶子结点
            path.add(root.val);
            res.add(new ArrayList<>(path));
            path.remove(path.size()-1);//这句话可以去掉，把path.add(root.val)提到前面取，把return去掉，这样保证能及时吐出来
            return;
        }
        path.add(root.val);
        dfs(root.left,sum-root.val,res,path);
        dfs(root.right,sum-root.val,res,path);
        path.remove(path.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>>res=new ArrayList<>();
        if(root==null)
            return res;
        dfs(root,sum,res,new ArrayList<>());
        return res;
    }

    //iterative way
    //每个节点保存一从上到这个节点的路径，太耗空间了
    //用hashmap存父节点
    //postorder 来搞事
    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>>res=new LinkedList<>();
        if(root==null)
            return res;
        Queue<Tuple<TreeNode,Integer>>q=new LinkedList<>();
        Map<TreeNode,TreeNode>map=new HashMap<>();

        q.offer(new Tuple(root,root.val));
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int val=(Integer)t.y;
            if(node.left==null && node.right==null && val==sum){
                List<Integer>path=new LinkedList<>();
                path.add(0,node.val);
                while(map.containsKey(node)){
                    node=map.get(node);
                    path.add(0,node.val);
                }
                res.add(path);
                continue;// 这一点至关重要，少了这点就会崩溃
            }
            if(node.left!=null){
                map.put(node.left,node);
                q.offer(new Tuple(node.left,node.left.val+val));
            }
            if(node.right!=null){
                map.put(node.right,node);
                q.offer(new Tuple(node.right,node.right.val+val));
            }
        }
        return res;
    }


     //114 flatten binary tree to linkedlist
        //recursive way and iterative way
    TreeNode prev=null;
     public void flatten(TreeNode root) {
         if(root==null)
             return;
         flatten(root.right);
         flatten(root.left);
         root.left=null;
         root.right=prev;
         prev=root;
     }

     //iterative way
    public void flattenIterative(TreeNode root){
         if(root==null)
             return;
         TreeNode cur=root;
         while(cur!=null){
             TreeNode node=cur.left;
             while(node!=null && node.right!=null)
                 node=node.right;
             if(node!=null){
                 node.right=cur.right;
                cur.right=cur.left;
             }
             cur.left=null;
             cur=cur.right;
         }
    }

    //115 distinct subsequence
    public int numDistinct(String s, String t) {
        int m=s.length(),n=t.length();
        if(m<n)
            return 0;
        int[][]dp=new int[m+1][n+1];

        for(int i=0;i<=m;++i)
            dp[i][0]=1;
        for(int i=1;i<=m;++i){
            for(int j=1;j<=n;++j){
                dp[i][j]=dp[i-1][j]+(s.charAt(i-1)==t.charAt(j-1)?dp[i-1][j-1]:0);
            }
        }
        return dp[m][n];
    }

    //116 Populating Next Right Pointers in Each Node
    //You may assume that it is a perfect binary tree

    //without const space
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        Queue<TreeLinkNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            while(size-- >0){
                TreeLinkNode node=q.poll();
                node.next=size==0?null:q.peek();
                if(node.left!=null)
                    q.offer(node.left);
                if(node.right!=null)
                    q.offer(node.right);
            }
        }
    }

    //constant space
    public void connectWithoutExtraSpace(TreeLinkNode root){
        if(root==null)
            return;
        TreeLinkNode cur=root;
        while(cur!=null){
            TreeLinkNode node=cur;
            while(node!=null &&node.left!=null){
                node.left.next=node.right;
                if(node.next!=null)
                    node.right.next=node.next.left;
                node=node.next;
            }
            cur=cur.left;
        }
    }

    //117 not perfect tree
    public void connectWithoutExtraSpaceAndPerfect(TreeLinkNode root){
        //hard
        TreeLinkNode newHead=new TreeLinkNode(0);
        newHead.next=root;
        while(newHead.next!=null){
            TreeLinkNode tail=newHead;
            TreeLinkNode n=newHead.next;
            newHead.next=null;//move to the next level;
            for(;n!=null;n=n.next){
                if(n.left!=null){
                    tail.next=n.left;
                    tail=tail.next;
                }
                if (n.right != null){
                    tail.next=n.right;
                    tail=tail.next;
                }
            }
        }
    }

    //118 pascal'triangle
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>>res=new ArrayList<>();
        if(numRows<1)
            return res;
        List<Integer>path=new ArrayList<>();
        for(int i=0;i<numRows;++i){
            path.clear();
            for(int j=-1;j<i;++j){
                if(j==-1||j==i-1)
                    path.add(1);
                else
                    path.add(res.get(i-1).get(j)+res.get(i-1).get(j+1));
            }
            res.add(new ArrayList<>(path));
        }
        return res;
    }

    //119 pascal's triangle
    public List<Integer> getRow(int rowIndex) {
        List<Integer>res=new ArrayList<>();
        for(int i=0;i<=rowIndex;++i){
            List<Integer>path=new ArrayList<>(res);
            res.clear();
            for(int j=-1;j<i;++j) {
                if (j == -1 || j == i - 1)
                    res.add(1);
                else
                    res.add(path.get(j) + path.get(j + 1));
            }
        }
        return res;
    }

    //save space
    public List<Integer> getRowSaveSpace(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i <= rowIndex; ++i) {
            res.add(0, 1);
            for (int j = 1; j < res.size() - 1; ++j)
                res.set(j, res.get(j) + res.get(j + 1));
        }
        return res;
    }


    //120 Triangle
    //modify the triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)
            return 0;
        int m=triangle.size();
        for(int i=m-2;i>=0;--i){
            for(int j=0;j<=i;++j){
                int val=Math.min(triangle.get(i+1).get(j),triangle.get(i+1).get(j+1))+triangle.get(i).get(j);
                triangle.get(i).set(j,val);
            }
        }
        return triangle.get(0).get(0);
    }

    //modify is not allowed
    //perfect
    public int mininumTotalNOTmodify(List<List<Integer>> triangle){
        if(triangle.size()==0)
            return 0;
        int m=triangle.size();
        int []res=new int[m];
        for(int i=0;i<m;++i)
            res[i]=triangle.get(m-1).get(i);
        for(int i=m-2;i>=0;--i){
            for(int j=0;j<=i;++j){
                res[j]=Math.min(res[j],res[j+1])+triangle.get(i).get(j);
            }
        }
        return res[0];
    }
}
