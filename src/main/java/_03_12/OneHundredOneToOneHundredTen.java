package _03_12;


import common.ListNode;
import common.TreeNode;
import common.Tuple;
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 3/12/17.
 */
public class OneHundredOneToOneHundredTen {

    //101 iterative way and recursive way

    public boolean isSame(TreeNode p,TreeNode q){
        if(p==null||q==null)
            return p==q;
        return (p.val==q.val) && isSame(p.left,q.right) && isSame(p.right,q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null||(root.left==null && root.right==null))
            return true;
        return isSame(root.left,root.right);
    }

    //iterative way
    public boolean isSymmetricByQueue(TreeNode root){
        //queue
        if(root==null||(root.left==null && root.right==null))
            return true;
        Queue<TreeNode>q=new LinkedList<>();
        if(root.left!=null)
            q.offer(root.left);
        if(root.right!=null)
            q.offer(root.right);
        while(!q.isEmpty()){
            if((q.size()&0x1)!=0)
                return false;
            TreeNode top1=q.poll();
            TreeNode top2=q.poll();
            if(top1.val!=top2.val)
                return false;
            if(top1.left!=null)
                q.offer(top1.left);
            if(top2.right!=null)
                q.offer(top2.right);
            if((q.size()&0x1)!=0)
                return false;
            if(top1.right!=null)
                q.offer(top1.right);
            if(top2.left!=null)
                q.offer(top2.left);
            if((q.size()&0x1)!=0)
                return false;
        }
        return true;
    }

    //102 recursive way and iterative way
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>res=new ArrayList<>();
        Queue<TreeNode>q=new LinkedList<>();
        if(root==null)
            return res;
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer>path=new ArrayList<>();
            while(size-- >0){
                TreeNode top=q.poll();
                path.add(top.val);
                if(top.left!=null)
                    q.add(top.left);
                if(top.right!=null)
                    q.add(top.right);
            }
            res.add(path);
        }
        return res;
    }

    //recursive way

    public void dfs(List<List<Integer>>res,TreeNode root,int pos){
        if(root==null)
            return;
        if(res.size()<=pos)
            res.add(new ArrayList<>());
        res.get(pos).add(root.val);
        dfs(res,root.left,pos+1);
        dfs(res,root.right,pos+1);
    }
    public List<List<Integer>>levelOrderRecursive(TreeNode root){
        List<List<Integer>>res=new ArrayList<>();
        dfs(res,root,0);
        return res;
    }

    //103 binary tree zigzag order
    //废题，就是和上题是一样的

    //104 maxDepth tree
    public int maxDepth(TreeNode root) {
        if(root==null)
            return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }

    //iterative way
    public int maxDepthIterative(TreeNode root){
        if(root==null)
            return 0;
        Queue<Tuple<TreeNode,Integer>>q=new LinkedList<>();
        q.offer(new Tuple<>(root,1));
        int maxDep=0;
        while(!q.isEmpty()){
            Tuple t=q.poll();
            TreeNode node=(TreeNode)t.x;
            int level=(Integer)t.y;
            if(node.left==null && node.right==null){
                maxDep=Math.max(maxDep,level);
            }
            if(node.left!=null)
                q.offer(new Tuple<>(node.left,level+1));
            if(node.right!=null)
                q.offer(new Tuple<>(node.right,level+1));
        }
        return maxDep;
    }

    //105 Construct Binary Tree from Preorder and Inorder Traversal

    //recursive way
    public TreeNode buildTree(int[]preorder,int start1,int end1,int[]inorder,int start2,int end2){
        if(start1>end1)
            return null;
        if(start1==end1)
            return new TreeNode(preorder[start1]);
        TreeNode root=new TreeNode(preorder[start1]);
        int ind=start2;
        while(ind<end2){
            if(inorder[ind]==preorder[start1])
                break;
            ind++;
        }
        root.left=buildTree(preorder,start1+1,ind-start2+start1,inorder,start2,ind-1);
        root.right=buildTree(preorder,ind+start1-start2+1,end1,inorder,ind+1,end2);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n=preorder.length;
        return buildTree(preorder,0,n-1,inorder,0,n-1);
    }

    //iterative way
    //interesting way



    //construct binary tree from inorder and inorder

    public TreeNode buildTreePost(int[]inorder,int start1,int end1,int[]postorder,int start2,int end2){
        if(start2>end2)
            return null;
        if(start2==end2)
            return new TreeNode(postorder[start2]);
        TreeNode root=new TreeNode(postorder[end2]);
        int ind=start1;
        while(ind<end1){
            if(inorder[ind]==postorder[end2])
                break;
            ind++;
        }
        root.left=buildTreePost(inorder,start1,ind-1,postorder,start2,ind-1-start1+start2);
        root.right=buildTreePost(inorder,ind+1,end1,postorder,end2-end1+ind,end2-1);
        return root;
    }
    public TreeNode buildTreePost(int[] inorder, int[] postorder) {
        int n=inorder.length;
        return buildTreePost(inorder,0,n-1,postorder,0,n-1);
    }

    //107 level order II

    //要是reverse的那种，大可不必最后reverse，只需要insert(0,arraylist);
    public void dfs(TreeNode root,List<List<Integer>>res,int pos){
        if(root==null)
            return;
        if(res.size()<=pos)
            res.add(0,new ArrayList<>());
        res.get(res.size()-pos-1).add(root.val);//很有意思
        dfs(root.left,res,pos+1);
        dfs(root.right,res,pos+1);
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>>res=new ArrayList<>();
        dfs(root,res,0);
        return res;
    }


    //108 Convert Sorted Array to Binary Search Tree
    //divide and conquer

    public TreeNode build(int[]nums,int begin,int end){
        if(begin>end)
            return null;
        if(begin==end)
            return new TreeNode(nums[begin]);
        int mid=(end-begin)/2+begin;
        TreeNode root=new TreeNode(nums[mid]);
        root.left=build(nums,begin,mid-1);
        root.right=build(nums,mid+1,end);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        return build(nums,0,n-1);
    }


    //109 converted sorted linked list to binary search tree
    //naive way is to converted it to array first
    //O(nlogn)

    public TreeNode toBST(ListNode head,ListNode tail){
        if(head==tail)
            return null;
        if(head.next==tail)
            return new TreeNode(head.val);
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=tail && fast.next!=tail){
            fast=fast.next.next;
            slow=slow.next;
        }
        TreeNode root=new TreeNode(slow.val);
        root.left=toBST(head,slow);
        root.right=toBST(slow.next,tail);
        return root;
    }
    public TreeNode sortedListToBST(ListNode head) {
        return toBST(head,null);
    }

    //O(N)
    //a little tricky
    //很有意思的一道题
    ListNode prev=null;

    public TreeNode recursive(int start,int end){
        if(start>end)
            return null;
        int mid=(end-start)/2+start;
        TreeNode left=recursive(start,mid-1);
        TreeNode node=new TreeNode(prev.val);
        node.left=left;
        prev=prev.next;
        node.right=recursive(mid+1,end);
        return node;
    }
    public TreeNode sortedListToBSTOONE(ListNode head){
        if(head==null)
            return null;
        prev=head;
        ListNode p=head;
        int size=0;
        while(p!=null){
            size++;
            p=p.next;
        }
        return recursive(0,size-1);
    }


    //110 balanced binary tree

    public int getHeight(TreeNode node){
        if(node==null)
            return 0;
        int l=getHeight(node.left);
        int r=getHeight(node.right);
        if(l<0||r<0||Math.abs(l-r)>1)
            return -1;
        return Math.max(l,r)+1;
    }
    public boolean isBalanced(TreeNode root) {
        return getHeight(root)>-1;
    }
}
