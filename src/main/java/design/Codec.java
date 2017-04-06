package design;

import common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tao on 4/6/17.
 */

//就是递归来写嘛,其实是不用q的，直接数组也是可以搞定的，
// 我贴给你看哪看,数组其实是更快的。
// 用deque来解码是最快的



    /*
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
     */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        serialize(root,sb);
        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String []strs=data.split("\\s");
        Queue<String> q=new LinkedList<>(Arrays.asList(strs));
        return deserialize(q);

        /*
        int end=strs.length-1;
        int []res=new int[1];
        return deserialize(strs,res,end);

         */
    }

    private TreeNode deserialize(String[]strs,int []res,int end){
        if(res[0]>end||strs[res[0]].equals("@"))
            return null;
        TreeNode root=new TreeNode(Integer.parseInt(strs[res[0]]));
        res[0]++;
        root.left=deserialize(strs,res,end);
        res[0]++;
        root.right=deserialize(strs,res,end);
        return root;
    }

    public TreeNode deserialize(Queue<String>q){
        if(q.isEmpty())
            return null;
        String val=q.poll();
        if(val.equals("@"))
            return null;
        TreeNode root=new TreeNode(Integer.parseInt(val));
        root.left=deserialize(q);
        root.right=deserialize(q);
        return root;
    }


    private void serialize(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append('@').append(' ');
            return;
        }
        sb.append(root.val).append(' ');
        serialize(root.left,sb);
        serialize(root.right,sb);
    }
}
