package design;

import common.TreeNode;

import java.util.Stack;

/**
 * Created by tao on 3/18/17.
 */
//其实就是中序遍历，naive way的方法就是一次性中序遍历存到数组里，然后开始整，sb的方法我就不写了
//用到栈，最多保留log（h）极致的话其实可以取到const space， morris 遍历，这个等以后再说吧，代码贴了上去
public class BSTIterator {

    private Stack<TreeNode>stk=null;
    TreeNode cur=null;
    public BSTIterator(TreeNode root) {
        stk=new Stack<>();
        cur=root;
        while(cur!=null){
            stk.push(cur);
            cur=cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur!=null ||!stk.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        while(cur!=null){
            stk.push(cur);
            cur=cur.left;
        }
        cur=stk.pop();
        int val=cur.val;
        cur=cur.right;
        return val;
    }
}