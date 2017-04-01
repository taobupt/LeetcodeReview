package _04_01;

import common.TreeNode;

/**
 * Created by Tao on 4/1/2017.
 */
public class TwoHundredSeventySixToEightyFive {
    //285 麻蛋，昨天在HEB想了很久，我擦，居然忘了，看来还是理解不够深入啊，得经常复习啊，少年
    //norder Successor in BST
    //recursive
    //without parent
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null)
            return null;
        if(root.val<=p.val)
            return inorderSuccessor(root.right,p);
        else{
            TreeNode left=inorderSuccessor(root.left,p);
            return left!=null?left:root;
        }
    }

    //iterative way
    public TreeNode inorderSuccessorIterative(TreeNode root,TreeNode p){
        TreeNode cur=root;
        TreeNode res=null;
        while(cur!=null){
            if(cur.val<=p.val)
                cur=cur.right;
            else{
                res=cur;
                cur=cur.left;
            }
        }
        return res;
    }

    //predecessor 和这个是一样的，稍微改变一下就可以了,J就是把判断条件改一下

    //with parent
    //有右节点，就往右，再往左，不然就是利用parent节点向上爬，爬到parent.left=cur;
    public TreeNode inorderSuccessorWithParent(TreeNode p){
        //
        TreeNode res=null;
        TreeNode curr=p;
        if(curr.right!=null){
            curr=curr.right;
            while(curr.left!=null)
                curr=curr.left;
            res=curr;
        }else{
            TreeNode parent=curr.parent;
            while(parent!=null && parent.right==curr){
                curr=parent;
                parent=curr.parent;
            }
            res=parent;
        }
        return res;
    }
}
