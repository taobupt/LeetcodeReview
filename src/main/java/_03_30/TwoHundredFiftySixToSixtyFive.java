package _03_30;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao on 3/30/17.
 */
public class TwoHundredFiftySixToSixtyFive {

    //257 binary tree paths
    //dfs is the most simple

    public void dfs(List<String>res,TreeNode root,List<String>path){
        if(root==null)
            return;
        path.add(""+root.val);
        if(root.left==null && root.right==null){
            res.add(String.join("->",new ArrayList<CharSequence>(path)));
        }
        dfs(res,root.left,path);
        dfs(res,root.right,path);
        path.remove(path.size()-1);

    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String>res=new ArrayList<>();
        List<String>path=new ArrayList<>();
        dfs(res,root,path);
        return res;
    }
}
