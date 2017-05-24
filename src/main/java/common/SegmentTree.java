package common;

/**
 * Created by tao on 5/22/17.
 */
public class SegmentTree {


    public SegmentTreeNode build(int start,int end){
        if(start>end)
            return null;
        if(start==end)
            return new SegmentTreeNode(start,end);
        SegmentTreeNode root=new SegmentTreeNode(start,end);
        int mid = (end-start)/2+start;
        root.left=build(start,mid);
        root.right=build(mid+1,end);
        return root;
    }

    //create a corresponding segment tree with every node value represent the correspond interval max value in the array

    public SegmentTreeNodeMax build(int[]A,int start,int end){
        if(start>end)
            return null;
        if(start==end)
            return new SegmentTreeNodeMax(start,end,A[start]);
        SegmentTreeNodeMax root=new SegmentTreeNodeMax(start,end,0);
        int mid =(end-start)/2+start;
        root.left=build(A,start,mid);
        root.right=build(A,mid+1,end);
        root.max=Math.max(root.left.max,root.right.max);
        return root;
    }
    public SegmentTreeNodeMax build(int[] A) {
        // write your code here
        return build(A,0,A.length-1);
    }


    //query the max value in the interval
    public int query(SegmentTreeNodeMax root, int start, int end) {
        // write your code here
        if(start>end)
            return Integer.MIN_VALUE;
        if(root.left==null && root.right==null)
            return root.max;
        if(end<=root.left.end)
            return query(root.left,start,end);
        else if(start>=root.right.start)
            return query(root.right,start,end);
        else
            return Math.max(query(root.left,start,root.left.end),query(root.right,root.right.start,end));
    }

    //count, 记得特殊情况还是要特殊处理
    public int query(SegmentTreeNodeCount root, int start, int end) {
        // write your code here
        if(start>end||root==null||start>root.end||end<root.start)
            return 0;
        if(start<=root.start && end>=root.end)
            return root.count;
        if(root.left==null && root.right==null)
            return root.count;
        if(root.left.end>=end)
            return query(root.left,start,end);
        else if(root.right.start<=start)
            return query(root.right,start,end);
        else
            return query(root.left,start,root.left.end)+query(root.right,root.right.start,end);
    }


    public void modify(SegmentTreeNodeMax root, int index, int value) {
        // write your code here
        if(root==null||index>root.end||index<root.start)
            return;
        if(root.left==null && root.right==null){
            root.max=value;
            return;
        }
        if(index<=root.left.end)
            modify(root.left,index,value);
        else if(index>=root.right.start)
            modify(root.right,index,value);
        root.max=Math.max(root.left.max,root.right.max);
    }

}
