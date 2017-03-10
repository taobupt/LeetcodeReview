package _03_07;

import common.Tree;
import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tao on 3/9/17.
 */

//补3月七号没做的作业
public class NinetyOneToOneHundred {


    TreeNode pre=null;


    //98 valid binary search tree
    //naive way, wast of space
    public boolean isValidBST(TreeNode root) {
        Tree t=new Tree();
        List<Integer> res=new ArrayList<>();
        t.inorder(root,res);
        int n=res.size();
        for(int i=1;i<n;++i){
            if(res.get(i)<=res.get(i-1))
                return false;
        }
        return true;
    }

    //save space;

    public boolean isValidBSTSaveSpace(TreeNode root){
        if(root==null)
            return true;
        if(!isValidBSTSaveSpace(root.left))
            return false;
        if(pre!=null && pre.val>=root.val)
            return false;
        pre=root;
        return isValidBSTSaveSpace(root.right);
    }

    //两边有值的recursive way

    //iterative way
    public boolean isValidBSTIterative(TreeNode root){
        //其实就是一个中序遍历，你要知道可是有4种中序遍历，递归只是其中的一种罢了
        //一种递归，两种stack，一种Morris
        return false;

    }










    //100 same tree
    //树有两种解决办法，一种是recursive，一种是iterative way
    //recursive way
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null||q==null)
            return p==q;
        return (p.val==q.val) &&isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    //iterative way;

    //use queue
    //打得太快，容易出现typo
    //用一个queue也是可以的，每次pop出俩，每次进left之后都判断是不是%2
    public boolean isSameTreeIterative(TreeNode p,TreeNode q){
        if(p==null||q==null)
            return p==q;
        Queue<TreeNode>queue1=new LinkedList<>();
        Queue<TreeNode>queue2=new LinkedList<>();
        queue1.offer(p);
        queue2.offer(q);
        while(!queue1.isEmpty()){
            TreeNode pnode=queue1.poll();
            TreeNode qnode=queue2.poll();
            if(pnode.val!=qnode.val)
                return false;
            if(pnode.left!=null)
                queue1.offer(pnode.left);
            if(qnode.left!=null)
                queue2.offer(qnode.left);
            if(queue1.size()!= queue2.size())
                return false;
            if(pnode.right!=null)
                queue1.offer(pnode.right);
            if(qnode.right!=null)
                queue2.offer(qnode.right);
            if(queue1.size()!= queue2.size())
                return false;
        }
        return true;
    }

    //use stack ,其实也就是把尾递归改成你想要的

}
