package design;

import common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tao on 5/25/17.
 */

//serialize and deserialize bst tree
public class CodecBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb =new StringBuilder();
        serialize(root,sb);
        return sb.toString();
    }


    public void serialize(TreeNode root, StringBuilder sb){
        if(root==null){
            sb.append("@").append(" ");
            return;
        }
        sb.append(root.val).append(" ");
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String []strs=data.split(" ");
        Deque<String>dq=new LinkedList<>();
        for(String str:strs)
            dq.offer(str);
        return deserialize(dq);
    }

    public TreeNode deserialize(Deque<String>dq){
        String top=dq.poll();
        if(top==null||top.equals("@"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(top));
        root.left=deserialize(dq);
        root.right=deserialize(dq);
        return root;
    }
}
