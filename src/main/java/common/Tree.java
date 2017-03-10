package common;

import java.util.List;
import java.util.Stack;

/**
 * Created by tao on 3/9/17.
 */
public class Tree {

    public void inorder(TreeNode node,List<Integer> res){
        if(node==null)
            return;
        inorder(node.left,res);
        res.add(node.val);
        inorder(node.right,res);
    }

    public void preorder(TreeNode node,List<Integer>res){
        if(node==null)
            return;
        res.add(node.val);
        preorder(node.left,res);
        preorder(node.right,res);
    }

    public void postorder(TreeNode node,List<Integer>res){
        if(node==null)
            return;
        postorder(node.left,res);
        postorder(node.right,res);
        res.add(node.val);
    }


    public void preorderByStack(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode>stk=new Stack<>();
        stk.push(root);
        while(!stk.isEmpty()){
            TreeNode node=stk.pop();
            System.out.println(node.val);
            if(node.right!=null)
                stk.push(node.right);
            if(node.left!=null)
                stk.push(node.left);
        }
    }

    public void inorderByStack(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode>stk=new Stack<>();
        while(root!=null){
            stk.push(root);
            root=root.left;
        }

        while(!stk.isEmpty()){
            TreeNode node=stk.pop();
            System.out.println(node.val);
            if(node.right!=null){
                node=node.right;
                while(node!=null){
                    stk.push(node);
                    node=node.left;
                }
            }
        }
    }


    //我已经很高兴了
    public void postorderByStack(TreeNode root){
        if(root==null)
            return;
        TreeNode pre=null;
        Stack<TreeNode>stk=new Stack<>();
        stk.push(root);
        while(!stk.isEmpty()){
            TreeNode node=stk.peek();
            if(pre==null||pre.left==node||pre.right==node){
                if(node.left!=null)
                    stk.push(node.left);
                else if(node.right!=null)
                    stk.push(node.right);
                else{
                    System.out.println(node.val);
                    stk.pop();
                }
            }else if(pre==node.left){
                if(node.right!=null)
                    stk.push(node.right);
                else{
                    System.out.println(node.val);
                    stk.pop();
                }
            }else if(pre==node.right){
                System.out.println(node.val);
                stk.pop();
            }
            pre=node;
        }
    }

    //another inorder way,
    public void inorderByAnotherStack(TreeNode node){
        if(node==null)
            return ;
        Stack<TreeNode>stk=new Stack<>();
        while(node!=null||!stk.isEmpty()){
            while(node!=null){
                stk.push(node);
                node=node.left;
            }
            node=stk.pop();
            System.out.println(node.val);
            node=node.right;
        }
    }

    //another postorder way
    public void postorderByAnotherStack(TreeNode node){
        if(node==null)
            return;
        TreeNode pre = null;
        Stack<TreeNode>stk=new Stack<>();
        while(node!=null ||!stk.isEmpty()){
            while(node!=null){
                stk.push(node);
                node=node.left;
            }
            node=stk.peek();
            if(node.right!=null && pre!=node.right){
                node=node.right;
            }else{
                //already visited
                System.out.println(node.val);
                pre=node;
                stk.pop();
                node=null;
            }
        }
    }

}
