package design;

import common.TreeNode;

/**
 * Created by tao on 3/18/17.
 */
public class BSTIteratorMorris {
    private TreeNode cur = null;

    public BSTIteratorMorris(TreeNode node) {
        cur = node;
    }

    public boolean hasNext() {
        return cur != null;
    }

    public int next() {
        TreeNode prev = null;
        int res = 0;
        while (cur != null) {
            if (cur.left == null) {
                res = cur.val;
                cur = cur.right;
                break;
            } else {
                //find predecessor
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    res = cur.val;
                    cur = cur.right;
                    break;
                }
            }
        }
        return res;
    }
}
