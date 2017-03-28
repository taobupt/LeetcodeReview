package _03_27;

import common.ListNode;
import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 3/27/17.
 */
public class TwoHundredTwentyFiveToThirtyFive {

    //226 invert binary tree
    //iterative and recursive
    //data is large, then the program will crash
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return null;
        TreeNode left=root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(left);
        return root;
    }

    //iterative way,  就是每个节点都反转子节点
    public TreeNode invertTreeIterative(TreeNode root){
        if(root==null)
            return null;
        Queue<TreeNode>q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode node=q.poll();

            //swap children
            TreeNode tmp=node.left;
            node.left=node.right;
            node.right=tmp;
            if(node.left!=null)
                q.offer(node.left);
            if(node.right!=null)
                q.offer(node.right);
        }
        return root;
    }

    //228 Summary Ranges
    //pay attention to the overflow
    public List<String> summaryRanges(int[] nums){
        List<String>res=new ArrayList<>();
        int n=nums.length;
        if(n==0)
            return res;
        int start=nums[0];
        int i=1;
        while(i<n){
            while(i<n && nums[i]==nums[i-1]+1)
                i++;
            res.add(""+(nums[i-1]==start?start:start+"->"+nums[i-1]));
            if(i<n)
                start=nums[i++];
        }
        res.add(""+(nums[i-1]==start?start:start+"->"+nums[i-1]));
        return res;

    }


    //229 majority element II
    public List<Integer> majorityElement(int[] nums) {
        //morre vote
        int a=0,b=0;
        int n=nums.length;
        int cnt_a=0,cnt_b=0;
        for(int x:nums){
            if(x==a){
                cnt_a++;
            }else if(x==b){
                cnt_b++;
            }else if(cnt_a==0){
                cnt_a=1;
                a=x;
            }else if(cnt_b==0){
                cnt_b=1;
                b=x;
            }else{
                cnt_a--;
                cnt_b--;
            }
        }

        //check whether the two are valid
        cnt_a=0;
        cnt_b=0;
        for(int x:nums){
            if(x==a)
                cnt_a++;
            else if(x==b)
                cnt_b++;
        }
        List<Integer>res=new ArrayList<>();
        if(cnt_a>n/3)
            res.add(a);
        if(cnt_b>n/3)
            res.add(b);
        return res;
    }

    //230 Kth Smallest Element in a BST
    //第一种就是做个中序遍历，然后取nums[k-1]
    //声明prev node
    //很重点啊，因为k也是会发生改变的，
    int res =0;
    public int inorder(TreeNode root,int k){
        if(root==null)
            return k;
        k=inorder(root.left,k);
        k--;
        if(k==0){
            res=root.val;
        }
        k=inorder(root.right,k);
        return k;
    }
    public int kthSmallest(TreeNode root, int k) {
            inorder(root,k);
            return res;
    }

    //morris inorder
    public int kthSmallestMorris(TreeNode root, int k) {
        TreeNode cur=root;
        TreeNode prev=null;
        while(cur!=null){
            if(cur.left==null){
                k--;
                if(k==0)
                    return cur.val;
                cur=cur.right;
            }else{
                //find predecessor
                prev=cur.left;
                while(prev.right!=null && prev.right!=cur){
                    prev=prev.right;
                }
                if(prev.right==null){
                    prev.right=cur;
                    cur=cur.left;
                }else{
                    prev.right=null;
                    k--;
                    if(k==0)
                        return cur.val;
                    cur=cur.right;
                }
            }
        }
        return -1;
    }

    //231 power of 2
    //while or recursive
    //one bit
    public boolean isPowerOfTwo(int n) {

        return n>0 && (n&(n-1))==0;
        //return n>0 && Integer.bitCount(n)==1;
    }

    public boolean isPowerOfFour(int num) {
        if(num<=0)
            return false;
        return (num&(num-1))==0 && (num&0x55555555)!=0;
    }

    //232 has solved in design
    //234 palindrome linked list
    //取一半反转
    //1 2 3 4 5 6
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null)
            return true;
        ListNode fast=head.next;
        ListNode slow=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        fast=slow.next;
        //REVERSE second half;
        ListNode sec=null;
        while(fast!=null){
            ListNode next=fast.next;
            fast.next=sec;
            sec=fast;
            fast=next;
        }
        fast=sec;
        slow.next=null;
        slow=head;
        while(fast!=null){
            if(fast.val!=slow.val)
                return false;
            slow=slow.next;
            fast=fast.next;
        }
        return true;
    }

    //recursive way
    //全局变量
    ListNode cur;
    public boolean isPalindromeRecursive(ListNode head){
        if(head==null||head.next==null)
            return true;
        cur=head;
        return isPalindromeHelper(head);
    }
    public boolean isPalindromeHelper(ListNode head){
        if(head==null)
            return true;
        boolean flag=isPalindromeHelper(head.next);
        if(flag && cur.val==head.val){
            cur=cur.next;
            return true;
        }
        else
            return false;
    }

    //235
    //Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||p==null||q==null)
            return null;
        if(root.val>Math.max(p.val,q.val))
            return lowestCommonAncestor(root.left,p,q);
        if(root.val<Math.min(p.val,q.val))
            return lowestCommonAncestor(root.right,p,q);
        else
            return root;
    }

    //iterative way
    public TreeNode lowestCommonAnesotrIterative(TreeNode root,TreeNode p,TreeNode q){
        if(root==null||p==null||q==null)
            return null;
        TreeNode cur=root;
        int minValue=Math.min(p.val,q.val);
        int maxValue=Math.max(p.val,q.val);
        while(cur!=null){
            if(cur.val>maxValue)
                cur=cur.left;
            else if(cur.val<minValue)
                cur=cur.right;
            else
                return cur;
        }
        return cur;
    }
}
